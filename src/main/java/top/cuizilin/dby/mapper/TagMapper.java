package top.cuizilin.dby.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import top.cuizilin.dby.pojo.Tag;
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
public interface TagMapper extends BaseMapper<Tag> {
    //根据书的id查询所有标签
    List<Tag> searchALlByBookId(String bookId);

    int saveBookTag(String bookId, List<String> tagIds);

    int deleteTagsByBookId(String bookId);
}
