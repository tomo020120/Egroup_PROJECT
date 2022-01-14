package cmd.user;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;

public class TransferAccountInfoEditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();
		String completeMessage = (String)reqContext.getSessionAttribute("completeMessage");

		if(completeMessage != null) {
			resContext.setMessage(completeMessage);
			reqContext.removeSessionAttribute("completeMessage");
		}

		resContext.setTargetPath("accountInfoEdit");

		return resContext;
	}

}
