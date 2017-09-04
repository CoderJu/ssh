package cn.eleven.user.service;

import cn.eleven.user.dao.UserDao;
import cn.eleven.user.pojo.User;
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
}
