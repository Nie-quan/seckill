package org.demo.exception;

/**
 * ��ɱ�ر��쳣
 * @author ASUS
 *
 */
public class SeckillCloseException extends RuntimeException{
	
	private static final long serialVersionUID = 7884701223507977034L;

	public SeckillCloseException(String message) {
		super(message);
	}

	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
