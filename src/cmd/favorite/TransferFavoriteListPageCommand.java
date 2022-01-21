package cmd.favorite;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferFavoriteListPageCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("favoriteListPage");
		return resContext;
	}
}