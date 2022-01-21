package dao.user;

import java.util.List;

import bean.AddressBean;

public interface DeliveryInfoEditDao {
	public abstract List<AddressBean> getDeliveryInfo(String userId);
	public abstract int getSameAddressQuantity(String address,String userId); // 同一住所の個数取得
	public abstract boolean addDeliveryInfo(AddressBean addressBean);
	public abstract boolean updateDeliveryInfo(AddressBean addressBean);
	public abstract boolean deleteDeliveryInfo(String userId,String deliveryInfoId);
}
