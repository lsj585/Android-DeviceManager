package com.nd.sdp.android.serviceloader.testService;

import com.nd.sdp.android.serviceloader.annotation.Service;

/**
 * Created by Young on 2018/5/8.
 */
@Service(ICastTestService.class)
public class CastTestServiceImpl implements IWrongProviderService {

    @Override
    public String test() {
        return null;
    }
}
