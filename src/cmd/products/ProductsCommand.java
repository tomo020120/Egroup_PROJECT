package cmd.products;


/*
 *各インポート
 */
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.products.ProductsDao;
import dbManager.ConnectionManager;

public class ProductsCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductsDao dao = factory.getProductsDao();
		RequestContext reqc = getRequestContext();
		String categoryId = reqc.getParameter("categoryId")[0];

		String productName="";
		String[] colorsNo= {"0"};
		reqc.setSessionAttribute("holdPageNo",1);
		reqc.setSessionAttribute("maxPageNo", dao.getProductsSearchResultCount(productName, colorsNo,categoryId));


		ConnectionManager.getInstance().beginTransaction();

		resc.setResult(dao.getAllProducts(categoryId));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resc.setTargetPath("products");

		return resc;
	}
}
