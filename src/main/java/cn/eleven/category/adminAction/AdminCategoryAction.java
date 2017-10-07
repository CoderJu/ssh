package cn.eleven.category.adminAction;

import cn.eleven.category.pojo.Category;
import cn.eleven.category.service.CategoryService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.util.List;

/**
 * Created by User on 2017/10/7.
 */
public class AdminCategoryAction  extends ActionSupport implements ModelDriven<Category>{

    private Category category = new Category();
    private CategoryService categoryService;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Category getModel() {
        return category;
    }

    public String findAll(){
        List<Category> categories = categoryService.findAll();
        ServletActionContext.getContext().getValueStack().set("categories",categories);
        return "findAll";
    }

    public String save(){
        categoryService.save(category);
        return "saveSuccess";
    }

    public String edit(){
        category =  categoryService.findByCid(category.getCid());
        return "editPage";
    }

    public String update(){
        categoryService.update(category);
        return "updateSuccess";
    }

    public String delete(){
        //删除一级分类的同时删除二级分类需要通过对象删除
        Category exitCategory =  categoryService.findByCid(category.getCid());
        categoryService.deleteByCid(exitCategory);
        return "deleteSuccess";
    }
}
