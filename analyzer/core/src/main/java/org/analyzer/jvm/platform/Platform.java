package org.jvm.analyzer.platform;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Platform {
    static final Properties JVMProperties = System.getProperties();
    public static final Path javaToolDir = Paths.get(JVMProperties.getProperty("java.home")).getParent().resolve("bin");
    static final String OSName = JVMProperties.getProperty("os");

}