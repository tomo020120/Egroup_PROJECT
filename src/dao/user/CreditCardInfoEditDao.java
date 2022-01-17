package dao.user;

import java.util.List;

import bean.CreditCardBean;

public interface CreditCardInfoEditDao {
	public abstract List<CreditCardBean> getCreditCardInfo(String userId);
	public abstract CreditCardBean getTargetDeliveryInfo(String creditCardId);
	public abstract boolean addCreditCardInfo(CreditCardBean creditCardBean);
	public abstract boolean updateCreditCardInfo(CreditCardBean creditCard);
	public abstract boolean deleteCreditCardInfo(String creditCardId);
}
