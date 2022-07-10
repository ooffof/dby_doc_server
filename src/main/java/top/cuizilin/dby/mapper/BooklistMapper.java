package top.cuizilin.dby.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.cuizilin.dby.pojo.Book;
import top.cuizilin.dby.pojo.Booklist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-06
 */
@Mapper
public interface BooklistMapper extends BaseMapper<Booklist> {

    //根据书id获取对应所有书单
    List<Booklist> searchAllByBookId(String bookId);

    //根据用户id获取所有书单
    List<Booklist> searchALlByUserId(String userId);

    int addBookToBooklist(String bookId, String booklistId);

    int searchBookAndBooklist(String bookId, String booklistId);

    int deleteBookFromBooklist(String bookId, String booklistId);
}
