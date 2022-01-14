package cmd.cart;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.cart.CartDao;
import dbManager.ConnectionManager;

public class TransferCartCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqContext = getRequestContext();
    	AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
    	CartDao dao = factory.getCartDao();

    	UserAndCartBean user=(UserAndCartBean)reqContext.getToken();

    	if(user==null) {
    		resc.setTargetCommandPath("login");
    		return resc;
    	}
    	ConnectionManager.getInstance().beginTransaction();

    	resc.setResult(dao.getInsideCart(user.getUserId()));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resc.setTargetPath("cart");
		return resc;
	}

}