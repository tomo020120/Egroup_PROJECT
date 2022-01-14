package cmd.user;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferUserMailAddressEditFormCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("userMailAddressEditForm");

		return resContext;
	}

}
