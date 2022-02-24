package cmd.admin.product;

import java.io.File;

import bean.joinBean.AllProductDetailBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.admin.product.ProductEditDao;
import dbManager.ConnectionManager;

public class EditProductCommand extends AbstractCommand{

	@Override
	public ResponseContext execute(ResponseContext resContext) {
		RequestContext reqContext = getRequestContext();
		String updatePictPath = "";
		String exitingPictPath = "";
		String commandPath = "";
		String itemId = "";
		if(reqContext.uploadFile()) { // 写真のアップロードもしもう写真があるならfalse、新しく写真を更新するなら保存と前の画像の消去
			exitingPictPath = reqContext.getParameter("exitPictPath")[0]; // 更新前の写真のパス
			reqContext.deletePictFile(exitingPictPath); // 写真の消去
			String fileName = reqContext.getPictFileName(); // ファイル名の取得
			System.out.println(fileName);
			updatePictPath = "images/" + fileName;
		}else if(reqContext.isNoFile()){ // ファイルがあげられていない時true
			updatePictPath = reqContext.getParameter("exitPictPath")[0]; // 更新前の写真のパス
		}else { // ファイルが存在していた時、または例外
			itemId = reqContext.getParameter("itemId")[0];
			commandPath = "editProductPage?itemId=" + itemId + "&message=編集に失敗しました";
			resContext.setTargetCommandPath(commandPath);
			return resContext;
		}

		String productName = reqContext.getParameter("productName")[0];
		String price = reqContext.getParameter("price")[0];
		String categoryId = reqContext.getParameter("categoryId")[0];
		String colorId = reqContext.getParameter("colorId")[0];
		String artistId = reqContext.getParameter("artistId")[0];
		itemId = reqContext.getParameter("itemId")[0];

		AllProductDetailBean all = new AllProductDetailBean();

		all.setName(productName);
		all.setPrice(price);
		all.setCategoryId(categoryId);
		all.setColorId(colorId);
		all.setArtistId(artistId);
		all.setItemId(itemId);
		all.setPictPath(updatePictPath);

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductEditDao edit = factory.getProductEditDao();

		ConnectionManager.getInstance().beginTransaction();


		if(edit.updateProduct(all)) {
			commandPath = "adminProductsPage?message=編集完了";

			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();
		}
		else {
			File imageFile = new File(reqContext.getAbsoluteFilePath()); //編集に失敗したらアップロードしたファイルをフォルダから消す
			imageFile.delete();

			commandPath = "editProductPage?itemId=" + itemId + "&message=編集に失敗しました";

			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();
		}

		resContext.setTargetCommandPath(commandPath);

		return resContext;
	}

}
