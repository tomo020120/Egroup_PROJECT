package cmd.user.guest;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferGuestPurchaseConfirmPageCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("guestPurchaseConfirmPage");

		return resContext;
	}

}
