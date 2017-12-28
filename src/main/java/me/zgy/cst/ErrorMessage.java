package me.zgy.cst;

import me.zgy.exception.ServiceException;
import me.zgy.exception.SystemException;

/**
 * Created by Rayee on 2017/12/28.
 */
public enum ErrorMessage {

    es_do_index_sys_error("ES_DO_INDEX_SYS_ERROR", "ES索引数据失败"),

    redis_lock_sys_err("REDIS_LOCK_SYS_ERR", "Redis锁 系统异常");

    private final String code;
    private final String msg;

    ErrorMessage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceException getServiceException() {
        return new ServiceException(code, msg);
    }

    public ServiceException getServiceException(Throwable e) {
        return new ServiceException(code, msg, e);
    }

    public SystemException getSystemException() {
        return new SystemException(code, msg);
    }

    public SystemException getSystemException(Throwable e) {
        return new SystemException(code, msg, e);
    }

}
