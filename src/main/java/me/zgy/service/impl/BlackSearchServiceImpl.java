package me.zgy.service.impl;

import me.zgy.api.BlackSearchService;
import me.zgy.bean.dto.TBlack;
import me.zgy.bean.param.BlackSearchParam;
import me.zgy.es.ESSearchResult;
import me.zgy.es.index.base.BlackIndex;
import me.zgy.query.BlackQueryBuilder;
import me.zgy.service.es.search.BlackIndexSearchService;
import me.zgy.utils.BlackBuilds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Rayee on 2018/1/2.
 */
@Service
public class BlackSearchServiceImpl implements BlackSearchService {

    @Resource
    private BlackIndexSearchService blackIndexSearchService;

    @Override
    public List<TBlack> query(BlackSearchParam param) {
        //构造搜索语句
        String query = BlackQueryBuilder.buildQuery(param);
        ESSearchResult<BlackIndex> result = blackIndexSearchService.findBySearchParam(query);
        return BlackBuilds.buildBlacks(result);
    }
}
