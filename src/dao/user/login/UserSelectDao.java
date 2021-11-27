package dao.user.login;

import bean.UserBean;

public interface UserSelectDao{
	public abstract  UserBean getUserInfo(String _mail,String _pass);
}