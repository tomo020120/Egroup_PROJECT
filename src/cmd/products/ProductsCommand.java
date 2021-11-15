package cmd.products;

import cmd.AbstractCommand;
import context.ResponseContext;

public class ProductsCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("products");
		return resContext;
	}

}