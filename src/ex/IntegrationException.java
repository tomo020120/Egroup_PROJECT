package ex;

public class IntegrationException extends RuntimeException {
	public IntegrationException(String mess,Throwable e){
		super(mess,e);
	}
}
