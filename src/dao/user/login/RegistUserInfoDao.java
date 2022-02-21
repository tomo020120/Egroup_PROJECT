package dao.user.login;

import bean.TemporaryUserBean;

public interface RegistUserInfoDao {
	public abstract boolean addUserInfo(TemporaryUserBean userBean);

	public abstract boolean deleteTemporaryData(String UUID);

	public abstract TemporaryUserBean getTempUserInfo(String UUID);
}
