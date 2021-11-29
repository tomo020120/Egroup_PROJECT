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
    	String userId="1";

    	System.out.print("要素数：" + dao.getCart(userId).size());

    	resc.setResult(dao.getCart(userId));
		resc.setTargetPath("cart");
		return resc;
	}

}