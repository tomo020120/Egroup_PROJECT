package cmd.user;

import cmd.AbstractCommand;
import context.ResponseContext;

public class TransferRegistUserInfoCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resContext) {


		resContext.setTargetPath("registUserInfo");//jspファイルを指す;
		return resContext;
	}

}


