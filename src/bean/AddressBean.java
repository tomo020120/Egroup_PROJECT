package bean;

import java.io.Serializable;

public class AddressBean implements Serializable{
	private String deliveryInfoId;
	private String deliveryName;
	private String postalCode;
	private String address;
	private String tel;
	private String userId;

	public String getDeliveryInfoId() {
		return deliveryInfoId;
	}
	public void setDeliveryInfoId(String deliveryInfoId) {
		this.deliveryInfoId = deliveryInfoId;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
