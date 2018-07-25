package com.nd.adhoc.dmsdk.exception;
public class DeviceManagerUnsupportException extends Exception {


    private int mErrorCode;

    public DeviceManagerUnsupportException(int errorCode) {

        this.mErrorCode = errorCode;
    }

    @Override
    public String getMessage() {
        switch (mErrorCode) {
            case ErrorCode.ERROR_CODE_UN_SUPPORT:
                return "未找到与该方法匹配的API";
            case ErrorCode.DEFAULT_OPERATION_ERROR:
                return "操作失败";
        }
        return "";
    }


    public int getErrorCode(){
        return mErrorCode;
    }
}
