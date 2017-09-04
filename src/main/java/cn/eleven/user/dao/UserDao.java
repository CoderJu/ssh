package cn.eleven.user.dao;


import cn.eleven.user.pojo.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by User on 2017/9/3.
 */
public class UserDao extends HibernateDaoSupport {


    /**
     * 按照登陆名查询
     * @param userName
     * @return
     */
    public User findByUserName(String userName){
        String hql = "from User u where u.username = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql,userName);
        if (list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
