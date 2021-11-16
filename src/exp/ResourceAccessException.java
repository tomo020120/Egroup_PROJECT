package exp;

public class ResourceAccessException extends RuntimeException{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L; 
		// コンストラクタ
	public ResourceAccessException(String msg,Exception e){
		super(msg,e);
	}
}