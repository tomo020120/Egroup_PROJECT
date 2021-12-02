package bean;

import java.io.Serializable;

public class CartInsideBean implements Serializable{
	private String orderCount;
	private int subTotal;
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	public int getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}


}
