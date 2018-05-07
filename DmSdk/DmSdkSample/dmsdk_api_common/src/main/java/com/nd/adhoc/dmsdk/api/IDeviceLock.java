package com.nd.adhoc.dmsdk.api;

public interface IDeviceLock {

	boolean lock();

	boolean unlock();

	boolean isLock();

    void release();
}
