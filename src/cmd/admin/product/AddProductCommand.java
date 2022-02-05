package cmd.admin.product;

import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.admin.product.ProductEditDao;
import dbManager.ConnectionManager;

public class AddProductCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();

		String productName = reqContext.getParameter("productName")[0];
		String price = reqContext.getParameter("price")[0];
		String categoryId = reqContext.getParameter("categoryId")[0];
		String colorId = reqContext.getParameter("colorId")[0];
		String shapeId = reqContext.getParameter("shapeId")[0];
		String artistId = reqContext.getParameter("artistId")[0];

		String pictPath = "";

		if(reqContext.uploadFile()) { // 写真のアップロード
			String fileName = reqContext.getPictFileName(); // ファイル名の取得
			System.out.println(fileName);

			pictPath = "images/" + fileName;
		}
		else { // アップロードファイルが既に存在していた時の処理
			resContext.setMessage("その写真はすでにアップロードされているか予期せぬ事態が起き追加できませんでした");
			resContext.setTargetPath("addProductPage");
			return resContext;
		}


		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductEditDao edit = factory.getProductEditDao();

		ConnectionManager.getInstance().beginTransaction();

		if(edit.addProduct(productName, price, categoryId, colorId, shapeId, artistId, pictPath)) {
			resContext.setMessage("製品追加完了");
			resContext.setTargetPath("addProductComplete");


			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();
		}
		else {
			resContext.setMessage("その商品は既に存在しているため追加できません");
			resContext.setTargetPath("addProductPage");

			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();
		}

		resContext.setTargetPath("addProductPage");

		return resContext;
	}

}

