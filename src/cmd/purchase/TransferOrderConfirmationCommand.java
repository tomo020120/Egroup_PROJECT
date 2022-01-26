package cmd.purchase;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.purchase.PurchaseDao;
import dbManager.ConnectionManager;

public class TransferOrderConfirmationCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String creditCardInfoId = reqContext.getParameter("creditCardInfoId")[0];

		reqContext.setSessionAttribute("creditCard",creditCardInfoId);

		UserAndCartBean userAndCartBean = (UserAndCartBean)reqContext.getToken();

		String userId = userAndCartBean.getUserId();
		String cartId = userAndCartBean.getCartId();

		String deliveryInfoId=reqContext.getSessionAttribute("address").toString();
		String cardId=reqContext.getSessionAttribute("creditCard").toString();

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		PurchaseDao dao = factory.getPurchaseDao();
    	ConnectionManager.getInstance().beginTransaction();


    	resContext.setResult(dao.getAllOrderConfirmation(userId, deliveryInfoId, cardId, cartId));
    	resContext.setTargetPath("orderConfirmationt");

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();
		return resContext;
	}

}