package cn.eleven.admin.action;

import cn.eleven.admin.pojo.Admin;
import cn.eleven.admin.service.AdminService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

/**
 * Created by User on 2017/10/7.
 */
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {

    private Admin admin = new Admin();
    private AdminService adminService;

    public Admin getModel() {
        return admin;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public String login(){
        Admin exitAdmin =  adminService.login(admin);
        if (exitAdmin == null){
            this.addActionError("用户名密码错误，请重新登陆");
            return "loginFail";
        }else {
            ServletActionContext.getRequest().getSession().setAttribute("admin",exitAdmin);
            return "loginSuccess";
        }

    }
}
