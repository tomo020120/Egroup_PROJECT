package cmd.admin;

import bean.joinBean.UserAndCartBean;
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
			UserAndCartBean userAndCartBean = new UserAndCartBean();
			userAndCartBean.setUserName("管理者");

			reqContext.setToken(userAndCartBean);

			reqContext.removeSessionAttribute("authenticationCode");
			resContext.setTargetPath("adminPage");
		}else {
			resContext.setMessage("認証コードが間違っています");
			resContext.setTargetPath("adminAuthenticationPage");
		}

		return resContext;
	}

}
