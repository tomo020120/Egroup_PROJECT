package dao.user.login;

import bean.UserBean;
import bean.joinBean.UserAndCartBean;

public interface UserSelectDao{
	public abstract UserBean getUserInfo(String _mail,String _pass);
	public abstract UserAndCartBean getUserAndCartInfo(String userId);
}