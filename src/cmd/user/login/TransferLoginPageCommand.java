package cmd.user.login;

import cmd.AbstractCommand;
import context.ResponseContext;


public class TransferLoginPageCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resContext){
        resContext.setTargetPath("login");
//わあああ
        return resContext;
    }
}