package cn.eleven.categorySecond.adminCategorySecond;

import cn.eleven.categorySecond.pojo.CategorySecond;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Created by User on 2017/10/7.
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

    private CategorySecond categorySecond = new CategorySecond();

    public CategorySecond getModel() {
        return categorySecond;
    }
}
