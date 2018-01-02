package me.zgy.test;

import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import me.zgy.bean.param.BlackSearchParam;
import me.zgy.es.ESClientFactory;
import me.zgy.exception.ServiceException;
import me.zgy.routing.DataSource;
import me.zgy.service.es.index.BlackIndexService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Rayee on 2017/12/28.
 */
public class ESTest extends BaseTest {

    @Resource
    private BlackIndexService blackIndexService;

    @Test
    public void test01() throws ServiceException {
        blackIndexService.index(BlackSearchParam.builder().creditNo("320681190000000000").build());
    }

}
