package cmd.products;


/*
 *各インポート 
 */
import cmd.AbstractCommand;
import context.ResponseContext;
import context.WebResponseContext;
import dao.AbstractDaoFactory;
import dao.products.ProductsDao;

public class ProductsCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		resc = new WebResponseContext();
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductsDao dao = factory.getProductsDao();
		resc.setResult(dao.getAllProducts());
		resc.setTargetPath("products");
		return resc;
	}
}
