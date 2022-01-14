package cmd.user;

import bean.joinBean.UesrInfoBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;

public class TransferUserInfoEditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {

		RequestContext reqContext = getRequestContext();

		String userName = reqContext.getParameter("name")[0];
		String emailAddress = reqContext.getParameter("emailAddress")[0];
		String userPass = reqContext.getParameter("pass")[0];
		String cardCode = reqContext.getParameter("cardCode")[0];
		String address = reqContext.getParameter("address")[0];
		String tel = reqContext.getParameter("tel")[0];
		String postalCode = reqContext.getParameter("postalCode")[0];

		UesrInfoBean UIB = new UesrInfoBean();

		UIB.setUserName(userName);
		UIB.setEmailAddress(emailAddress);
		UIB.setUserPassword(userPass);
		UIB.setCartCode(cardCode);
		UIB.setAddress(address);
		UIB.setTel(tel);
		UIB.setPostalCode(postalCode);

		resContext.setTargetPath("userInfoEdit");
		return resContext;
	}

}