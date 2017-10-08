package cn.eleven.categorySecond.dao;

import cn.eleven.categorySecond.pojo.CategorySecond;
import cn.eleven.utils.PageHibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by User on 2017/10/7.
 */
public class CategorySecondDao extends HibernateDaoSupport {

    public int findCount() {
        String hql = "select count(*) from CategorySecond";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if (list != null && list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    public List<CategorySecond> findByPage(int begin, int limit) {
        String hql = "from CategorySecond order by csid desc";
        List<CategorySecond>  list = this.getHibernateTemplate()
                .execute(new PageHibernateCallback<CategorySecond>(hql,null,begin,limit));
        if (list != null && list.size()>0){
            return list;
        }
        return null;
    }

    public void save(CategorySecond categorySecond) {
        this.getHibernateTemplate().save(categorySecond);
    }

    public CategorySecond findByCsid(Integer csid) {
        return this.getHibernateTemplate().get(CategorySecond.class,csid);
    }

    public void delete(CategorySecond categorySecond) {
        this.getHibernateTemplate().delete(categorySecond);
    }

    public void update(CategorySecond categorySecond) {
        this.getHibernateTemplate().update(categorySecond);
    }
}
