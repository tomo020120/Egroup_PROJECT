package bean;

import java.io.Serializable;

public class CartInsideBean implements Serializable{
	private String orderCount;
	private String subTotal;
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	
	
}
