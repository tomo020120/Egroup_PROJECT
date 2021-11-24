package dao.user.login;

import bean.UserBean;

public interface RegistUserInfoDao {
	public abstract boolean addUserInfo(UserBean userBean);

	public abstract UserBean getTempUserInfo(String UUID);
}
