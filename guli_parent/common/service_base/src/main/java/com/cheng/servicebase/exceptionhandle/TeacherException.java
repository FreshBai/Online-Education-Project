package com.cheng.servicebase.exceptionhandle;

import lombok.Data;

/**
 * Created by Administrator on 2021/5/5.
 **/
@Data
public class TeacherException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Integer errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public TeacherException() {
        super();
    }

    public TeacherException(BaseErrorInfoInterface errorInfoInterface) {
        //super(errorInfoInterface.getResultCode());
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public TeacherException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {
        //super(errorInfoInterface.getResultCode(), cause);
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public TeacherException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public TeacherException(Integer errorCode, String errorMsg) {
        //super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public TeacherException(Integer errorCode, String errorMsg, Throwable cause) {
        //super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public String getMessage() {
        return errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
