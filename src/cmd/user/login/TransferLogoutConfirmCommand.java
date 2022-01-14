package cmd.user.login;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferLogoutConfirmCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("logoutConfirm");
		return resContext;
	}

}
