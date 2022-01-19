package cmd.user;

import bean.AddressBean;
import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.DeliveryInfoEditDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class AddDeliveryInfoCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		//各配送先情報取得
		String deliveryName = reqContext.getParameter("deliveryName")[0];
		String firstTelephoneNumber = reqContext.getParameter("firstTelephoneNumber")[0];
		String secondTelephoneNumber = reqContext.getParameter("secondTelephoneNumber")[0];
		String thirdTelephoneNumber = reqContext.getParameter("thirdTelephoneNumber")[0];
		String postalCode = reqContext.getParameter("postalCode")[0];
		String address = reqContext.getParameter("address")[0];
		String houseNumber = reqContext.getParameter("houseNumber")[0];
		String buildingName = reqContext.getParameter("buildingName")[0];

		String fullTelephoneNumber = (firstTelephoneNumber + "-" + secondTelephoneNumber + "-" + thirdTelephoneNumber);
		String fullAddress = (address + "/" + houseNumber + "/" + buildingName);

		String userId = ((UserAndCartBean)reqContext.getToken()).getUserId();

		AddressBean addressBean = new AddressBean();


		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		DeliveryInfoEditDao edit = factory.getDeliveryInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();

		if(edit.getSameAddressQuantity(fullAddress, userId) != 0) {
			resContext.setMessage("入力された住所は登録済みです。");
			resContext.setTargetPath("addDeliveryInfoForm");

			ConnectionManager.getInstance().closeTransaction();

			return resContext;
		}

		addressBean.setDeliveryName(deliveryName);
		addressBean.setTel(fullTelephoneNumber);
		addressBean.setPostalCode(postalCode);
		addressBean.setAddress(fullAddress);
		addressBean.setUserId(userId);

		if(edit.addDeliveryInfo(addressBean)) {
			resContext.setTargetCommandPath("deliveryInfoEdit");

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException("配送先情報新規追加エラー", null);
		}

		return resContext;
	}
}