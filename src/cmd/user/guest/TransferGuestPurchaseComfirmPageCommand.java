package cmd.user.guest;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferGuestPurchaseComfirmPageCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("guestPurchaseComfirmPage");

		return resContext;
	}

}
