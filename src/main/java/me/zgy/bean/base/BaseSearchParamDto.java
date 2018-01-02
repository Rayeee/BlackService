package me.zgy.bean.base;

import java.util.Date;

/**
 * Created by Rayee on 2017/12/29.
 */
public class BaseSearchParamDto {

    private String query;//查询语句
    private Date startDate;//起始时间
    private Date endDate;//结束时间
    private Integer pageSize;


    public BaseSearchParamDto() {
    }

    public BaseSearchParamDto(String query) {
        this.query = query;
    }

    public BaseSearchParamDto(String query, Date startDate, Date endDate) {
        this.query = query;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
