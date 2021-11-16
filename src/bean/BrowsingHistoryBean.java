package bean;

import java.io.Serializable;

public class BrowsingHistoryBean implements Serializable{
	private String viewId;
	private String clickDate;
	public String getViewId() {
		return viewId;
	}
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}
	public String getClickDate() {
		return clickDate;
	}
	public void setClickDate(String clickDate) {
		this.clickDate = clickDate;
	}
	
	
}
