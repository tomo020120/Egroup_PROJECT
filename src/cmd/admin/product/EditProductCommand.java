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
		if(reqContext.uploadFile()) { // 写真のアップロードもしもう写真があるならfalse、新しく写真を更新するなら保存と前の画像の消去
			exitingPictPath = reqContext.getParameter("exitPictPath")[0]; // 更新前の写真のパス
			if(reqContext.deletePictFile(exitingPictPath)) {// 更新前の写真の消去
				String fileName = reqContext.getPictFileName(); // ファイル名の取得
				System.out.println(fileName);

				updatePictPath = "images/" + fileName;
			}
		}else if(reqContext.isNoFile()){ // ファイルがあげられていない時true
			updatePictPath = reqContext.getParameter("exitPictPath")[0]; // 更新前の写真のパス
		}else { // ファイルが存在していた時、または例外
			resContext.setMessage("その写真はすでにアップロードされているか予期せぬ事態が起き追加できませんでした");
			resContext.setTargetPath("editProductPage");
			return resContext;
		}

		String productName = reqContext.getParameter("productName")[0];
		String price = reqContext.getParameter("price")[0];
		String categoryId = reqContext.getParameter("categoryId")[0];
		String colorId = reqContext.getParameter("colorId")[0];
		String shapeId = reqContext.getParameter("shapeId")[0];
		String artistId = reqContext.getParameter("artistId")[0];
		String itemId = reqContext.getParameter("itemId")[0];

		AllProductDetailBean all = new AllProductDetailBean();

		all.setName(productName);
		all.setPrice(price);
		all.setCategoryId(categoryId);
		all.setColorId(colorId);
		all.setShapeId(shapeId);
		all.setArtistId(artistId);
		all.setItemId(itemId);
		all.setPictPath(updatePictPath);

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductEditDao edit = factory.getProductEditDao();

		ConnectionManager.getInstance().beginTransaction();

		String commandPath = "";

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
