package bean.joinBean;

import java.io.Serializable;

public class AllArtistDetailBean implements Serializable {
	private String itemId;
	private String name;

	private String artistId;
	private String artistName;
	private String coutory;
	private String group;
	private String artistPict;

	private String pictId;
	private String pictPath;


	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getCoutory() {
		return coutory;
	}
	public void setCoutory(String coutory) {
		this.coutory = coutory;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getArtistPict() {
		return artistPict;
	}
	public void setArtistPict(String artistPict) {
		this.artistPict = artistPict;
	}
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
