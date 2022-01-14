package dao.user;

import java.util.List;

public interface UserAccountInfoEditDao {
	public abstract List<String> getAllUserMailAddress(); // ユーザーテーブル内のメアド取得

	public abstract boolean updateUserName(String newUserName,String userId); // ユーザー名更新

	public abstract boolean updateUserMailAddress(String newUserMailAddress,String userId); // メアド更新

	public abstract boolean updatePassword(String newPassword,String userId); // パスワード更新
}
