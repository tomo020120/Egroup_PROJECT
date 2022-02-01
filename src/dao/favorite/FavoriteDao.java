package dao.favorite;

import java.util.List;

import bean.FavoriteBean;
import bean.joinBean.AllFavoriteBean;

public interface FavoriteDao {
	public abstract List<AllFavoriteBean> getFavoriteList(String userId);
	public abstract boolean addFavorite(FavoriteBean favoriteBean);
	public abstract boolean deleteFavorite(String itemId,String userId);
	public abstract boolean isAddFavoriteItem(String itemId,String userId);
}
