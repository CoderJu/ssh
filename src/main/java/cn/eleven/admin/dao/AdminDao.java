package cn.eleven.admin.dao;

import cn.eleven.admin.pojo.Admin;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by User on 2017/10/7.
 */
public class AdminDao extends HibernateDaoSupport{

    public Admin login(Admin admin) {
        String hql ="from Admin where  username = ? and password = ?";
        List<Admin> list = (List<Admin>) this.getHibernateTemplate()
                .find(hql,admin.getUsername(),admin.getPassword());
        if (list != null && list.size()>0){
            System.out.println("================"+list.get(0).getPassword()+list.get(0).getUsername());
            return list.get(0);
        }
        return null;
    }
}
