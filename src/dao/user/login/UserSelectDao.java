package dao.user.login;


public interface UserSelectDao{
	public abstract  boolean selectMailAndPass(String _mail,String _pass);
}