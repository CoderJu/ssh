package cn.eleven.utils;

import java.util.List;

/**
 * Created by User on 2017/10/5.
 */
public class PageBean<E> {
    private int page; //当前页数
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private int limit;//每页显示记录数
    private List<E> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
}
