package org.analyzer.jvm.util;

/**
 * StringUtils
 */
public class StringUtils {

	public static boolean isEmpty(String ret) {
        if(ret==null || ret.equals(""))
            return true;
        else
            return false;
	}

}