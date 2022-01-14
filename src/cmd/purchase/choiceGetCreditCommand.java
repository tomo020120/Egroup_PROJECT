package cmd.purchase;

import cmd.AbstractCommand;
import context.ResponseContext;
import dbManager.ConnectionManager;

public class choiceGetCreditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {

    	ConnectionManager.getInstance().beginTransaction();

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resc.setTargetPath("choiceCredit");
		return resc;
	}

}

