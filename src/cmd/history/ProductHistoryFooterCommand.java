package cmd.history;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.history.HistoryDao;
import dbManager.ConnectionManager;

public class ProductHistoryFooterCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		HistoryDao dao = factory.getHistoryDao();
		String userId=((UserAndCartBean)(reqc.getToken())).getUserId();
		
		ConnectionManager.getInstance().beginTransaction();
		System.out.println(userId);
		resc.setResult(dao.getProductHistory(userId));
		
		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();
		
		resc.setTargetPath("productHistoryFooter");
		
		return resc;
	}
}
