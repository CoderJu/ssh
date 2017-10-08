package cn.eleven.user.adminAction;

import cn.eleven.user.pojo.User;
import cn.eleven.user.service.UserService;
import cn.eleven.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Created by User on 2017/10/8.
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    private UserService userService;
    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getModel() {
        return user;
    }

    public String findAllByPage(){

        PageBean<User> pageBean =  userService.findAllByPage(page);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAllByPage";
    }

    public String edit(){
        user = userService.findByUid(user.getUid());
        return "edit";
    }

    public String update(){
        userService.update(user);
        return "updateSuccess";
    }

    public String delete(){
        user = userService.findByUid(user.getUid());
        user.setState(0);
        userService.update(user);
        return "deleteSuccess";
    }

}
