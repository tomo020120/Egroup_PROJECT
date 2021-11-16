package dao.user.login;

import bean.UserBean;

public interface LoginDao {
	public abstract boolean addUserLoginInfo(UserBean userBean);
}
