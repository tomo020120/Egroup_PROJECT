package bean;

import java.io.Serializable;

public class ColorBean implements Serializable{
	private String colorId;
	private String colorName;
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
	
	
}
