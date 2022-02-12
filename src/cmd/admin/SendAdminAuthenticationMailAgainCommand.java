package cmd.admin;

import cmd.AbstractCommand;
import cmd.RandomNumberGenerator.CreateRandomNumber;
import cmd.mail.SendMail;
import context.RequestContext;
import context.ResponseContext;

public class SendAdminAuthenticationMailAgainCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String authenticationCode = CreateRandomNumber.getSixDegitsNumber();

		SendMail.send("adm1n.ibanez0120@gmail.com","認証コード:" + authenticationCode,"管理者用認証コード");
		reqContext.setSessionAttribute("authenticationCode", authenticationCode);

		resContext.setTargetPath("adminAuthenticationPage");

		return resContext;
	}

}
