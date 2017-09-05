package cn.eleven.user.service;

import cn.eleven.user.dao.UserDao;
import cn.eleven.user.pojo.User;
import cn.eleven.utils.UUIDUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017/9/3.
 */
@Transactional
public class UserService {
    //注入userdao
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findByUserName(String userName){
        return userDao.findByUserName(userName);
    }

    public void save(User user) {
        user.setState(0);//0代表用户未激活 1代表用户已经激活
        String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
        user.setCode(code);
        userDao.save(user);
    }
}
