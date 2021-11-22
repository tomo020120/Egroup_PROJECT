package cmd.products.search;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import context.WebResponseContext;
import dao.AbstractDaoFactory;
import dao.products.ProductsDao;

public class ProductsSearchCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		resc = new WebResponseContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductsDao dao = factory.getProductsDao();
		String productName=reqc.getParameter("productName")[0];
		resc.setResult(dao.getProductsSearchResult(productName));
		resc.setTargetPath("products");
		return resc;
	}
}
