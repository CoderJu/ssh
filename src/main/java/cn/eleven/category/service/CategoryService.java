package cn.eleven.category.service;

import cn.eleven.category.dao.CategoryDao;
import cn.eleven.category.pojo.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 2017/9/8.
 */
@Transactional
public class CategoryService {

    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public void save(Category category) {
        categoryDao.save(category);
    }

    public Category findByCid(Integer cid) {
        return categoryDao.findByCid(cid);
    }

    public void update(Category category) {
        categoryDao.update(category);
    }


    public void deleteByCid(Category exitCategory) {
        categoryDao.deleteByCid(exitCategory);
    }
}
