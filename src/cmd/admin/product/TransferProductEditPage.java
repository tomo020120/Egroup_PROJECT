package cmd.admin.product;

import cmd.AbstractCommand;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.products.ProductsDao;
import dbManager.ConnectionManager;

public class TransferProductEditPage extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductsDao dao = factory.getProductsDao();

		ConnectionManager.getInstance().beginTransaction();

		resContext.setResult(dao.getAllProducts("1"));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resContext.setTargetPath("productEditPage");

		return resContext;
	}

}
