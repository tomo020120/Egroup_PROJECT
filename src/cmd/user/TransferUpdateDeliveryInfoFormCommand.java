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

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resContext.setTargetPath("updateDeliveryInfoForm");

		return resContext;
	}

}
