package dao.user;

import java.util.List;

import bean.AddressBean;

public interface DeliveryInfoEditDao {
	public abstract List<AddressBean> getDeliveryInfo(String userId);
	public abstract AddressBean getTargetDeliveryInfo(String deliveryInfoId);
	public abstract boolean addDeliveryInfo(AddressBean addressBean);
	public abstract boolean deleteDeliveryInfo(String userId,String deliveryInfoId);
}
