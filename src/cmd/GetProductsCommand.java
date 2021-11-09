package cmd;

import orcl.DbDummy;
import java.util.ArrayList;
import context.RequestContext;
import context.ResponseContext;
import context.WebResponseContext;

public class GetProductsCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resContext){
        resContext.setResult(DbDummy.getDatabase());

        resContext.setTargetPath("view");

        return resContext;
    }
}