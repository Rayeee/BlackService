package me.zgy.query;

import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Created by IFT8 on 16/9/11.
 * QueryHandlerUtils_v2
 */
public class QueryBuilders extends BoolQueryBuilder {

    public SearchSourceBuilder build() {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        if (this.hasClauses()) {
            sourceBuilder.query(this);
        }
        return sourceBuilder;
    }

    private ScriptQueryBuilder makeScriptBuilder(String name, String script) {
        ScriptQueryBuilder scriptQueryBuilder = scriptQuery(ScriptHelper.buildScript(script));
        if (!StringUtils.isEmpty(name)) {
            scriptQueryBuilder.queryName(name);
        }
        return scriptQueryBuilder;
    }

    public QueryBuilders scriptBuilder(String name, String script) {
        if (StringUtils.isEmpty(script)) {
            return this;
        }
        ScriptQueryBuilder scriptQueryBuilder = makeScriptBuilder(name, script);
        this.filter(scriptQueryBuilder);
        return this;
    }

    public QueryBuilders scriptBuilder(String script) {
        return scriptBuilder(null, script);
    }

    /**
     * 过滤结果搜索构造器
     *
     * @param resultIncludes 结果包含字段 为null全包含
     * @param resultexcludes 结果例外字段 为null均不例外
     */
    public SearchSourceBuilder buildWithResultFilter(String[] resultIncludes, String[] resultexcludes) {
        return SearchSourceBuilder.searchSource().fetchSource(resultIncludes, resultexcludes).query(this);
    }

    public SearchSourceBuilder buildWithResultFilter(List<String> resultIncludes, List<String> resultexcludes) {
        String[] resultIncludesArr = null;
        String[] resultexcludesArr = null;
        if (!CollectionUtils.isEmpty(resultIncludes)) {
            resultIncludesArr = resultIncludes.toArray(new String[]{});
        }
        if (!CollectionUtils.isEmpty(resultexcludes)) {
            resultexcludesArr = resultexcludes.toArray(new String[]{});
        }
        return SearchSourceBuilder.searchSource().fetchSource(resultIncludesArr, resultexcludesArr).query(this);
    }

    /**
     * 时间段收缩构造器(左闭右开)
     *
     * @param field     时间字段名
     * @param startDate 起始时间
     * @param endDate   结束时间
     */
    public QueryBuilders timeFilterBuilder(String field, Date startDate, Date endDate) {
        return rangeBuilder(field, startDate == null ? null : startDate.getTime(), endDate == null ? null : endDate.getTime(), true, false);
    }

    public QueryBuilders rangeBuilder(String field, Long start, Long end, boolean includeLower, boolean includeUpper) {
        if (start == null && end == null) {
            return this;
        }
        RangeQueryBuilder rangeQueryBuilder = rangeQuery(field);
        if (start != null) {
            rangeQueryBuilder.from(start);
        }
        if (end != null) {
            rangeQueryBuilder.to(end);
        }
        rangeQueryBuilder.includeLower(includeLower);
        rangeQueryBuilder.includeUpper(includeUpper);
        this.filter(rangeQueryBuilder);
        return this;
    }

    public QueryBuilders rangeBuilder(String field, Long start, Long end) {
        return rangeBuilder(field, start, end, true, true);
    }

    public QueryBuilders nestedTimeFilterBuilder(String path, String field, Date startDate, Date endDate) {
        if (startDate == null && endDate == null) {
            return this;
        }
        return nestedQueryBuilder(path, new QueryBuilders().timeFilterBuilder(field, startDate, endDate));
    }

    /**
     * 搜索构造器
     *
     * @param field      索引字段名
     * @param collection 搜索的集合
     */
    public QueryBuilders batchFilter(String field, Collection collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return this;
        }
        this.filter(termsQuery(field, collection));
        return this;
    }

    public QueryBuilders filter(String field, Object value) {
        if (value == null) {
            return this;
        }
        this.filter(termQuery(field, value));
        return this;
    }

    /**
     * 构造以索引Ids查询语句
     *
     * @param indexIds       对应索引的_id
     * @param indexType      索引类型
     * @param resultIncludes 结果包含字段 为null全包含
     * @param resultexcludes 结果例外字段 为null均不例外
     */
    public String buildIdsQuery(List<String> indexIds, String indexType, String[] resultIncludes, String[] resultexcludes, int size) {
        return buildWithResultFilter(resultIncludes, resultexcludes)
                //索引的_id
                .query(new IdsQueryBuilder(indexType).addIds(indexIds)).size(size).toString();
    }

    /**
     * 内嵌对象查询(集合)
     */
    public QueryBuilders nestedQueryBuilder(String path, String field, Collection collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return this;
        }
        nestedQueryBuilder(path, termsQuery(field, collection));
        return this;
    }

    public QueryBuilders nestedQueryBuilder(String path, QueryBuilder queryBuilder) {
        if (queryBuilder == null) {
            return this;
        }
        NestedQueryBuilder nestedQueryBuilder = new NestedQueryBuilder(path, queryBuilder);
        this.filter(nestedQueryBuilder);
        return this;
    }

    public QueryBuilders nestedScriptBuilder(String path, String name, String script) {
        if (StringUtils.isEmpty(script)) {
            return this;
        }
        nestedQueryBuilder(path, makeScriptBuilder(name, script));
        return this;
    }

    public QueryBuilders nestedScriptBuilder(String path, String script) {
        nestedScriptBuilder(path, null, script);
        return this;
    }

    /**
     * 内嵌对象查询(单个)
     */
    public QueryBuilders nestedQueryBuilder(String path, String field, Object value) {
        if (value == null) {
            return this;
        }
        nestedQueryBuilder(path, termQuery(field, value));
        return this;
    }

    public QueryBuilders map2QueryBuilder(Map<String, Object> fieldMap) {
        QueryBuilders boolQueryBuilder = new QueryBuilders();
        for (Map.Entry<String, Object> entry : fieldMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                if (!StringUtils.isEmpty(entry.getValue())) {
                    boolQueryBuilder.filter(matchQuery(entry.getKey(), entry.getValue()));
                }
            } else {
                if (entry.getValue() != null) {
                    boolQueryBuilder.filter(termQuery(entry.getKey(), entry.getValue()));
                }
            }
        }
        return this;
    }

    /**
     * 根据Map参数构造搜索构造器
     *
     * @param fieldMap  key:查询字段
     *                  value: 查询值
     * @param timeField 时间字段名
     * @param startDate 起始时间
     * @param endDate   结束时间
     */
    public QueryBuilders map2QueryBuilder(Map<String, Object> fieldMap, String timeField, Date startDate, Date endDate) {
        QueryBuilders boolQueryBuilder = timeFilterBuilder(timeField, startDate, endDate);
        if (CollectionUtils.isEmpty(fieldMap)) {
            return boolQueryBuilder;
        }
        for (Map.Entry<String, Object> entry : fieldMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                if (!StringUtils.isEmpty(entry.getValue())) {
                    boolQueryBuilder.filter(matchQuery(entry.getKey(), entry.getValue()));
                }
            } else {
                if (entry.getValue() != null) {
                    boolQueryBuilder.filter(termQuery(entry.getKey(), entry.getValue()));
                }
            }
        }
        return this;
    }

    public QueryBuilders mustNotBuilder(QueryBuilder queryBuilder) {
        if (queryBuilder == null) {
            return this;
        }
        this.filter(new BoolQueryBuilder().mustNot(queryBuilder));
        return this;
    }

    /**
     * 字段不存在过滤器
     */
    public QueryBuilders missingBuilder(List<String> fields) {
        if (fields == null) {
            return this;
        }
        for (String field : fields) {
            mustNotBuilder(existsQuery(field));
        }
        return this;
    }

    public QueryBuilders mustNotBuilder(String field, Collection collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return this;
        }
        mustNotBuilder(field, termsQuery(field, collection));
        return this;
    }

    public QueryBuilders mustNotBuilder(String field, Object value) {
        if (value == null) {
            return this;
        }
        mustNotBuilder(termQuery(field, value));
        return this;
    }

    public QueryBuilders existsBuilder(String field) {
        if (field == null) {
            return this;
        }
        this.filter(existsQuery(field));
        return this;
    }

    public QueryBuilders nestedExistsBuilder(String path, String field) {
        if (field == null) {
            return this;
        }
        nestedQueryBuilder(path, existsQuery(field));
        return this;
    }

    public QueryBuilders missingBuilder(String field) {
        if (field == null) {
            return this;
        }
        mustNotBuilder(existsQuery(field));
        return this;
    }

    public QueryBuilders nestedMissingBuilder(String path, String field) {
        if (field == null) {
            return this;
        }
        nestedQueryBuilder(path, new BoolQueryBuilder().mustNot(existsQuery(field)));
        return this;
    }
}