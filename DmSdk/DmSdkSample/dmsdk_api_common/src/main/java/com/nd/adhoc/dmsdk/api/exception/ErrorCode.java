package com.nd.adhoc.dmsdk.api.exception;

public class ErrorCode {

    /**
     * NO 无异常
     */
    public static  int DEFAULT_ERROR_CODE=0;

    /**
     * 构造函数中的成员变量未被初始化，获取为NULL。
     */
    public static  final int ERROR_CODE_CONSTRUCT_NO_INSTANCE=-1;
    /**
     * 激活失败，context为NULL
     */
    public static  final int ERROR_CODE_LICENSE_FAILURE=-2;
}
