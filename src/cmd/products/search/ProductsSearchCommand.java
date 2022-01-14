package cmd.products.search;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.products.ProductsDao;
import dbManager.ConnectionManager;

public class ProductsSearchCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductsDao dao = factory.getProductsDao();
		String productName=reqc.getParameter("productName")[0];
		String sortNo=reqc.getParameter("sort")[0];


		switch(sortNo) {
			case "1" :
				sortNo="a.itemId ASC";
				System.out.println("番号でソート");
				break;
			case "2" :
				sortNo="price ASC";
				System.out.println("安い順でソート");
				break;
			case "3" :
				sortNo="price DESC";
				System.out.println("高い順でソート");
				break;

		}

		System.out.println(sortNo);

		ConnectionManager.getInstance().beginTransaction();

		resc.setResult(dao.getProductsSearchResult(productName,sortNo));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resc.setTargetPath("products");
		return resc;
	}
}
