package cmd.admin.product;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.products.ProductsDao;
import dbManager.ConnectionManager;

public class TransferAdminProductsPageCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		try {
			String message = reqContext.getParameter("message")[0];
			resContext.setMessage(message);
		}catch(NullPointerException e) {}

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductsDao dao = factory.getProductsDao();


		String productName="";
		String[] colorsNo= {"0"};
		reqContext.setSessionAttribute("holdAdminPageNo",1);
		reqContext.setSessionAttribute("maxAdminPageNo", dao.getProductsSearchResultCount(productName, colorsNo,"1"));

		ConnectionManager.getInstance().beginTransaction();


		resContext.setResult(dao.getAllProducts("1"));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		resContext.setTargetPath("adminProductsPage");

		return resContext;
	}

}
