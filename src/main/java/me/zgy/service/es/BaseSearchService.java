package me.zgy.service.es;

import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchScroll;
import io.searchbox.params.Parameters;
import me.zgy.cst.ErrorMessage;
import me.zgy.es.ESClientFactory;
import me.zgy.es.ESHandler;
import me.zgy.es.ESSearchResult;

import java.io.IOException;
import java.util.List;

/**
 * Created by Rayee on 2017/12/29.
 */
public class BaseSearchService<T> {

    private static final JestClient client = ESClientFactory.getClient();
    private static final String SCROLL_CACHE_TIME = "5m";//scroll结果缓存5分钟,足以
    private static final int DEFAULT_RESULT_SIZE = 10;//默认返回10个

    /**
     * 通用搜索
     *
     * @param indexs     查询的索引列表
     * @param type       文档类型
     * @param query      查询语句
     * @param sourceType 结果类
     */
    protected ESSearchResult<T> fetchBySearchParam(List<String> indexs, String type, String query, Class<T> sourceType) {
        //构造查询参数
        Search searchParam = buildBaseSearchBuilder(indexs, type, query).build();
        //搜索
        SearchResult searchResult = executeSearch(searchParam);
        //解析结果
        return parseResult(searchResult, sourceType);
    }

    /**
     * 1.游标式滚动查询 (返回scrollId供searchScroll继续查询)
     * 降低深度分页的高损耗
     *
     * @param indexs     查询的索引列表
     * @param type       文档类型
     * @param query      查询语句
     * @param pageSize   分页大小
     * @param sourceType 结果类
     * @return 返回前部分结果, 并scrollId供searchScroll继续查询
     */
    protected ESSearchResult<T> getSearchScrollIdAndTopResult(List<String> indexs, String type, String query, Integer pageSize, Class<T> sourceType) {
        //构造查询参数
        Search searchParam = buildScrollSearchParam(indexs, type, query, pageSize);
        //搜索
        SearchResult searchResult = executeSearch(searchParam);
        //解析结果
        return parseResult(searchResult, sourceType);
    }

    /**
     * 2.游标式滚动查询 (getSearchScrollId后续获取数据方法)
     *
     * @param scrollId   查询的key(getSearchScrollId返回的)
     * @param sourceType 结果类
     */
    protected ESSearchResult<T> searchScroll(String scrollId, Class<T> sourceType) {
        SearchScroll scroll = new SearchScroll.Builder(scrollId, SCROLL_CACHE_TIME)//结果保存多久
                .build();

        JestResult jestResult = executeSearch(scroll);
        //解析结果
        return parseResult(jestResult, sourceType);
    }

    protected ESSearchResult<T> parseResult(JestResult searchResult, Class<T> sourceType) {
        return ESHandler.parseResult(searchResult, sourceType);
    }

    /* 默认搜索设置 */
    private Search.Builder buildBaseSearchBuilder(List<String> indexs, String type, String query) {
        return new Search.Builder(query)
                .addIndex(indexs)//搜索索引
                .addType(type);
    }

    /* 构建搜索 */
    private Search buildScrollSearchParam(List<String> indexs, String type, String query, Integer pageSize) {
        if (pageSize == null) {
            pageSize = DEFAULT_RESULT_SIZE;
        }
        //默认设置
        Search.Builder searchBuilder = buildBaseSearchBuilder(indexs, type, query);
        searchBuilder.setParameter(Parameters.SIZE, pageSize)//分页大小
                .setParameter(Parameters.SCROLL, SCROLL_CACHE_TIME);//结果保存多久

        //构造搜索
        return searchBuilder.build();
    }

    private <R extends JestResult> R executeSearch(Action<R> action) {
        try {
            return client.execute(action);
        } catch (IOException e) {
            throw ErrorMessage.es_do_index_sys_error.getSystemException();
        }
    }

}
