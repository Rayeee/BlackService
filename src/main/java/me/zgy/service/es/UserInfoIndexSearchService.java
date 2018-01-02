package me.zgy.service.es;

import me.zgy.bean.base.BaseScrollSearchParamDto;
import me.zgy.es.ESHandler;
import me.zgy.es.ESSearchResult;
import me.zgy.es.index.UserInfoIndex;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static me.zgy.cst.BlackCst.USER_INFO_INDEX_PREFIX;
import static me.zgy.cst.BlackCst.USER_INFO_INDEX_TYPE;

/**
 * Created by Rayee on 2017/12/29.
 */
@Service
public class UserInfoIndexSearchService extends BaseSearchService<UserInfoIndex> {

    /**
     * 通用搜索
     *
     * @param query     查询语句
     * @param startDate 查询起始时间(运单创建时间)
     * @param endDate   查询截止时间(运单创建时间)
     */
    public ESSearchResult<UserInfoIndex> findBySearchParam(String query, Date startDate, Date endDate) {
        //构建查询Index
        List<String> indexs = ESHandler.getIndexNames(USER_INFO_INDEX_PREFIX, startDate, endDate);

        return super.fetchBySearchParam(indexs, USER_INFO_INDEX_TYPE, query, UserInfoIndex.class);
    }

    /**
     * 1.游标式滚动查询 (返回scrollId供searchScroll继续查询)并附带第一批结果
     * 降低深度分页的高损耗
     *
     * @param param 必填项
     *              param.query 查询语句
     *              param.startDate 查询起始时间
     *              param.endDate 查询截止时间
     *              可选项
     *              param.pageSize 默认10
     */
    public ESSearchResult<UserInfoIndex> getSearchScrollIdAndTopResult(BaseScrollSearchParamDto param) {
        //构建查询Index
        List<String> indexs = ESHandler.getIndexNames(USER_INFO_INDEX_PREFIX, param.getStartDate(), param.getEndDate());
        //查询
        return super.getSearchScrollIdAndTopResult(indexs, USER_INFO_INDEX_TYPE, param.getQuery(), param.getPageSize(), UserInfoIndex.class);
    }

    /**
     * 2.游标式滚动查询 (getSearchScrollId后续获取数据方法)
     *
     * @param scrollId 查询的key(getSearchScrollId返回的)
     */
    public ESSearchResult<UserInfoIndex> searchScroll(String scrollId) {
        //查询
        return super.searchScroll(scrollId, UserInfoIndex.class);
    }

}
