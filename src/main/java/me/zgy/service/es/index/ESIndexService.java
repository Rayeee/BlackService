package me.zgy.service.es.index;

import io.searchbox.core.Bulk;
import io.searchbox.core.BulkResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import me.zgy.cst.ErrorMessage;
import me.zgy.es.ESClientFactory;
import me.zgy.es.index.base.BaseIndex;
import me.zgy.exception.ServiceException;
import me.zgy.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Rayee on 2017/12/28.
 */
public class ESIndexService<T extends BaseIndex> {

    private static final Logger logger = LoggerFactory.getLogger(ESIndexService.class);

    protected void index(T entity, String indexName, String indexType) throws ServiceException {
        logger.debug("开始建索引indexName={},indexType={}, entity={}", indexName, indexType, JsonUtils.toJson(entity));

        long start = System.currentTimeMillis();

        Index index = new Index.Builder(entity).index(indexName).type(indexType).build();
        DocumentResult result = null;

        try {
            result = ESClientFactory.getClient().execute(index);
        } catch (Exception e) {
            logger.error("创建ES索引【失败】index={}", JsonUtils.toJson(index), e);
            throw ErrorMessage.es_do_index_sys_error.getSystemException(e);
        }

        if (result == null) {
            logger.error("创建ES索引【失败】执行结果为空index={}", JsonUtils.toJson(index));
            throw ErrorMessage.es_do_index_sys_error.getSystemException();
        } else if (result.isSucceeded()) {
            System.out.println("创建 success");
            logger.info("创建ES索引【成功】index={},type={},id={},doc={}", result.getIndex(), result.getType(), result.getId(), result.getJsonString());
        } else {
            logger.error("创建ES索引【失败】responseCode={},errorMessage={},index={}", result.getResponseCode(), result.getErrorMessage(), JsonUtils.toJson(index));
            throw ErrorMessage.es_do_index_sys_error.getSystemException();
        }
    }


//    public void batchIndex(List<T> entities, String indexName, String indexType) throws IOException {
//        logger.debug("开始批量建索引indexName={},indexType={}, entity={}", indexName, indexType, JsonUtils.toJson(entities));
//        Bulk.Builder bulkBuilder = new Bulk.Builder();
//        entities.forEach(e -> {
//            Index index = new Index.Builder(e).index("ix_user_info").type("user_info").build();
//            bulkBuilder.addAction(index);
//        });
//        BulkResult result = ESClientFactory.getClient().execute(bulkBuilder.build());
//        if (result == null) {
//            logger.error("创建ES索引【失败】执行结果为空index={}", JsonUtils.toJson(entities));
//            throw ErrorMessage.es_do_index_sys_error.getSystemException();
//        } else if (result.isSucceeded()) {
//            System.out.println("创建 success");
//            logger.info("创建ES索引【成功】index={},type={},id={},doc={}", result.getIndex(), result.getType(), result.getId(), result.getJsonString());
//        } else {
//            logger.error("创建ES索引【失败】responseCode={},errorMessage={},index={}", result.getResponseCode(), result.getErrorMessage(), JsonUtils.toJson(index));
//            throw ErrorMessage.es_do_index_sys_error.getSystemException();
//        }
//    }

}
