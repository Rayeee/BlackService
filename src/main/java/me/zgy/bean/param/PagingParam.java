package me.zgy.bean.param;

/**
 * Created by Rayee on 2018/1/2.
 */
public class PagingParam {

    private int pageIndex;

    private int pageSize = 10;

    public int getOffset() {
        return pageIndex * pageSize;
    }

    public int getLimit() {
        return pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}