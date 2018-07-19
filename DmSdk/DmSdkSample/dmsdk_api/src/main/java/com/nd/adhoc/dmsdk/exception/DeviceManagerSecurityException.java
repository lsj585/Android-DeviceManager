package com.nd.adhoc.dmsdk.exception;
public class DeviceManagerSecurityException extends Exception {


    private int mErrorCode;

    public DeviceManagerSecurityException(int errorCode) {

        this.mErrorCode = errorCode;
    }

    @Override
    public String getMessage() {
        switch (mErrorCode) {
            case ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE:
                return "构造函数中的成员变量未被初始化，获取为NULL";
            case ErrorCode.DEFAULT_OPERATION_ERROR:
                return "操作失败";
        }
        return "";
    }


    public int getErrorCode(){
        return mErrorCode;
    }
}
