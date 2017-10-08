package cn.eleven.user.adminAction;

import cn.eleven.user.pojo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Created by User on 2017/10/8.
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    public User getModel() {
        return user;
    }
}
