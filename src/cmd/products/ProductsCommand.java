package cmd.products;


/*
 *各インポート
 */
import cmd.AbstractCommand;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.products.ProductsDao;
import dbManager.ConnectionManager;

public class ProductsCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductsDao dao = factory.getProductsDao();

		ConnectionManager.getInstance().beginTransaction();

		resc.setResult(dao.getAllProducts());

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resc.setTargetPath("products");

		return resc;
	}
}
