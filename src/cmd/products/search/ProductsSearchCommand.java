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
		String sortNo=reqc.getParameter("sort")[0];
		
		
		switch(sortNo) {
			case "1" :
				sortNo="ORDER BY a.itemId ASC";
				break;
			case "2" :
				sortNo="ORDER BY price ASC";
				break;
			case "3" :
				sortNo="ORDER BY price DESC";
				break;
				
		}
		
		resc.setResult(dao.getProductsSearchResult(productName,sortNo));
		resc.setTargetPath("products");
		return resc;
		//アヒージョ
	}
}
