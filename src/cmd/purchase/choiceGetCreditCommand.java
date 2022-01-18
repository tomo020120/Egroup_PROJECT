package cmd.purchase;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dbManager.ConnectionManager;

public class choiceGetCreditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		String deliveryInfoId = reqc.getParameter("deliveryInfoId")[0];
		System.out.println(deliveryInfoId);
		reqc.setSessionAttribute("address",deliveryInfoId);
    	ConnectionManager.getInstance().beginTransaction();

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resc.setTargetPath("choiceCredit");
		return resc;
	}

}

