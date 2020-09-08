package com.mitsburguer.app;

import java.io.InputStream;

public class MockUtils {

    public static InputStream buildMock(Class<?> clazz, String fileName){
        return clazz.getClassLoader().getResourceAsStream(fileName);
    }
}
