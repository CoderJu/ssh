package cn.eleven.user.dao;


import cn.eleven.user.pojo.User;
import cn.eleven.utils.PageBean;
import cn.eleven.utils.PageHibernateCallback;
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

    /**
     * 存入数据库
     * @param user
     */
    public void save(User user) {
        this.getHibernateTemplate().save(user);
    }

    /**
     * 按照激活码查询
     * @param code
     * @return
     */
    public User findByCode(String code) {
        String hql = "from User u where u.code = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql,code);
        if (list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public void update(User exitUser) {
        this.getHibernateTemplate().update(exitUser);
    }

    public User login(User user) {
        String hql = "from User u where u.username = ? and u.password=? and u.state = '1' ";
        List<User> list =  (List<User>)this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword());
        if (list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public PageBean<User> findAllByPage(Integer page) {
        return null;
    }

    public int findCount() {
        String hql = "select count(*) from User order by uid";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    public List<User> findByPage(int begin, int limit) {
        String hql = "from User ";
        List<User> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<User>(hql,null,begin,limit));
        if (list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    public User findByUid(Integer uid) {
       return this.getHibernateTemplate().get(User.class,uid);
    }
}
