package cmd.user;

import bean.joinBean.UserAndCartBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;

public class TransferUserInfoEditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		UserAndCartBean userAndCartBean = (UserAndCartBean) reqContext.getToken();

		if(userAndCartBean == null) { // ログインしてなければログインへ飛ばす
			resContext.setTargetCommandPath("login");
			return resContext;
		}

		resContext.setTargetPath("userInfoEdit");

		return resContext;
	}

}