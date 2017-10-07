package cn.eleven.admin.service;

import cn.eleven.admin.dao.AdminDao;
import cn.eleven.admin.pojo.Admin;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017/10/7.
 */
@Transactional
public class AdminService {
    private AdminDao adminDao ;

    public AdminDao getAdminDao() {
        return adminDao;
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public Admin login(Admin admin) {
        return adminDao.login(admin);
    }
}
