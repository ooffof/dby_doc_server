package top.cuizilin.dby.service;

import org.springframework.web.multipart.MultipartFile;
import top.cuizilin.dby.dto.BookUpdate;
import top.cuizilin.dby.dto.BookUpload;
import top.cuizilin.dby.dto.R;
import top.cuizilin.dby.pojo.Book;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import top.cuizilin.dby.pojo.Booklist;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-04
 */
public interface IBookService extends IService<Book> {

    R<Book> upload(MultipartFile file, BookUpload upload) throws Exception;

    R<Map<String, Object>> listBook(Integer pageNo, Integer pageSize);

    byte[] getCoverByBookId(String bookId) throws Exception;

    R<Book> getBookByBookId(String bookId);

    R<Map<String, Object>> searchBookByName(String bookName, Integer pageNo, Integer pageSize);

    byte[] downloadBook(String bookId) throws Exception;

    R<List<Book>> getRecommendBooks(String bookId);

    R<List<Booklist>> getRecommendBookList(String bookId);

    byte[] getSinglePDF(String bookId, Integer pageNo) throws IOException;

    R<Book> deleteById(String bookId);

    R<Book> updateBook(BookUpdate bookUpdate);

    R<Long> countBook();

    R<Integer> scan() throws Exception;

    R<Map<String, Object>> searchByTagName(Integer pageNo, Integer pageSize, String tagName);

    R<Map<String, Object>> searchByTypeName(Integer pageNo, Integer pageSize, String typeName);
}
