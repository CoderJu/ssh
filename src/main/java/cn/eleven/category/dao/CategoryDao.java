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

    public void save(Category category) {
        this.getHibernateTemplate().save(category);
    }

    public Category findByCid(Integer cid) {
        String hql ="from Category where cid = ?";
        List<Category> list = (List<Category>) this.getHibernateTemplate().find(hql,cid);
        if (list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public void update(Category category) {
        this.getHibernateTemplate().update(category);
    }

    public void deleteByCid(Category category) {
        this.getHibernateTemplate().delete(category);
    }
}
