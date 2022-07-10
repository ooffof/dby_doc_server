package top.cuizilin.dby.service;

import top.cuizilin.dby.dto.*;
import top.cuizilin.dby.pojo.Book;
import top.cuizilin.dby.pojo.Booklist;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-06
 */
public interface IBooklistService extends IService<Booklist> {

    R<Booklist> createBooklist(BookListUpload bookListUpload) throws Exception;

    R<List<Booklist>> getAllByUserId(String userId);

    R<Booklist> updateBooklist(BookListUpdate bookListUpdate);

    R<Booklist> deleteBookList(String bookList);

    R<Booklist> addBook(BookAdd bookAdd);

    R<List<Book>> getAll(String listId);

    R<Booklist> deleteBookFromBooklist(BookDelete bookDelete);

    R<List<Booklist>> getAllBooklist();

    R<Booklist> deleteById(String booklistId);

    R<Long> countBooklist();
}
