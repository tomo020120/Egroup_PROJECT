package cmd;

import context.ResponseContext;
import orcl.DbDummy;

public class GetProductsCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resContext){
        resContext.setResult(DbDummy.getDatabase());

        resContext.setTargetPath("view");

        return resContext;
    }
}