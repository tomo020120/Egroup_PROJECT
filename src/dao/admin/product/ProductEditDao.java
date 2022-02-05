package dao.admin.product;

public interface ProductEditDao {
	public abstract int isExsitsProductName(String productName);
	public abstract boolean addProduct(String productName,String price,String categoryId,String colorId,String shapeId,String artistId,String pictPath);
	public abstract boolean updateProduct(String productName,String price,String categoryId,String colorId,String shapeId,String artistId,String pictPath,String itemId);
	public abstract boolean deleteProduct(String itemId);
}
