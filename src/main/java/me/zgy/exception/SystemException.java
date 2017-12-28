package me.zgy.exception;

public class SystemException extends RuntimeException {

    private String code;

    private static final long serialVersionUID = 1133605694607523034L;

    public SystemException(Exception e) {
        super(e);
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(String code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
