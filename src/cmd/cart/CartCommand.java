package cmd.cart;

import bean.UserBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.cart.CartDao;
import dbManager.ConnectionManager;

public class CartCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqContext = getRequestContext();
    	AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
    	CartDao dao = factory.getCartDao();

    	UserBean user=(UserBean)reqContext.getToken();
    	if(user==null) {
    		resc.setTargetPath("login");
    		return resc;
    	}
    	ConnectionManager.getInstance().beginTransaction();

    	resc.setResult(dao.getCart(user.getUserId()));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resc.setTargetPath("cart");
		return resc;
	}

}