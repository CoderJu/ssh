package cn.eleven.category.service;

import cn.eleven.category.dao.CategoryDao;
import cn.eleven.category.pojo.Category;

import java.util.List;

/**
 * Created by User on 2017/9/8.
 */
public class CategoryService {

    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
