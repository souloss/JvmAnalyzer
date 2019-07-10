package org.analyzer.jvm.exceptions;

/**
 * NosupportMethod
 */
public class NosupportMethod extends RuntimeException{

    static final long serialVersionUID = 1L;
	
	public NosupportMethod(String data) {
        super(data); 
	}
}