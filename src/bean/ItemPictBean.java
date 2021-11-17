package bean;

import java.io.Serializable;

public class ItemPictBean implements Serializable{
	private String pictId;
	private String pictPath;
	public String getPictId() {
		return pictId;
	}
	public void setPictId(String pictId) {
		this.pictId = pictId;
	}
	public String getPictPath() {
		return pictPath;
	}
	public void setPictPath(String pictPath) {
		this.pictPath = pictPath;
	}
}
