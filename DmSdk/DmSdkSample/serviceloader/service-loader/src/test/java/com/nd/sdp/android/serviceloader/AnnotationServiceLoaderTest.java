package com.nd.sdp.android.serviceloader;

import com.nd.sdp.android.serviceloader.testService.ICastTestService;
import com.nd.sdp.android.serviceloader.testService.ITestEmptyService;
import com.nd.sdp.android.serviceloader.testService.ITestService;
import com.nd.sdp.android.serviceloader.testService.IWrongNewInstanceService;
import com.nd.sdp.android.serviceloader.testService.IWrongProviderService;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * AnnotationServiceLoaderTest
 * Created by Young on 2018/5/8.
 */
public class AnnotationServiceLoaderTest {

    @Test(expected = ServiceConfigurationError.class)
    public void wrongAccessProvider() throws Exception {
        AnnotationServiceLoader.load(IWrongProviderService.class).iterator();
    }

    @Test
    public void iterator() throws Exception {
        Iterator<ITestService> iterator = AnnotationServiceLoader.load(ITestService.class)
                .iterator();
        assertTrue(iterator.hasNext());
        ITestService next = iterator.next();
        assertEquals(next.test(), "a");
        assertTrue(iterator.hasNext());
        next = iterator.next();
        assertEquals(next.test(), "b");
    }

    @Test
    public void emptyImpl() throws Exception {
        Iterator<ITestEmptyService> iterator = AnnotationServiceLoader.load(ITestEmptyService.class)
                .iterator();
        assertTrue(!iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void emptyImpl2() throws Exception {
        Iterator<ITestEmptyService> iterator = AnnotationServiceLoader.load(ITestEmptyService.class)
                .iterator();
        iterator.next();
    }

    @Test
    public void load() throws Exception {
        ServiceLoader<ITestService> load = AnnotationServiceLoader.load(ITestService.class);
        assertNotNull(load);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void remove() throws Exception {
        AnnotationServiceLoader.load(ITestService.class).iterator().remove();
    }

    @Test(expected = NullPointerException.class)
    public void nullInterface() throws Exception {
        AnnotationServiceLoader.load(null);
    }

    @Test(expected = ServiceConfigurationError.class)
    public void castError() throws Exception {
        Iterator<ICastTestService> iterator = AnnotationServiceLoader.load(ICastTestService.class)
                .iterator();
        iterator.next();
    }

    @Test(expected = ServiceConfigurationError.class)
    public void newInstanceError() throws Exception {
        Iterator<IWrongNewInstanceService> iterator = AnnotationServiceLoader.load(IWrongNewInstanceService.class)
                .iterator();
        iterator.next();
    }

}