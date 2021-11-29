package cmd.cart;

import cmd.AbstractCommand;
import context.ResponseContext;
import context.WebResponseContext;
import dao.AbstractDaoFactory;
import dao.cart.CartDao;

public class CartCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		resc = new WebResponseContext();
    	AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
    	CartDao dao = factory.getCartDao();
    	resc.setResult(dao.getCart());
		resc.setTargetPath("cart");
		return resc;
	}

}