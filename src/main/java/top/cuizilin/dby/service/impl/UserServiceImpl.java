package top.cuizilin.dby.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import top.cuizilin.dby.dto.*;
import top.cuizilin.dby.pojo.User;
import top.cuizilin.dby.mapper.UserMapper;
import top.cuizilin.dby.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.cuizilin.dby.utils.ObjectUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public R<User> signup(UserSignup signup) throws Exception {
        if(this.getOne(new QueryWrapper<User>().eq("username", signup.getUsername())) != null){
            return R.normalError(Constants.USERNAME_EXISTS);
        }
        User user = new User();
        ObjectUtil.mergeObj(signup, user);
        user.setType(0);
        user.setBalance(100);
        user.setCreateTime(LocalDateTime.now());
        this.save(user);
        return R.normalSuccess();
    }

    @Override
    public R<User> login(UserLogin userLogin) throws Exception {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userLogin.getUsername())
                .eq("password", userLogin.getPassword());
        User user = this.getOne(queryWrapper);
        return user == null ? R.normalError("用户名或密码错误") : R.normalSuccessAndData(user);
    }

    @Override
    public R<List<User>> getAllUser() {
       return R.normalSuccessAndData(this.list(null));
    }

    @Override
    public R<User> deleteUser(String userId) {
       userMapper.delete(new QueryWrapper<User>().eq("id", userId));
       return R.normalSuccess();
    }

    @Override
    public R<User> updateUser(UserUpdate userUpdate) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", userUpdate.getId())
                .set("username", userUpdate.getUsername())
                .set("password", userUpdate.getPassword())
                .set("balance", userUpdate.getBalance())
                .set("type", userUpdate.getType());
        this.update(updateWrapper);
        return R.normalSuccess();
    }

    @Override
    public R<Long> countUser() {
        return R.normalSuccessAndData(this.count(null));
    }
}
