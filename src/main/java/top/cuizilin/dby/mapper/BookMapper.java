package top.cuizilin.dby.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.cuizilin.dby.pojo.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-04
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    List<Book> searchAllByTagId(String tagId);


    List<Book> searchAllByBookId(String bookId);

    List<Book> searchAllByListId(String listId);
}
