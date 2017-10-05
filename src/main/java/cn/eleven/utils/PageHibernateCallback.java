package cn.eleven.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.List;

/**
 * 分页执行HibernateCallback
 * Created by User on 2017/10/5.
 */
public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {
    private String hql;
    private Object[] param;
    private int startIndex;
    private int pageSize;

    public PageHibernateCallback(String hql, Object[] param, int startIndex, int pageSize) {
        super();
        this.hql = hql;
        this.param = param;
        this.startIndex = startIndex;
        this.pageSize = pageSize;
    }

    public List<T> doInHibernate(Session session) throws HibernateException {

        Query query = session.createQuery(hql);
        if (param != null){
            for(int i =0;i<param.length;i++){
                query.setParameter(i,param[i]);
            }
        }
        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);
        return query.list();
    }
}
