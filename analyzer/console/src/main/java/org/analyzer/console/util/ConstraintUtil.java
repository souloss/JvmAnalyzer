package org.analyzer.console.util;

import org.analyzer.console.exception.NotAllowedException;

public class ConstraintUtil {

    static public void paramNotNullCheck(Object param){
        if(param == null) {
            throw new NotAllowedException("参数不能为空");
        }
    }
}
