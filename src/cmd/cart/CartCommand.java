package cmd.cart;

import cmd.AbstractCommand;
import context.ResponseContext;

public class CartCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("cart");
		return resContext;
	}

}