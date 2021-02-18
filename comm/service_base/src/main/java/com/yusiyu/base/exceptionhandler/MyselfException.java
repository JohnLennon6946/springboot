package com.yusiyu.base.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyselfException extends RuntimeException{
    private Integer code;
    private String msg;

}
