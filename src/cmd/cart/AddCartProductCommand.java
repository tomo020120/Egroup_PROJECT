package cmd.cart;

import bean.CartBean;
import bean.UserBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.cart.CartDao;
import dbManager.ConnectionManager;

public class AddCartProductCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		CartDao dao = factory.getCartDao();
		String itemId=reqc.getParameter("itemId")[0];
		String orderCount=reqc.getParameter("orderCount")[0];
		String price=reqc.getParameter("price")[0];
		
		//外部キーのcartIDとってくる
		String cartId="1";
		String subTotal = Integer.toString(Integer.parseInt(price) * Integer.parseInt(orderCount));
		
		UserBean user=(UserBean)reqc.getToken();
    	if(user==null) {
    		resc.setTargetPath("login");
    		return resc;
    	}
    	
		String userId=user.getUserId();
		
		CartBean cartbean = dao.getCartTotal(userId,cartId);
		String total = cartbean.getTotal();
		total+=Integer.parseInt(subTotal);//subTotalを加える

		System.out.println("orderCount ; " + orderCount);

		//以下一文は一時的な採用

		/*
		CartInsideBean cartInsideBean = new CartInsideBean();
		cartInsideBean.setOrderCount(orderCount);
		cartInsideBean.setSubTotal(subTotal);
		*/

		ConnectionManager.getInstance().beginTransaction();
		
		if(dao.addCartProduct(itemId, orderCount, subTotal, cartId)) {
			System.out.println("cartinside追加");
			if(dao.updateCartTotal(total,userId)) {

				ConnectionManager.getInstance().commit();
				ConnectionManager.getInstance().closeTransaction();

				resc.setTargetPath("CartAddComplete");
				System.out.println("cart追加");
				return resc;
			}
		}
		ConnectionManager.getInstance().rollback();
		ConnectionManager.getInstance().closeTransaction();

		System.out.println("cartinside失敗");
		resc.setTargetPath("error/integrationError");
		return resc;
    }
}