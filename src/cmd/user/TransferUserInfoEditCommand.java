package cmd.user;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferUserInfoEditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {

		resContext.setTargetPath("userInfoEdit");
		return resContext;
	}

}