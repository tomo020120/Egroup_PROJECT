package cmd.purchase;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.purchase.PurchaseDao;
import dbManager.ConnectionManager;

public class PurchaseHistoryCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String userId = ((UserAndCartBean)(reqContext.getToken())).getUserId();
		System.out.println(userId);
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		PurchaseDao dao = factory.getPurchaseDao();

    	ConnectionManager.getInstance().beginTransaction();


    	resContext.setResult(dao.getAllPurchaseHistory(userId));
    	resContext.setTargetPath("purchaseHistory");

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();
		return resContext;
	}

}
