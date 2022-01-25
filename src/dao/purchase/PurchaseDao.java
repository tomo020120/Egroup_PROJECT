package dao.purchase;

import java.util.List;

import bean.AddressBean;
import bean.CreditCardBean;
import bean.joinBean.AllCartBean;
import bean.joinBean.AllOrderConfirmationBean;
import bean.joinBean.AllPurchaseHistoryBean;

public interface PurchaseDao{
	public abstract boolean getdeliveryInfoId(String userId,String fullAddress);
	public abstract List<AllOrderConfirmationBean> getAllOrderConfirmation(String userId,String deliveryInfoId,String cardId,String cartId);
	public abstract AllCartBean getInsideCart(String cartId);
	public abstract boolean PurchaseCompleted(String ItemId,String OrderCount,String subTotal,String cartId,int total,String userId);
	public abstract List<AllPurchaseHistoryBean> getAllPurchaseHistory(String userId);
	public abstract AddressBean getTargetAddressInfo(String deliveryInfoId);
	public abstract CreditCardBean getTargetCreditCardInfo(String cardId);
}


