package com.nd.sdp.android.serviceloader.testService;

import com.nd.sdp.android.serviceloader.annotation.ServiceName;

/**
 * TestServiceImplA
 * Created by Young on 2018/5/8.
 */
@ServiceName("com.nd.sdp.test.service.b")
public class TestServiceImplB implements ITestService {
    @Override
    public String test() {
        return "b";
    }
}
