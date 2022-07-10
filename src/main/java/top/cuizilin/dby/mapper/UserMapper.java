package top.cuizilin.dby.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.cuizilin.dby.pojo.User;
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
public interface UserMapper extends BaseMapper<User> {
    //根据用户id查询对应用户
    User searchByUserId(String userId);
}
