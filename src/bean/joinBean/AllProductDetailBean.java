package bean.joinBean;

import java.io.Serializable;

public class AllProductDetailBean implements Serializable{
	private String itemId;
	private String name;
	private String price;
	private String releaseDate;
	private String orderCount;

	private String colorId;
	private String colorName;

	private String categoryId;
	private String categoryName;


	private String shapeId;
	private String shapeName;

	private String artistId;
	private String artistName;
	private String coutory;
	private String group;
	private String artistPict;


	private String pictId;
	private String pictPath;

	private String spec;
	private String neck;


	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getNeck() {
		return neck;
	}
	public void setNeck(String neck) {
		this.neck = neck;
	}
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}

	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getShapeId() {
		return shapeId;
	}
	public void setShapeId(String shapeId) {
		this.shapeId = shapeId;
	}
	public String getShapeName() {
		return shapeName;
	}
	public void setShapeName(String shapeName) {
		this.shapeName = shapeName;
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