package cn.eleven.categorySecond.service;

import cn.eleven.categorySecond.dao.CategorySecondDao;
import cn.eleven.categorySecond.pojo.CategorySecond;
import cn.eleven.utils.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 2017/10/7.
 */
@Transactional
public class CategorySecondSerivce {
    private CategorySecondDao categorySecondDao;

    public CategorySecondDao getCategorySecondDao() {
        return categorySecondDao;
    }

    public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
        this.categorySecondDao = categorySecondDao;
    }

    public PageBean<CategorySecond> findAllByPage(CategorySecond categorySecond, Integer page) {
        PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
        pageBean.setPage(page);

        int limit = 8;
        pageBean.setLimit(limit);

        int totalCount = categorySecondDao.findCount();
        pageBean.setTotalCount(totalCount);

        Integer totalpage = null;
        if (totalCount % limit == 0){
            totalpage = totalCount / limit;
        }else{
            totalpage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalpage);

        int begin = (page - 1 )*limit;

        List<CategorySecond> categorySeconds = categorySecondDao.findByPage(begin,limit);
        pageBean.setList(categorySeconds);

        return pageBean;
    }

    public void save(CategorySecond categorySecond) {
        categorySecondDao.save(categorySecond);
    }

    public CategorySecond findByCsisd(Integer csid) {
        return categorySecondDao.findByCsid(csid);
    }

    public void delete(CategorySecond categorySecond) {
        categorySecondDao.delete(categorySecond);
    }

    public void update(CategorySecond categorySecond) {
        categorySecondDao.update(categorySecond);
    }

    public List<CategorySecond> findAll() {
        return categorySecondDao.findAll();
    }
}
