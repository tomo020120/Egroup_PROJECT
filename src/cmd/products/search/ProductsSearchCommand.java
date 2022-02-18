package cmd.products.search;

import bean.HoldWordBean;
import cmd.AbstractCommand;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.products.ProductsDao;
import dbManager.ConnectionManager;


public class ProductsSearchCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc=getRequestContext();
		String categoryId = reqc.getParameter("categoryId")[0];

		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
		ProductsDao dao = factory.getProductsDao();
		String productName=reqc.getParameter("productName")[0];
		String sortNo=reqc.getParameter("sort")[0];
		//String colorNo=reqc.getParameter("color")[0];
		String pageNo = reqc.getParameter("pageNo")[0];

		//colorが複数の場合も登録
		String[] colorsNo=reqc.getParameter("color");

		HoldWordBean hwb = new HoldWordBean();

		reqc.setSessionAttribute("holdSearchWord", productName);
		reqc.setSessionAttribute("holdsortNo", sortNo);
		reqc.setSessionAttribute("holdColorsNo",colorsNo);
		reqc.setSessionAttribute("holdPageNo",pageNo);


		hwb.setHoldWord((String)reqc.getSessionAttribute("holdSearchWord"));
		hwb.setHoldSortNo((String)reqc.getSessionAttribute("holdsortNo"));


		System.out.println("ホールド値１："+reqc.getSessionAttribute("holdSearchWord"));
		System.out.println("ホールド値２："+reqc.getSessionAttribute("holdSortNo"));


		switch(sortNo) {
			case "0" :
				sortNo="a.itemId ASC";
				System.out.println("番号でソート");
				break;
			case "1" :
				sortNo="price ASC";
				System.out.println("安い順でソート");
				break;
			case "2" :
				sortNo="price DESC";
				System.out.println("高い順でソート");
				break;

		}

		System.out.println(sortNo);

		ConnectionManager.getInstance().beginTransaction();

		reqc.setSessionAttribute("maxPageNo", dao.getProductsSearchResultCount(productName, colorsNo,categoryId));

		//検索実行＋結果取得
		resc.setResult(dao.getProductsSearchResult(productName,sortNo,colorsNo,pageNo,categoryId));

		ConnectionManager.getInstance().commit();
		ConnectionManager.getInstance().closeTransaction();

		String commandKey = reqc.getCommandKey();

		if(commandKey.equals("searchByAdmin")) {
			resc.setTargetPath("productEditPage");
		}else {
			resc.setTargetPath("products");
		}

		return resc;
	}
}
