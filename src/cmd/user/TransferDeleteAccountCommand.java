package cmd.user;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferDeleteAccountCommand extends AbstractCommand{

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("deleteAccount");

		return resContext;
	}
}
