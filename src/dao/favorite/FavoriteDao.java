package dao.favorite;

import java.util.List;

import bean.FavoriteBean;

public interface FavoriteDao {
	public abstract List<FavoriteBean> getFavoriteList(String userId);
	public abstract boolean addFavorite(FavoriteBean favoriteBean);
	public abstract boolean deleteFavorite(String itemId,String userId);
}
