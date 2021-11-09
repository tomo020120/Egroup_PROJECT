package cmd;

import context.ResponseContext;
import context.WebResponseContext;


public class InputCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resContext){
        resContext.setTargetPath("input");

        return resContext;
    }
}