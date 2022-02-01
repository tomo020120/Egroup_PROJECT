package cmd.user.guest;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferGuestCreditCardInfoFormCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("guestCreditCardInfoForm");

		return resContext;
	}

}
