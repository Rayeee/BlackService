package me.zgy.service.es.search;

import com.google.common.collect.Lists;
import me.zgy.cst.BlackCst;
import me.zgy.es.ESSearchResult;
import me.zgy.es.index.base.BlackIndex;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Rayee on 2018/1/2.
 */
@Service
public class BlackIndexSearchService extends BaseSearchService<BlackIndex> {

    public ESSearchResult<BlackIndex> findBySearchParam(String query) {
        //构建查询Index
        List<String> indexes = Lists.newArrayList(BlackCst.BLACK_INDEX_NAME);

        return super.fetchBySearchParam(indexes, BlackCst.BLACK_INDEX_TYPE, query, BlackIndex.class);
    }

}
