package top.cuizilin.dby.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.multipart.MultipartFile;
import top.cuizilin.dby.dto.BookUpdate;
import top.cuizilin.dby.dto.BookUpload;
import top.cuizilin.dby.dto.Constants;
import top.cuizilin.dby.dto.R;
import top.cuizilin.dby.mapper.TagMapper;
import top.cuizilin.dby.pojo.Book;
import top.cuizilin.dby.mapper.BookMapper;
import top.cuizilin.dby.pojo.Booklist;
import top.cuizilin.dby.pojo.Tag;
import top.cuizilin.dby.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.cuizilin.dby.utils.ObjectUtil;
import top.cuizilin.dby.utils.PDFUtil;

import java.io.*;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-04
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {


    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private BookMapper bookMapper;


    @Override
    public R<Book> upload(MultipartFile file, BookUpload upload) throws Exception {

        if (this.getOne(new QueryWrapper<Book>().eq("name", file.getOriginalFilename())) != null) {
            return R.normalError("该书已经存在");
        }

        Map<String, Object> map = PDFUtil.processPDF(file);
        Book book = new Book();
        ObjectUtil.mergeObj(upload, book);
        if ("".equals(upload.getNickname())) {
            book.setNickname(file.getName());
        }


        book.setSrc((String) map.get("src"));
        book.setImgSrc((String) map.get("imgSrc"));
        book.setTotalPage((Integer) map.get("totalPage"));

        if (map.get("dir") != null) {
            book.setDir(JSON.toJSONString(map.get("dir")));
            book.setHasDir(true);
        } else {
            book.setHasDir(false);
        }


        book.setName(file.getOriginalFilename());
        this.save(book);
        if (!"".equals(upload.getTagIds())) {
            tagMapper.saveBookTag(book.getId(), Arrays.asList(upload.getTagIds().split(",")));
        }
        return R.normalSuccess();
    }

    @Override
    public R<Map<String, Object>> listBook(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>(pageNo, pageSize);
        IPage<Book> iPage = bookMapper.selectPage(page, null);
        Map<String, Object> map = new HashMap<>();
        map.put("totalPage", iPage.getPages());
        map.put("records", iPage.getRecords());
        map.put("current", iPage.getCurrent());
        map.put("total", iPage.getTotal());
        return R.normalSuccessAndData(map);
    }

    @Override
    public byte[] getCoverByBookId(String bookId) throws Exception {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", bookId);
        Book book = this.getOne(queryWrapper);
        FileInputStream in = new FileInputStream(book.getImgSrc());
        byte[] b = new byte[in.available()];
        in.read(b);
        return b;
    }

    @Override
    public R<Book> getBookByBookId(String bookId) {
        return R.normalSuccessAndData(this.getOne(new QueryWrapper<Book>().eq("id", bookId)));
    }

    @Override
    public R<Map<String, Object>> searchBookByName(String bookName, Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>(pageNo, pageSize);
        IPage<Book> bookPage = bookMapper.selectPage(page, new QueryWrapper<Book>().like("nickname", bookName));

        Map<String, Object> map = new HashMap<>();
        map.put("totalPage", bookPage.getPages());
        map.put("records", bookPage.getRecords());
        map.put("current", bookPage.getCurrent());
        map.put("total", bookPage.getTotal());

        return R.normalSuccessAndData(map);
    }

    @Override
    public byte[] downloadBook(String bookId) throws Exception {
        Book book = this.getOne(new QueryWrapper<Book>().eq("id", bookId));
        String src = book.getSrc();
        FileInputStream in = new FileInputStream(src);
        byte[] b = new byte[in.available()];
        in.read(b);
        in.close();
        return b;
    }

    @Override
    public R<List<Book>> getRecommendBooks(String bookId) {
        Book book = this.getOne(new QueryWrapper<Book>().eq("id", bookId));
        String[] nameSplit = book.getName().split("\\.");
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (nameSplit.length > 1) {
            for (int i = 0; i < nameSplit.length; i++) {
                if (i < nameSplit.length - 1)
                    queryWrapper = queryWrapper.like("name", nameSplit[i]).or();
                else
                    queryWrapper.or(false);
            }
        } else {
            queryWrapper.like("name", book.getName());
        }
        List<Book> bookList = this.list(queryWrapper);
        for (Book b : bookList) {
            if (b.getId().equals(bookId)) {
                bookList.remove(b);
                break;
            }
        }
        return R.normalSuccessAndData(bookList);
    }

    @Override
    public R<List<Booklist>> getRecommendBookList(String bookId) {
        Book book = this.getOne(new QueryWrapper<Book>().eq("id", bookId));
        System.out.println(book);
        List<Booklist> booklists = book.getAllList();
        return R.normalSuccessAndData(booklists);
    }

    @Override
    public byte[] getSinglePDF(String bookId, Integer pageNo) throws IOException {
        Book book = this.getOne(new QueryWrapper<Book>().eq("id", bookId));

        if (redisTemplate.opsForValue().get(bookId) == null) {
            byte[] b = PDFUtil.getPDF(book.getSrc());
            redisTemplate.opsForValue().set(bookId, b);
        }
        byte[] nb = (byte[]) redisTemplate.opsForValue().get(bookId);
        PDDocument document = PDDocument.load(nb);
        PDDocument returnDoc = new PDDocument();
        returnDoc.addPage(document.getPage(pageNo));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        returnDoc.save(out);
        return out.toByteArray();
    }

    @Override
    public R<Book> deleteById(String bookId) {
        QueryWrapper<Book> q = new QueryWrapper<>();

        q.eq("id", bookId);
        Book book = this.getOne(q);
        if (book == null) {
            return R.normalSuccess();
        }
        bookMapper.delete(q);

        PDFUtil.deleteBookAndCover(book.getSrc(), book.getImgSrc());

        return R.normalSuccess();
    }

    @Override
    public R<Book> updateBook(BookUpdate bookUpdate) {
        UpdateWrapper<Book> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", bookUpdate.getId())
                .set("nickname", bookUpdate.getNickname())
                .set("author", bookUpdate.getAuthor())
                .set("type", bookUpdate.getType())
                .set("must", bookUpdate.getMust())
                .set("other", bookUpdate.getOther());
        this.update(updateWrapper);
        if (!"".equals(bookUpdate.getTagIds())) {
            List<String> tagIds = Arrays.asList(bookUpdate.getTagIds().split(","));
            tagMapper.deleteTagsByBookId(bookUpdate.getId());
            tagMapper.saveBookTag(bookUpdate.getId(), tagIds);
        }

        return R.normalSuccess();

    }

    @Override
    public R<Long> countBook() {
        return R.normalSuccessAndData(this.count(null));
    }

    @Override
    public R<Integer> scan() throws Exception {
        List<Book> list = this.list(null);
        List<String> books = new ArrayList<>();
        for (Book book : list) {
            books.add(book.getName());
        }
        int count = 0;
        String path = PDFUtil.PATH;
        File file = new File(PDFUtil.PATH);
        String[] pdfs = file.list();
        for (String pdf : pdfs) {
            if (!"cover".equals(pdf) && !books.contains(pdf)) {
                Book book = new Book();
                book.setName(pdf);
                count++;
                book.setSrc(path + pdf);
                book.setImgSrc(path + "cover/" + pdf.replace(".pdf", ".jpg"));

                try {
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(path + pdf));
                    byte[] b = new byte[in.available()];
                    in.read(b);
                    Map<String, Object> map = null;

                    map = PDFUtil.getBookMarkAndSaveCover(b, pdf);
                    book.setTotalPage((Integer) map.get("totalPage"));
                    if (map.get("dir") != null) {
                        book.setHasDir(true);
                        book.setDir(JSON.toJSONString(map.get("dir")));
                    } else {
                        book.setHasDir(false);
                    }
                } catch (Exception e) {
                    book.setNickname(book.getName());
                    book.setAuthor("未知");
                    book.setType("未知");
                    book.setMust("");
                    book.setOther("");

                    bookMapper.insert(book);
                    continue;
                }

                book.setNickname(book.getName());
                book.setAuthor("未知");
                book.setType("未知");
                book.setMust("");
                book.setOther("");

                bookMapper.insert(book);
            }
        }
        return R.normalSuccessAndData(count);
    }


    @Override
    public R<Map<String, Object>> searchByTagName(Integer pageNo, Integer pageSize, String tagName) {
        Tag tag = tagMapper.selectOne(new QueryWrapper<Tag>().eq("name", tagName));
        if(tag == null){
            return R.normalError("该标签不存在");
        }
        List<Book> books = tag.getBookList();
        List<String> bookIds = new ArrayList<>();
        for (Book book : books) {
            bookIds.add(book.getId());
        }
        Page<Book> bookPage = new Page<>(pageNo, pageSize);
        IPage<Book> iPage = bookMapper.selectPage(bookPage, new QueryWrapper<Book>().in("id", bookIds));
        Map<String, Object> map = new HashMap<>();
        map.put("current", iPage.getCurrent());
        map.put("total", iPage.getTotal());
        map.put("records", iPage.getRecords());
        map.put("pages", iPage.getPages());

        return R.normalSuccessAndData(map);
    }

    @Override
    public R<Map<String, Object>> searchByTypeName(Integer pageNo, Integer pageSize, String typeName) {
        Page<Book> bookPage = new Page<>(pageNo, pageSize);
        IPage iPage = bookMapper.selectPage(bookPage, new QueryWrapper<Book>().eq("type", typeName));
        Map<String, Object> map = new HashMap<>();
        map.put("current", iPage.getCurrent());
        map.put("total", iPage.getTotal());
        map.put("records", iPage.getRecords());
        map.put("pages", iPage.getPages());

        return R.normalSuccessAndData(map);
    }



}
