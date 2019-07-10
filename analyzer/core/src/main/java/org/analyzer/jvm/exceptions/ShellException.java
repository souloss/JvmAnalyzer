package org.analyzer.jvm.exceptions;

/**
 * ShellException
 */
public class ShellException extends RuntimeException{

	static final long serialVersionUID = 1L;
	
	public ShellException(String data) {
        super(data); 
	}
}