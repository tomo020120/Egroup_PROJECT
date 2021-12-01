package bean;

import java.io.Serializable;

public class CartBean implements Serializable{
	private String cartId;
	private String Total;
	
	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	
	
}
