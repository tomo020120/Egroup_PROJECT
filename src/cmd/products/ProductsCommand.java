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
		RequestContext reqContext = getRequestContext();
		String categoryId = reqContext.getParameter("categoryId")[0];

		ConnectionManager.getInstance().beginTransaction();

		resc.setResult(dao.getAllProducts(categoryId));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resc.setTargetPath("products");

		return resc;
	}
}
