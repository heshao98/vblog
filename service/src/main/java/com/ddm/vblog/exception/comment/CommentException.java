package com.ddm.vblog.exception.comment;

import com.ddm.vblog.exception.BaseException;

/**
 * @Description 评论异常
 * @Date:2019/3/13 12:15
 * @Author ddm
 **/
public class CommentException extends BaseException {

    private static final long serialVersionUID = -7482893367270488685L;

    private int status = 200;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CommentException() {
    }

    public CommentException(String message, int status) {
        super(message);
        this.status = status;
    }

    public CommentException(String message) {
        super(message);
    }

    public CommentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentException(Throwable cause) {
        super(cause);
    }

    public CommentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
