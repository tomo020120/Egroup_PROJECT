package dao.user;

import java.util.List;

import bean.CreditCardBean;

public interface CreditCardInfoEditDao {
	public abstract List<CreditCardBean> getCreditCardInfo(String userId);
	public abstract int getSameCreditCardQuantity(String cardNo,String userId); // 同一カードの個数取得
	public abstract boolean addCreditCardInfo(CreditCardBean creditCardBean);
	public abstract boolean updateCreditCardInfo(CreditCardBean creditCardBean);
	public abstract boolean deleteCreditCardInfo(String creditCardId);
}
