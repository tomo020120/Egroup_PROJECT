package cmd.userInfoEdit;

import cmd.AbstractCommand;
import context.ResponseContext;

public class UserInfoEditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("userInfoEdit");
		return resContext;
	}

}