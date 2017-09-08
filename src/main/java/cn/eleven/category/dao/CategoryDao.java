package cn.eleven.category.dao;

import cn.eleven.category.pojo.Category;
import cn.eleven.category.service.CategoryService;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by User on 2017/9/8.
 */
public class CategoryDao extends HibernateDaoSupport {


    public List<Category> findAll() {
       String hql = " from Category";
       List<Category> list = (List<Category>) this.getHibernateTemplate().find(hql);
       return list;
    }
}
