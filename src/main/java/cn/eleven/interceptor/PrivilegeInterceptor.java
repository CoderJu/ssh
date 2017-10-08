package cn.eleven.interceptor;

import cn.eleven.admin.pojo.Admin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * 后台权限拦截器
 * Created by User on 2017/10/8.
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {

    //未登录的用户无法访问
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
       Admin admin = (Admin) ServletActionContext.
               getRequest().getSession().getAttribute("admin");
       if (admin == null){
           ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
           actionSupport.addActionError("您尚未登录，请登录后访问！");
            return "loginFail";
       }else{
           return actionInvocation.invoke();
       }

    }
}
