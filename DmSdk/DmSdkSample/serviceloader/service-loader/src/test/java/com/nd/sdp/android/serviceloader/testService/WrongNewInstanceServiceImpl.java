package com.nd.sdp.android.serviceloader.testService;

import com.nd.sdp.android.serviceloader.annotation.Service;

/**
 * WrongNewInstanceServiceImpl
 * Created by Young on 2018/5/8.
 */
@Service(IWrongNewInstanceService.class)
public class WrongNewInstanceServiceImpl implements IWrongNewInstanceService {

    public WrongNewInstanceServiceImpl(String test) {
    }

}
