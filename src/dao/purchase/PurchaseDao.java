package dao.purchase;

import java.util.List;

import bean.joinBean.AllOrderConfirmationBean;

public interface PurchaseDao{
	public boolean getdeliveryInfoId(String userId,String fullAddress);
	public List<AllOrderConfirmationBean> getAllOrderConfirmation(String userId,String deliveryInfoId,String cardId,String cartId);
}


