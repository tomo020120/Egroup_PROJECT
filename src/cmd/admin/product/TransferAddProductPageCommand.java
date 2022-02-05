package cmd.admin.product;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferAddProductPageCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("addProductPage");

		return resContext;
	}

}
