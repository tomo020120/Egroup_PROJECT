<<<<<<< HEAD
package cmd.user.login;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;

public class ExecuteLogoutCommand extends AbstractCommand {

	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();


		return null;
	}

}
=======
package cmd.user.login;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;

public class ExecuteLogoutCommand extends AbstractCommand {

	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		reqContext.sessionInvalidate();

		resContext.setTargetPath("logoutComplete");

		return resContext;
	}

}
>>>>>>> branch 'master' of git@github.com:tomo020120/Egroup_PROJECT.git
