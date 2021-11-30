package cmd.cart;

import bean.UserBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import context.WebResponseContext;
import dao.AbstractDaoFactory;
import dao.cart.CartDao;

public class CartCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqContext = getRequestContext();
		resc = new WebResponseContext();
    	AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
    	CartDao dao = factory.getCartDao();

    	UserBean user=(UserBean)reqContext.getToken();
    	resc.setResult(dao.getCart(user.getUserId()));
		resc.setTargetPath("cart");
		return resc;
	}

}