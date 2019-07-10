package org.analyzer.console.exception;

public class NotAllowedException extends RuntimeException{
    static final long serialVersionUID  =1L;
    
    public NotAllowedException() {
        super();
    }

    public NotAllowedException(String message) {
        super(message);
    }

    public NotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAllowedException(Throwable cause) {
        super(cause);
    }

    protected NotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
