package cmd.user;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.DeliveryInfoEditDao;
import dbManager.ConnectionManager;

public class ExecuteDeleteDeliveryInfoCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String deliveryInfoId = reqContext.getParameter("deliveryInfoId")[0];

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		DeliveryInfoEditDao edit = factory.getDeliveryInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();

		if(edit.deleteDeliveryInfo(deliveryInfoId)) {
			resContext.setTargetCommandPath("deliveryInfoEdit");
			reqContext.setSessionAttribute("deleteFlag",true);

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();
		}

		return resContext;
	}

}
