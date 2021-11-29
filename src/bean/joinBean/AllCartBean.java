package bean.joinBean;

import java.io.Serializable;

public class AllCartBean implements Serializable{
	private String itemId;//アイテムID
	private String name;//商品名

	private String subTotal;//小計
	private String orderCount;//個数
	private String total;
	private String pictPath;

	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
		System.out.println("test"+this.itemId);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getPictPath() {
		return pictPath;
	}
	public void setPictPath(String pictPath) {
		this.pictPath = pictPath;
	}



}
