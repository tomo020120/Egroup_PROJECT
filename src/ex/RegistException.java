package ex;

public class RegistException extends RuntimeException {
	public RegistException(String mess,Throwable e){
		super(mess,e);
	}
}
