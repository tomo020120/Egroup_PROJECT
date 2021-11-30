package dao.user;

import java.util.List;

import bean.TemporaryUserBean;

public interface UserInfoDao {
	public abstract boolean addTempUserLoginInfo(TemporaryUserBean tempUserBean); // 一時テーブルへユーザー情報追加

	public abstract List<String> getUserMailAddress(); // 一時ユーザーテーブル内のメアド取得

}
