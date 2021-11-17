package bean;

import java.io.Serializable;

public class FavoriteBean implements Serializable{
	private String favoriteId;

	public String getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}
	
}
