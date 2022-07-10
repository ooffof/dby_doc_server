package top.cuizilin.dby.service;

import top.cuizilin.dby.dto.R;
import top.cuizilin.dby.dto.UserLogin;
import top.cuizilin.dby.dto.UserSignup;
import top.cuizilin.dby.dto.UserUpdate;
import top.cuizilin.dby.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-04
 */
public interface IUserService extends IService<User> {

    R<User> signup(UserSignup signup) throws Exception;


    R<User> login(UserLogin userLogin) throws Exception;

    R<List<User>> getAllUser();

    R<User> deleteUser(String userId);

    R<User> updateUser(UserUpdate userUpdate);

    R<Long> countUser();
}
