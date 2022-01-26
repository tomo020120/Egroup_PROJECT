package dao.history;

import java.util.List;

import bean.joinBean.AllBrowsingHistoryInfoBean;

public interface HistoryDao {
	public void addProductHistory(String userId, String itemId);;
	public List<AllBrowsingHistoryInfoBean> getProductHistory(String userId);
	public void deleteProductHistory(String userId,String itemId);
}
