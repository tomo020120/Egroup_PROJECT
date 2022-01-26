package cmd.products.details;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.history.HistoryDao;
import dao.products.ProductsDao;
import dbManager.ConnectionManager;


public class ProductsDetailsCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductsDao dao = factory.getProductsDao();
		HistoryDao dao2 = factory.getHistoryDao();
		String itemId=reqc.getParameter("itemId")[0];
		String userId=null;
		if(reqc.getToken()!=null) {
			userId = ((UserAndCartBean)(reqc.getToken())).getUserId();
		}
		
		
		
		ConnectionManager.getInstance().beginTransaction();
		if(userId!=null) {
			dao2.addProductHistory(userId,itemId);
		}
			resc.setResult(dao.getProductsDetails(itemId));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resc.setTargetPath("productsDetails");
		return resc;
    }
}