package com.yusiyu.base.exceptionhandler;



import com.yusiyu.commutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //指定出现什么异常调用该方法,下面是所有异常都执行
    @ExceptionHandler(Exception.class)
    @ResponseBody   //为了返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常");
    }

    //自定义异常
    @ExceptionHandler(MyselfException.class)
    @ResponseBody   //为了返回数据
    public R error(MyselfException e) {
        log.error(e.getMsg());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
