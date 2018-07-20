package com.nd.adhoc.dmsdk.exception;

/**
 * 此类暂时不用
 */
public class ErrorCode {

    /**
     * 构造函数中的成员变量未被初始化，获取为NULL。
     */
    public static  final int ERROR_CODE_CONSTRUCT_NO_INSTANCE=-1;
    /**
     * 激活失败，context为NULL
     */
    public static  final int ERROR_CODE_LICENSE_FAILURE=-2;

    public static final int ERROR_CODE_UN_SUPPORT=-3;
    /**
     * 操作异常-关闭失败
     */
    public static final int DEFAULT_OPERATION_ERROR=1;

}
