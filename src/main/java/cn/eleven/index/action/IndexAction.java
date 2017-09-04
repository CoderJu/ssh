package cn.eleven.index.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页访问的Action
 * Created by User on 2017/8/29.
 */
public class IndexAction extends ActionSupport{

    @Override
    public String execute() throws Exception {
        return "index";
    }
}
