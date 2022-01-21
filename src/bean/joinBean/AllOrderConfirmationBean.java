package bean.joinBean;

import java.io.Serializable;

public class AllOrderConfirmationBean implements Serializable{
	private String userId;//ユーザーID
	private String mailAddress;//メールアドレス
	private String deliveryInfoId;//配送ID
	private String deliveryName;//配送先氏名
	private String postalCode;//郵便番号
	private String address;//住所
	private String tel;//電話番号
	private String cardId;//カードID
	private String cardOwnerName;//カード所有者名
	private String cardNo;//カード番号
	private String cardCompany;//カード会社
	private String expirationDate;//有効期限
	private String name;//商品名
	private String orderCount;//注文個数
	private String subTotal;//小計
	private String itemId;//アイテムID
	private String cartId;//カートID
	private String pictPath;//商品の写真
	private String total;//合計

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
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
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardOwnerName() {
		return cardOwnerName;
	}
	public void setCardOwnerName(String cardOwnerName) {
		this.cardOwnerName = cardOwnerName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardCompany() {
		return cardCompany;
	}
	public void setCardCompany(String cardCompany) {
		this.cardCompany = cardCompany;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getPictPath() {
		return pictPath;
	}
	public void setPictPath(String pictPath) {
		this.pictPath = pictPath;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}


}
