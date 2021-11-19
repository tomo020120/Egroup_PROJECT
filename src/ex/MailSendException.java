package ex;

public class MailSendException extends RuntimeException {
	public MailSendException(String mess , Throwable e) {
		super(mess,e);
	}
}
