package cmd.products.details;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import context.WebResponseContext;
import dao.AbstractDaoFactory;
import dao.products.ProductsDao;


public class ProductsDetailsCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		resc = new WebResponseContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductsDao dao = factory.getProductsDao();
		String itemId=reqc.getParameter("itemId")[0];
		resc.setResult(dao.getProductsDetails(itemId));
		resc.setTargetPath("productsDetails");
		return resc;
    }
}