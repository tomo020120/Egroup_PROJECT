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

public class UpdateDeliveryInfoCommand extends AbstractCommand{

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
		String deliveryInfoId = reqContext.getParameter("deliveryInfoId")[0];

		String fullTelephoneNumber = (firstTelephoneNumber + "-" + secondTelephoneNumber + "-" + thirdTelephoneNumber);
		String fullAddress = (address + "/" + houseNumber + "/" + buildingName);

		String userId = ((UserAndCartBean)(reqContext.getToken())).getUserId();

		AddressBean addressBean = new AddressBean();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		DeliveryInfoEditDao edit = factory.getDeliveryInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();

		addressBean.setDeliveryInfoId(deliveryInfoId);
		addressBean.setDeliveryName(deliveryName);
		addressBean.setTel(fullTelephoneNumber);
		addressBean.setPostalCode(postalCode);
		addressBean.setAddress(fullAddress);

		String url = "deliveryInfoEdit?message=";

		if(edit.updateDeliveryInfo(addressBean)) {
			int result = edit.getSameAddressQuantity(fullAddress, userId);
			if(result <= 1) {
				url += "配送先情報編集完了";
				resContext.setTargetCommandPath(url);

				ConnectionManager.getInstance().commit();
				ConnectionManager.getInstance().closeTransaction();
			}else {
				url += "入力された住所はすでに登録されているため登録できません。";
				resContext.setTargetCommandPath(url);

				ConnectionManager.getInstance().rollback();
				ConnectionManager.getInstance().closeTransaction();
			}
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException("配送先情報更新エラー", null);
		}

		return resContext;
	}
}
