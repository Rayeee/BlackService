package me.zgy.exception;

public class ServiceException extends Exception {
    private String code;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        this(message);
        this.setCode(code);
    }

    public ServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
