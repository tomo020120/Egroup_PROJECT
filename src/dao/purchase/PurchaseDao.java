package dao.purchase;

import java.util.List;

import bean.joinBean.AllCartBean;
import bean.joinBean.AllOrderConfirmationBean;
import bean.joinBean.AllPurchaseHistoryBean;

public interface PurchaseDao{
	public boolean getdeliveryInfoId(String userId,String fullAddress);
	public List<AllOrderConfirmationBean> getAllOrderConfirmation(String userId,String deliveryInfoId,String cardId,String cartId);
	public AllCartBean getInsideCart(String cartId);
	public boolean PurchaseCompleted(String ItemId,String OrderCount,String subTotal,String cartId,int total,String userId);
	public List<AllPurchaseHistoryBean> getAllPurchaseHistory(String userId);
}


