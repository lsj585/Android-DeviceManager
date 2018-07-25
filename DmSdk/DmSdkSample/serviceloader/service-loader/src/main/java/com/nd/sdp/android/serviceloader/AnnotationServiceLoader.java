package com.nd.sdp.android.serviceloader;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;

import com.nd.sdp.android.serviceloader.internal.IServiceProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 程序编译时注入的service脚本
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@Keep
public class AnnotationServiceLoader<S> implements ServiceLoader<S> {

    private final Class<S> service;

    private Collection<Class<? extends S>> classes;

    private LazyIterator lookupIterator;

    private AnnotationServiceLoader(Class<S> svc) {
        service = requireNonNull(svc, "Service interface cannot be null");
    }

    public void reload() {
        try {
            IServiceProvider<S> serviceProvider = getServiceProvider();
            classes = serviceProvider.provide();
            lookupIterator = new LazyIterator(service);
        } catch (Exception e) {
            throw new ServiceConfigurationError(service.getName() + ": " + e.getMessage());
        }
    }

    private static void fail(Class<?> service, String msg, Throwable cause)
            throws ServiceConfigurationError {
        throw new ServiceConfigurationError(service.getName() + ": " + msg,
                cause);
    }

    @Override
    public Iterator<S> iterator() {
        init();
        return new Iterator<S>() {

            public boolean hasNext() {
                return lookupIterator.hasNext();
            }

            public S next() {
                return lookupIterator.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    private void init() {
        if (lookupIterator == null) {
            reload();
        }
    }

    // Private inner class implementing fully-lazy provider lookup
    //
    private class LazyIterator
            implements Iterator<S> {

        Class<S> service;
        Iterator<Class<? extends S>> iterator;

        private LazyIterator(Class<S> service) {
            this.service = service;
            iterator = classes.iterator();
        }

        private S nextService() throws RuntimeException {
            if (!iterator.hasNext())
                throw new NoSuchElementException();
            Class<? extends S> c = iterator.next();
            if (!service.isAssignableFrom(c)) {
                ClassCastException cce = new ClassCastException(
                        service.getCanonicalName() + " is not assignable from " + c.getCanonicalName());
                fail(service,
                        "Provider " + c.getName() + " not a subtype", cce);
            }
            try {
                return service.cast(c.newInstance());
            } catch (Exception e) {
                throw new ServiceConfigurationError(e.getMessage());
            }
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public S next() {
            return nextService();
        }

    }

    public static <S> ServiceLoader<S> load(Class<S> service) {
        return new AnnotationServiceLoader<>(service);
    }

    private static <T> T requireNonNull(T obj, @SuppressWarnings("SameParameterValue") String message) {
        if (obj == null)
            throw new NullPointerException(message);
        return obj;
    }

    @NonNull
    private IServiceProvider<S> getServiceProvider() throws IllegalAccessException, InstantiationException {
        try {
            String packageName = service.getPackage().getName();
            @SuppressWarnings("unchecked") Class<IServiceProvider<S>> serviceProviderClass = (Class<IServiceProvider<S>>) Class.forName(packageName + ".Provider_" + service.getSimpleName());
            return serviceProviderClass.newInstance();
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            Logger.getLogger("ServiceLoader")
                    .log(Level.ALL, "No Provider for" + service.getSimpleName() + ", Please Check whether annotationProcessor for app used");
            return new EmptyServiceProvider<>();
        }
    }

    private static class EmptyServiceProvider<S> implements IServiceProvider<S> {

        @Override
        public Collection<Class<? extends S>> provide() {
            return new ArrayList<>();
        }

    }

}
