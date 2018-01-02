package me.zgy.service;

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

    public void index(T entity, String indexName, String indexType) throws ServiceException {
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

}
