package cmd.user;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferAccountInfoEditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("accountInfoEdit");

		return resContext;
	}

}
