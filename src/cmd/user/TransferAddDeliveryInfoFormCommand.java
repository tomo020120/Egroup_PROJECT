package cmd.user;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferAddDeliveryInfoFormCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("addDeliveryInfoForm");

		return resContext;
	}

}
