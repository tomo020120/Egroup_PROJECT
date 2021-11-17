package bean;

import java.io.Serializable;

public class ArtistBean implements Serializable{
	private String artistId;
	private String artistName;
	private String coutory;
	private String group;
	private String artistPict;
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
}
