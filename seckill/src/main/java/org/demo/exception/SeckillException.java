package org.demo.exception;

/**
 * 秒杀相关业务异常
 * @author ASUS
 *
 */
public class SeckillException extends RuntimeException{

	private static final long serialVersionUID = -8023099454437099650L;

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillException(String message) {
		super(message);
	}
	
}
