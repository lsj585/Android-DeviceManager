package com.nd.sdp.android.serviceloader.internal;

import java.util.Collection;

public interface IServiceProvider<S> {

    Collection<Class<? extends S>> provide();

}
