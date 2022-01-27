package cmd.top;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferUserInfoPageCommand extends AbstractCommand {

	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("userInfo");
		return resContext;
	}

}
