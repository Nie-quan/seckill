package org.demo.exception;

/**
 * �ظ���ɱ�쳣(�������쳣)
 * @author ASUS
 *
 */
public class RepeatKillException extends RuntimeException{
	
	private static final long serialVersionUID = -4623587979677480627L;

	public RepeatKillException(String message) {
		super(message);
	}

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
