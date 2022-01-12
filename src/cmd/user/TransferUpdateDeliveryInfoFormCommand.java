package cmd.user;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.DeliveryInfoEditDao;
import dbManager.ConnectionManager;

public class TransferUpdateDeliveryInfoFormCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();


		String deliveryInfoId = reqContext.getParameter("deliveryInfoId")[0];

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		DeliveryInfoEditDao edit = factory.getDeliveryInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();

		resContext.setResult(edit.getTargetDeliveryInfo(deliveryInfoId));

		Object flag = reqContext.getSessionAttribute("errorFlag"); // UpdateDeliveryInfoCommandにてエラーメッセージを格納するべきかのflagがまず存在してるか判定

		if(flag != null) {
			boolean errorFlag = (boolean)flag; // あればbooleanにキャストしてエラーメッセージを格納後、セッションの消去
			if(errorFlag) {
				resContext.setMessage("入力された住所は登録済みです。");
				reqContext.removeSessionAttribute("errorFlag");
			}
		}

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resContext.setTargetPath("updateDeliveryInfoForm");

		return resContext;
	}

}
