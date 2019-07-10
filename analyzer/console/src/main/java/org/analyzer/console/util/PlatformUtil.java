package org.analyzer.console.util;

import java.util.Properties;

public class PlatformUtil {
    public static Properties PROPERTIES = System.getProperties();
    public static String OS = PROPERTIES.getProperty("os.name").toUpperCase();
}
