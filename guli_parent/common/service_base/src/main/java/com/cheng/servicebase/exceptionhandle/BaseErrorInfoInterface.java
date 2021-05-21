package com.cheng.servicebase.exceptionhandle;

/**
 * Created by Administrator on 2021/5/5.
 **/
public interface BaseErrorInfoInterface {
    /** 错误码*/
    Integer getResultCode();

    /** 错误描述*/
    String getResultMsg();
}
