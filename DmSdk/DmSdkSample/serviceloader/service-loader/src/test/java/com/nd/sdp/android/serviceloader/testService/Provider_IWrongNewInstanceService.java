package com.nd.sdp.android.serviceloader.testService;

import com.nd.sdp.android.serviceloader.internal.IServiceProvider;

import java.util.Arrays;
import java.util.Collection;

/**
 * Provider_IWrongNewInstanceService
 * <p>
 * this will be generate by annotation processor app
 * Created by Young on 2018/5/8.
 */

public class Provider_IWrongNewInstanceService implements IServiceProvider<IWrongNewInstanceService> {

    @Override
    public Collection<Class<? extends IWrongNewInstanceService>> provide() {
        return Arrays.asList(WrongNewInstanceServiceImpl.class);
    }

}
