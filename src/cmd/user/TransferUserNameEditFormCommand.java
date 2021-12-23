package cmd.user;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferUserNameEditFormCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("userNameEditForm");

		return resContext;
	}

}
