package cmd.user;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferPasswordEditFormCommand extends AbstractCommand{

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("passwordEditForm");

		return resContext;
	}
}
