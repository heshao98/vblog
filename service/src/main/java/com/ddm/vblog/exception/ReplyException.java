package com.ddm.vblog.exception;

import lombok.Data;

/**
 * @Description
 * @Date:2019/3/12 14:39
 * @Author ddm
 **/
@Data
public class ReplyException extends BaseException {

    private int status = 200;

    public ReplyException() {
    }

    public ReplyException(String message, int status) {
        super(message);
        this.status = status;
    }

    public ReplyException(String message) {
        super(message);
    }

    public ReplyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReplyException(Throwable cause) {
        super(cause);
    }

    public ReplyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
