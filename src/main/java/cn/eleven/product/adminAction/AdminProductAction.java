package cn.eleven.product.adminAction;

import cn.eleven.categorySecond.pojo.CategorySecond;
import cn.eleven.categorySecond.service.CategorySecondSerivce;
import cn.eleven.product.pojo.Product;
import cn.eleven.product.service.ProductService;
import cn.eleven.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2017/10/8.
 */
public class AdminProductAction extends ActionSupport  implements ModelDriven<Product>{
    private ProductService productService;
    private Product product = new Product();
    private  Integer page;
    private CategorySecondSerivce categorySecondSerivce;

    //文件上传
    private File upload;//上传的文件
    private String uploadFileName; //文件名
    private String uploadContextType;//文件的MIME的类型

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContextType() {
        return uploadContextType;
    }

    public void setUploadContextType(String uploadContextType) {
        this.uploadContextType = uploadContextType;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Product getModel() {
        return product;
    }

    public CategorySecondSerivce getCategorySecondSerivce() {
        return categorySecondSerivce;
    }

    public void setCategorySecondSerivce(CategorySecondSerivce categorySecondSerivce) {
        this.categorySecondSerivce = categorySecondSerivce;
    }

    public String findAllByPage(){
        PageBean<Product> pageBean = productService.findAllByPage(product,page);
        ServletActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAllByPage";
    }

    public String addPage(){
        //查询所有二级分类
        List<CategorySecond> csList = categorySecondSerivce.findAll();
        ActionContext.getContext().getValueStack().set("csList",csList);
        return "addPage";
    }

    public String save() throws IOException {
        product.setPdate(new Date());
        if (upload !=null){
            //文件上传
            //1、获取文件上传的磁盘路径
          String realPath =   ServletActionContext.getServletContext().getRealPath("/products");
          File diskFile = new File(realPath+ "//" + uploadFileName);
          FileUtils.copyFile(upload,diskFile);
          product.setImage("products/"+uploadFileName);
        }
        productService.save(product);
        return "saveSuccess";
    }

    public String delete(){
        //删除数据库数据
        product = productService.findById(product.getPid());
        productService.delete(product);
        //删除图片
        String path = product.getImage();
        String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
        File file = new File(realPath);
        file.delete();
        return  "deleteSuccess";
    }

    public String edit(){
        product = productService.findById(product.getPid());
        List<CategorySecond> csList = categorySecondSerivce.findAll();
        ActionContext.getContext().getValueStack().set("csList",csList);
        return "editPage";
    }

    public String update() throws IOException {
        product.setPdate(new Date());

        //文件上传
        if (upload !=null){
            //删除原图片
            String path = product.getImage();
            String realPath_1 = ServletActionContext.getServletContext().getRealPath("/"+path);
            File file = new File(realPath_1);
            file.delete();
            //文件上传
            //1、获取文件上传的磁盘路径
            String realPath =   ServletActionContext.getServletContext().getRealPath("/products");
            File diskFile = new File(realPath+ "//" + uploadFileName);
            FileUtils.copyFile(upload,diskFile);
            product.setImage("products/"+uploadFileName);
        }
        productService.update(product);

        return "updateSuccess";
    }
}
