package cmd.news;

import cmd.AbstractCommand;
import context.ResponseContext;

public class NewsCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("news");
		return resContext;
	}

}
