package cmd.top;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferTopPageCommand extends AbstractCommand {

	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("topPage");
		return resContext;
	}

}
