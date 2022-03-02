package cmd.admin.product;

import java.io.File;

import bean.joinBean.AllProductDetailBean;
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
		String pictPath = "";

		if(reqContext.uploadFile()) { // 写真のアップロードもしもう写真があるならfalse
			String fileName = reqContext.getPictFileName(); // ファイル名の取得
			System.out.println(fileName);

			pictPath = "images/" + fileName;
		}
		else { // アップロードファイルが既に存在していた時の処理
			resContext.setMessage("予期せぬ事態が起き追加できませんでした");
			resContext.setTargetPath("addProductPage");
			return resContext;
		}

		String productName = reqContext.getParameter("productName")[0];
		String price = reqContext.getParameter("price")[0];
		String categoryId = reqContext.getParameter("categoryId")[0];
		String colorId = reqContext.getParameter("colorId")[0];
		String artistId = reqContext.getParameter("artistId")[0];

		AllProductDetailBean all = new AllProductDetailBean();

		all.setName(productName);
		all.setPrice(price);
		all.setCategoryId(categoryId);
		all.setColorId(colorId);
		all.setArtistId(artistId);
		all.setPictPath(pictPath);

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductEditDao edit = factory.getProductEditDao();

		ConnectionManager.getInstance().beginTransaction();

		if(edit.addProduct(all)) {
			resContext.setMessage("製品追加完了");
			resContext.setTargetPath("addProductComplete");


			ConnectionManager.getInstance().commit();
			ConnectionManager.getInstance().closeTransaction();
		}
		else {
			File imageFile = new File(reqContext.getAbsoluteFilePath()); //追加に失敗したらアップロードしたファイルをフォルダから消す
			imageFile.delete();

			resContext.setMessage("存在していない番号の指定または、同じ製品名の商品がすでに存在しているため追加できませんでした。");
			resContext.setTargetPath("addProductPage");

			ConnectionManager.getInstance().rollback();
			ConnectionManager.getInstance().closeTransaction();
		}

		resContext.setTargetPath("addProductPage");

		return resContext;
	}

}

