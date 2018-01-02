package me.zgy.query;

import me.zgy.bean.param.BlackSearchParam;
import me.zgy.utils.FieldHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.zgy.cst.BlackCst.INDEX_CREDIT_NO;

/**
 * Created by Rayee on 2018/1/2.
 */
public class BlackQueryBuilder {

    //TODO
    public static String buildQuery(BlackSearchParam param) {
        QueryBuilders queryBuilders = buildShippingOrderQueryBuilder(param);
        //包含字段
        List<String> includeField = FieldHelper.getBlackBaseFiled();
        //trackingId
        return queryBuilders
                //返回值
                .buildWithResultFilter(includeField, null)
                .from(param.getOffset())
                .size(param.getLimit())
                .toString();
    }

    static QueryBuilders buildShippingOrderQueryBuilder(BlackSearchParam param) {
        QueryBuilders queryBuilders = new QueryBuilders();
        //root文档
        filterRootDoc(queryBuilders, param);
        return queryBuilders;
    }

    /**
     * 过滤root文档
     */
    private static void filterRootDoc(QueryBuilders queryBuilders, BlackSearchParam param) {
        //过滤root文档的简单字段信息(单个)
        simpleFilterRootDoc(queryBuilders, param);
    }

    private static void simpleFilterRootDoc(QueryBuilders queryBuilders, BlackSearchParam param) {
        Map<String, Object> fieldMap = new HashMap<>();
        //身份证
        fieldMap.put(INDEX_CREDIT_NO, param);
        //根据创建时间和Map参数查
        queryBuilders.map2QueryBuilder(fieldMap);
    }


}
