//package me.zgy.test;
//
//import io.searchbox.core.Search;
//import io.searchbox.core.SearchResult;
//import me.zgy.es.ESClientFactory;
//import me.zgy.exception.ServiceException;
//import me.zgy.routing.DataSource;
//import me.zgy.service.es.index.UserInfoIndexService;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.junit.Test;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//
///**
// * Created by Rayee on 2017/12/28.
// */
//public class ESTest extends BaseTest {
//
//    @Resource
//    private UserInfoIndexService userInfoIndexService;
//
//    @Test
//    @DataSource("dataSource1")
//    public void index() throws ServiceException {
//        userInfoIndexService.index(48l);
//    }
//
//    @Test
//    @DataSource("dataSource1")
//    public void batchIndex() throws ServiceException, IOException {
//        userInfoIndexService.batchIndex("上海");
//    }
//
//    @Test
//    public void query() throws IOException {
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.matchQuery("address", "上海"));
//        Search search = new Search.Builder(builder.toString()).addIndex("ix_user_info").addType("user_info").build();
//        SearchResult result = ESClientFactory.getClient().execute(search);
//    }
//
//}
