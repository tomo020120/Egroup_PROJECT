package cmd.news.details;

import cmd.AbstractCommand;
import context.ResponseContext;

//このコマンドは実質ダミー
public class NewsDetailCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {
		resContext.setTargetPath("newsDetail");
		return resContext;
	}

}