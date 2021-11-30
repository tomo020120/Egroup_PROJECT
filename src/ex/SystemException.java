package ex;

public class SystemException extends RuntimeException {
	public SystemException(String mess,Throwable e) {
		super(mess,e);
	}
}
