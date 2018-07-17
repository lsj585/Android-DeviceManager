package com.nd.sdp.android.serviceloader.testService;

import com.nd.sdp.android.serviceloader.internal.IServiceProvider;

import java.util.Arrays;
import java.util.Collection;

/**
 * Provider_ITestService
 * <p>
 * this will be generate by annotation processor app
 * Created by Young on 2018/5/8.
 */

public class Provider_ITestService implements IServiceProvider<ITestService> {

    @Override
    public Collection<Class<? extends ITestService>> provide() {
        return Arrays.asList(TestServiceImplA.class, TestServiceImplB.class);
    }

}
