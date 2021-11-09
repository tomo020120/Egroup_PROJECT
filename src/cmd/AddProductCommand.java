package cmd;

import java.util.Map;
import bean.ProductBean;
import orcl.DbDummy;
import context.RequestContext;
import context.ResponseContext;
import context.WebResponseContext;


public class AddProductCommand extends AbstractCommand{
    public ResponseContext execute(ResponseContext resContext){
        RequestContext reqContext = getRequestContext();

        String[] itemName = reqContext.getParameter("name");
        String[] price = reqContext.getParameter("price");

        ProductBean product = new ProductBean(); 

        product.setName(itemName[0]);
        product.setPrice(Integer.parseInt(price[0]));

        DbDummy.addProductBean(product);

        resContext.setTargetPath("start");

        return resContext;
    }
}