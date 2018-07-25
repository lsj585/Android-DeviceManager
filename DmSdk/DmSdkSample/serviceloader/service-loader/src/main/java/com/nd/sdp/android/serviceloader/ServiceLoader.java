package com.nd.sdp.android.serviceloader;

import java.util.Iterator;

public interface ServiceLoader<S> {

    /**
     * Return Impl Iterator(Lazy Iterator)
     *
     * @return Iterator
     */
    Iterator<S> iterator();

}
