package cmd.admin.user;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;

public class SwitchLoginUserCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		reqContext.removeSessionAttribute("token"); // ログイントークンを消してログイン画面へ

		resContext.setTargetCommandPath("login");

		return resContext;
	}

}
