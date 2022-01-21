package cmd.user;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.DeliveryInfoEditDao;
import dbManager.ConnectionManager;
import ex.IntegrationException;

public class ExecuteDeleteDeliveryInfoCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String userId = ((UserAndCartBean)reqContext.getToken()).getUserId();

		String deliveryInfoId = reqContext.getParameter("deliveryInfoId")[0];

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		DeliveryInfoEditDao edit = factory.getDeliveryInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();

		String url = "deliveryInfoEdit?message=";

		if(edit.deleteDeliveryInfo(userId,deliveryInfoId)) {
			url += "配送先情報消去完了";
			resContext.setTargetCommandPath(url);

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();
		}else {
			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();

			throw new IntegrationException("配送先情報消去時エラー", null);
		}

		return resContext;
	}

}
