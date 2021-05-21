package com.cheng.servicebase.exceptionhandle;


import com.cheng.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2021/5/4.
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R Exception(Exception e){
        log.error("执行了全局异常处理..",e);
        log.info(e.getMessage());
        return R.error();
    }

    /**
     * 处理自定义的业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(TeacherException.class)
    @ResponseBody
    public R TeacherException(TeacherException e){
        log.error("执行了讲师异常处理..",e);
        log.info(e.getMessage());
        return R.error().message(e.errorMsg).code(e.errorCode);
    }
    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public R exceptionHandler(HttpServletRequest req, NullPointerException e){

        return R.error().message(e.getMessage());
    }

}


