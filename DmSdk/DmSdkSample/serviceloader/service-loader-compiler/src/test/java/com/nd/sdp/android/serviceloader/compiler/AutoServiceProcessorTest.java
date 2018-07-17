package com.nd.sdp.android.serviceloader.compiler;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import javax.tools.JavaFileObject;

import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.Compiler.javac;

/**
 * Created by Young on 2018/5/8.
 */
public class AutoServiceProcessorTest {

    @Test
    public void process() throws Exception {
        JavaFileObject fileObject = JavaFileObjects.forResource("com/nd/sdp/android/serviceloader/compiler/test/TestServiceImpl.java");
        JavaFileObject fileObject2 = JavaFileObjects.forResource("com/nd/sdp/android/serviceloader/compiler/test/TestServiceImpl2.java");
        Compilation compile = javac()
                .withProcessors(new AutoServiceProcessor())
                .compile(fileObject, fileObject2);
        assertThat(compile).succeeded();
    }

}