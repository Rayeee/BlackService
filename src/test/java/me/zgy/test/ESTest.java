package me.zgy.test;

import me.zgy.api.BlackSearchService;
import me.zgy.bean.dto.TBlack;
import me.zgy.bean.param.BlackSearchParam;
import me.zgy.exception.ServiceException;
import me.zgy.service.es.index.BlackIndexService;
import me.zgy.service.es.search.BlackIndexSearchService;
import me.zgy.utils.JsonUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Rayee on 2017/12/28.
 */
public class ESTest extends BaseTest {

    @Resource
    private BlackIndexService blackIndexService;

    @Resource
    private BlackSearchService blackSearchService;

    @Test
    public void test01() throws ServiceException {
        blackIndexService.index(BlackSearchParam.builder().creditNo("320681190000000000").build());
    }

    @Test
    public void test02() throws ServiceException {
        List<TBlack> blacks = blackSearchService.query(BlackSearchParam.builder().creditNo("320681190000000000").build());
        System.out.println(JsonUtils.toJson(blacks));
    }

}
