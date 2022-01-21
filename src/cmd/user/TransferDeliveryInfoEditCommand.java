package cmd.user;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.DeliveryInfoEditDao;
import dbManager.ConnectionManager;

public class TransferDeliveryInfoEditCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();
		String userId = ((UserAndCartBean)reqContext.getToken()).getUserId(); // セッションからユーザーID取得

		String message = ""; // 一つ前のコマンドでパラメータとして渡されたものを取得。ヌルポならなにも入れない。
		try {
			message = reqContext.getParameter("message")[0];
		}
		catch(NullPointerException e) {
			message = "";
		}

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		DeliveryInfoEditDao edit = factory.getDeliveryInfoEditDao();

		ConnectionManager.getInstance().beginTransaction();

		resContext.setMessage(message);
		resContext.setResult(edit.getDeliveryInfo(userId));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resContext.setTargetPath("deliveryInfoEdit");
		return resContext;
	}
}
