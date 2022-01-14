package dao.user.login;

import java.util.List;

import bean.TemporaryUserBean;

public interface TempRegistDao {
	public abstract boolean addTempUserLoginInfo(TemporaryUserBean tempUserBean); // 一時テーブルへユーザー情報追加

	public abstract List<String> getUserMailAddress(); // ユーザーテーブル内のメアド取得
}
