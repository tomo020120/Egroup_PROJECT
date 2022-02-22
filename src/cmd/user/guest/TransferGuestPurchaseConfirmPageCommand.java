package cmd.user.guest;

import cmd.AbstractCommand;
import cmd.authentication.CreditCardSecurityCodeAuthentication;
import context.RequestContext;
import context.ResponseContext;

public class TransferGuestPurchaseConfirmPageCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String securityCode = reqContext.getParameter("securityCode")[0];
		String cardNo = reqContext.getParameter("cardNo")[0];

		System.out.println("入力セキュリティコード" + securityCode);

		boolean isCorrectCode = CreditCardSecurityCodeAuthentication.isCorrectSecurityCode(cardNo, securityCode);

		if(isCorrectCode) {
			resContext.setTargetPath("guestPurchaseConfirmPage");
		}
		else {
			resContext.setMessage("セキュリティコードが違います。");
			resContext.setTargetPath("guestCreditCardInfoForm");
		}

		return resContext;
	}

}
