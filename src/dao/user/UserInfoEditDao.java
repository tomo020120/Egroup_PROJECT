package dao.user;

import java.util.List;

import bean.AddressBean;
import bean.CreditCardBean;
import bean.UserBean;

public interface UserInfoEditDao {
	public abstract List<CreditCardBean> getCreditCardInfo(String userId);

	public abstract List<AddressBean> getAddressInfo(String userId);

	public abstract boolean updateUserInfo(UserBean userBean);

	public abstract boolean addCreditCard(CreditCardBean creditCardBean, String userId);

	public abstract boolean addAddress(AddressBean addressbean, String userId);

	public abstract boolean deleteCreditCard(String creditcardId);

	public abstract boolean deleteAddress(String addressId);
}
