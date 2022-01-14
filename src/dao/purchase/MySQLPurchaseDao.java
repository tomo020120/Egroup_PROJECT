package dao.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLPurchaseDao implements PurchaseDao{
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

}
