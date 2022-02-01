package cmd.user.guest;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferGuestDeliveryInfoFormCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("guestDeliveryInfoForm");

		return resContext;
	}

}
