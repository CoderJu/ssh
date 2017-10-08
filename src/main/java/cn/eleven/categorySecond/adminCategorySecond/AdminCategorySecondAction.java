package cn.eleven.categorySecond.adminCategorySecond;

import cn.eleven.category.pojo.Category;
import cn.eleven.category.service.CategoryService;
import cn.eleven.categorySecond.pojo.CategorySecond;
import cn.eleven.categorySecond.service.CategorySecondSerivce;
import cn.eleven.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.util.List;

/**
 * Created by User on 2017/10/7.
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

    private CategorySecond categorySecond = new CategorySecond();
    private CategorySecondSerivce categorySecondSerivce;
    private CategoryService categoryService;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public CategorySecond getModel() {
        return categorySecond;
    }

    public CategorySecondSerivce getCategorySecondSerivce() {
        return categorySecondSerivce;
    }

    public void setCategorySecondSerivce(CategorySecondSerivce categorySecondSerivce) {
        this.categorySecondSerivce = categorySecondSerivce;
    }

    public  String findAllByPage(){
       PageBean<CategorySecond> pageBean =  categorySecondSerivce.findAllByPage(categorySecond,page);
        ServletActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAllByPage";
    }

    public String addPage(){
        List<Category> cList = categoryService.findAll();
        ServletActionContext.getContext().getValueStack().set("cList",cList);
        return "addPage";
    }

    public String save(){
        categorySecondSerivce.save(categorySecond);
        return "saveSuccess";
    }

    public String delete(){
        //如果需要级联删除要先查询再删除
        categorySecond = categorySecondSerivce.findByCsisd(categorySecond.getCsid());
        categorySecondSerivce.delete(categorySecond);
        return "deleteSuccess";
    }

    public String edit(){
        categorySecond = categorySecondSerivce.findByCsisd(categorySecond.getCsid());
        List<Category> cList =  categoryService.findAll();
        ServletActionContext.getContext().getValueStack().set("cList",cList);
        return "editPage";
    }
    public String update(){
        categorySecondSerivce.update(categorySecond);
        return "updateSuccess";
    }
}
