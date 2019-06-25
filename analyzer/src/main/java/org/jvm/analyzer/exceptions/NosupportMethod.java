package org.jvm.analyzer.exceptions;

/**
 * NosupportMethod
 */
public class NosupportMethod extends RuntimeException{

    static final long serialVersionUID = 1L;
	
	public NosupportMethod(String data) {
        super(data); 
	}
}