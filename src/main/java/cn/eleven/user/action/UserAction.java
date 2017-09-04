package cn.eleven.user.action;

import cn.eleven.user.pojo.User;
import cn.eleven.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 2017/8/30.
 */

public class UserAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();
    //注入service
    private UserService userService ;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 模型驱动
     * @return
     */
    public User getModel() {

        return user;
    }

    public String registerPage(){
        return "registerPage";
    }


    /**
     * 校验用户名是否存在
     * @return
     * @throws IOException
     */
    public String findByUserName() throws IOException {
       User exitUser = userService.findByUserName(user.getUsername());
       HttpServletResponse response =   ServletActionContext.getResponse();
       response.setContentType("text/html;charset=UTF-8");
       if (exitUser != null){
           //用户名已经存在
           response.getWriter().print("<font color='red'>用户名已经存在</font>");
       }else {
           //用户名不存在
           response.getWriter().print("<font color='green'>用户名可以使用</font>");
       }
        return null;
    }


    public String register(){
        System.out.print("=====================");
        return null;
    }


}
