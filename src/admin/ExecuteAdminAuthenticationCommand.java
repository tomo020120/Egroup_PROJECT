package admin;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;

public class ExecuteAdminAuthenticationCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String correctAuthenticationCode = (String)reqContext.getSessionAttribute("authenticationCode");
		String inputAuthenticationCode = reqContext.getParameter("admiinAuthenticaionCode")[0];

		if(inputAuthenticationCode.equals(correctAuthenticationCode)) {
			resContext.
		}

		return null;
	}

}
