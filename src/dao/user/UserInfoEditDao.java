package dao.user;


import java.util.List;

import bean.joinBean.UserInfoEditBean;

public abstract class UserInfoEditDao {
	public abstract boolean addTempUserLoginInfo(UserInfoEditBean userInfoEditBean); // 一時テーブルへユーザー情報追加

	public abstract List<String> getUserMailAddress(); // 一時ユーザーテーブル内のメアド取得
}
