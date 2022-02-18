package cmd.admin.product;

import bean.joinBean.AllProductDetailBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.admin.product.ProductEditDao;
import dbManager.ConnectionManager;

public class TransferEditProductPageCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String itemId = reqContext.getParameter("itemId")[0];

		try {
			String message = reqContext.getParameter("message")[0];
			resContext.setMessage(message);
		}catch(NullPointerException e) {}

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductEditDao edit = factory.getProductEditDao();

		ConnectionManager.getInstance().beginTransaction();

		AllProductDetailBean all = edit.getTargetProduct(itemId); // 商品データ取得

		resContext.setResult(all);
		resContext.setTargetPath("editProductPage");

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		return resContext;
	}

}
