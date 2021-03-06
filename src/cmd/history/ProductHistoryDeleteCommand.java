package cmd.history;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.history.HistoryDao;
import dbManager.ConnectionManager;


public class ProductHistoryDeleteCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		HistoryDao dao = factory.getHistoryDao();
		String itemId=reqc.getParameter("itemId")[0];
		String userId=null;
		if(reqc.getToken()!=null) {
			userId = ((UserAndCartBean)(reqc.getToken())).getUserId();
		}


		ConnectionManager.getInstance().beginTransaction();
		System.out.println(userId);
		dao.deleteProductHistory(userId,itemId);
		resc.setResult(dao.getProductHistory(userId));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resc.setTargetPath("productHistoryPage");

		return resc;
	}
}
