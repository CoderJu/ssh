package cn.eleven.user.service;

import cn.eleven.user.dao.UserDao;
import cn.eleven.user.pojo.User;
import cn.eleven.utils.EmailUtils;
import cn.eleven.utils.PageBean;
import cn.eleven.utils.UUIDUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        //发送激活邮件
        EmailUtils.sendEmail(user.getEmail(),code);
    }


    public User findByCode(String code) {
        return userDao.findByCode(code);
    }

    public void update(User exitUser) {
        userDao.update(exitUser);
    }

    public User login(User user) {
        return userDao.login(user);
    }

    public PageBean<User> findAllByPage(Integer page) {
        PageBean<User> pageBean = new PageBean<User>();

        pageBean.setPage(page);

        int totalCount = 0;
        totalCount = userDao.findCount();
        pageBean.setTotalCount(totalCount);

        int limit =8;
        pageBean.setLimit(limit);

        int totalPage = 0;
        if (totalCount % limit ==0){
            totalPage = totalCount /limit;
        }else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);

        int begin = (page - 1 ) * limit;
        List<User> list = userDao.findByPage(begin,limit);
        pageBean.setList(list);

        return pageBean;
    }

    public User findByUid(Integer uid) {
        return  userDao.findByUid(uid);
    }
}
