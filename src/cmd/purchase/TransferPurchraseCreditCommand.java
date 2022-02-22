package cmd.purchase;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.user.CreditCardInfoEditDao;
import dbManager.ConnectionManager;

public class TransferPurchraseCreditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String userId = ((UserAndCartBean)(reqContext.getToken())).getUserId();
		System.out.println(userId);
		//if(reqContext.getSessionAttribute("address")== null)
		String deliveryInfoId="";
		try {
			deliveryInfoId = reqContext.getParameter("deliveryInfoId")[0];
			//System.out.println("deliveryInfoId:"+deliveryInfoId);

		}catch(NullPointerException e) {
			deliveryInfoId = (String)reqContext.getSessionAttribute("address");

		}

		try {
			String message = reqContext.getParameter("message")[0];

			resContext.setMessage(message);
		}catch(NullPointerException e) {}

		reqContext.setSessionAttribute("address",deliveryInfoId);

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		CreditCardInfoEditDao edit = factory.getCreditCardInfoEditDao();

    	ConnectionManager.getInstance().beginTransaction();

    	resContext.setResult(edit.getCreditCardInfo(userId));
    	resContext.setTargetPath("choiceCredit");



		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();
		return resContext;
	}

}


