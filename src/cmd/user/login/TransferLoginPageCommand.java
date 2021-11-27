package cmd.user.login;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;


public class TransferLoginPageCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resContext){
    	RequestContext reqContext = getRequestContext();

    	reqContext.setPastLocation(reqContext.getOneBeforeLocation()); // ログイン画面に行く前の情報取得

        resContext.setTargetPath("login");

        return resContext;
    }
}