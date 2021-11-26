package dao.user;

import bean.AddressBean;
import bean.CreditCardBean;
import bean.UserBean;

public interface EditUserInfoDao {
	public abstract boolean updateUserInfo(UserBean userBean);

	public abstract boolean addCreditCard(CreditCardBean creditCardBean, String userId);

	public abstract boolean addAddress(AddressBean addressbean, String userId);

	public abstract boolean deleteCreditCard(String creditcardId);

	public abstract boolean deleteAddress(String addressId);
}