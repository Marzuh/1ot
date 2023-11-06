package com.oneot.testassigment.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

public class TestUtil {
    public static String loadResource(String resourcePath) throws Exception {
        File file = ResourceUtils.getFile("classpath:" + resourcePath);
        return new String(Files.readAllBytes(file.toPath()));
    }
}
