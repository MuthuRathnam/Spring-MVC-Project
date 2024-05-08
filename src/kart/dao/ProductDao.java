package kart.dao;

import java.sql.SQLException;
import java.util.List;

import kart.model.ProductBean;

public interface ProductDao {

	List<ProductBean> productList(int id) throws SQLException;

	public ProductBean productInfo(String productId) throws SQLException;	

}
