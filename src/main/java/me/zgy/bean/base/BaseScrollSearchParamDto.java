package me.zgy.bean.base;

import java.util.Date;

/**
 * Created by Rayee on 2017/12/29.
 */
public class BaseScrollSearchParamDto extends BaseSearchParamDto {

    private String scrollId;//scroll查询Id

    public BaseScrollSearchParamDto() {
    }

    public BaseScrollSearchParamDto(String query, Date startDate, Date endDate, Integer pageSize) {
        super(query, startDate, endDate);
        setPageSize(pageSize);
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

}
