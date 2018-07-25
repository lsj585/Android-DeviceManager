package com.nd.sdp.android.serviceloader.testService;

import com.nd.sdp.android.serviceloader.internal.IServiceProvider;

import java.util.Arrays;
import java.util.Collection;

/**
 * Provider_IWrongProviderService
 * <p>
 * this will be generate by annotation processor app
 * this is wrong code since is only package access
 * Created by Young on 2018/5/8.
 */

class Provider_IWrongProviderService implements IServiceProvider<ITestService> {

    @Override
    public Collection<Class<? extends ITestService>> provide() {
        return Arrays.asList(TestServiceImplA.class, TestServiceImplB.class);
    }

}
