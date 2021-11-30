package ex;

public class BusinessException extends SystemException {

	public BusinessException(String mess, Throwable e) {
		super(mess, e);
	}

}
