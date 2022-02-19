package cmd.admin.product;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.admin.product.ProductEditDao;
import dbManager.ConnectionManager;

public class DeleteProductsCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String itemId = reqContext.getParameter("itemIdList")[0];

		String[] itemIdArray = itemId.split(",");

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductEditDao edit = factory.getProductEditDao();

		ConnectionManager.getInstance().beginTransaction();

		String commandPath = reqContext.getOneBeforeLocation() + "&message="; // 消す前にいた場所

		if(edit.deleteProduct(itemIdArray)) {
			commandPath += "消去完了";

			resContext.setTargetCommandPath(commandPath);

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();
		}else {
			commandPath += "消去失敗";

			resContext.setTargetCommandPath(commandPath);

			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();
		}

		return resContext;
	}

}
