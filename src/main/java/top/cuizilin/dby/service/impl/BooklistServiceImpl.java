package top.cuizilin.dby.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import top.cuizilin.dby.dto.*;
import top.cuizilin.dby.pojo.Book;
import top.cuizilin.dby.pojo.Booklist;
import top.cuizilin.dby.mapper.BooklistMapper;
import top.cuizilin.dby.service.IBooklistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.cuizilin.dby.utils.ObjectUtil;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-06
 */
@Service
public class BooklistServiceImpl extends ServiceImpl<BooklistMapper, Booklist> implements IBooklistService {

    @Autowired
    private BooklistMapper booklistMapper;


    @Override
    public R<Booklist> createBooklist(BookListUpload bookListUpload) throws Exception {
       Booklist booklist = new Booklist();
       ObjectUtil.mergeObj(bookListUpload, booklist);
       this.save(booklist);
       return R.normalSuccess();
    }

    @Override
    public R<List<Booklist>> getAllByUserId(String userId) {
        List<Booklist> allList = this.list(new QueryWrapper<Booklist>().eq("user_id", userId));
        return R.normalSuccessAndData(allList);
    }

    @Override
    public R<Booklist> updateBooklist(BookListUpdate bookListUpdate) {
        UpdateWrapper<Booklist> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", bookListUpdate.getId()).set("name", bookListUpdate.getName())
                .set("description", bookListUpdate.getDescription()).set("is_public", bookListUpdate.getIsPublic());
        this.update(updateWrapper);
        return R.normalSuccess();
    }

    @Override
    public R<Booklist> deleteBookList(String id) {
        this.remove(new QueryWrapper<Booklist>().eq("id", id));
        return R.normalSuccess();
    }

    @Override
    public R<List<Book>> getAll(String listId) {
        Booklist booklist = this.getOne(new QueryWrapper<Booklist>().eq("id", listId));
        return R.normalSuccessAndData(booklist.getBookList());
    }

    @Override
    public R<Booklist> deleteBookFromBooklist(BookDelete bookDelete) {
        String userId = bookDelete.getUserId();
        String booklistId = bookDelete.getBooklistId();
        Booklist booklist = this.getOne(new QueryWrapper<Booklist>().eq("id", booklistId).eq("user_id", userId));
        if(booklist == null){
            return R.normalError("您不是该书单的拥有者");
        }
        booklistMapper.deleteBookFromBooklist(bookDelete.getBookId(), bookDelete.getBooklistId());
        return R.normalSuccess();
    }

    @Override
    public R<List<Booklist>> getAllBooklist() {
       return R.normalSuccessAndData(this.list(null));
    }

    @Override
    public R<Booklist> deleteById(String booklistId) {
       booklistMapper.delete(new QueryWrapper<Booklist>().eq("id", booklistId));
       return R.normalSuccess();
    }

    @Override
    public R<Long> countBooklist() {
       return R.normalSuccessAndData(this.count(null));
    }

    @Override
    public R<Booklist> addBook(BookAdd bookAdd) {
        String bookId = bookAdd.getBookId();
        String booklistId = bookAdd.getBooklistId();
        if(booklistMapper.searchBookAndBooklist(bookId, booklistId) > 0){
            return R.normalError("该书单中已经存在该书");
        }
        booklistMapper.addBookToBooklist(bookId, booklistId);
        return R.normalSuccess();
    }
}
