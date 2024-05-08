package kart.service;

import java.sql.SQLException;
import java.util.List;

import kart.model.CartEntryBean;
import kart.model.ProductBean;

public interface ProductService {

	 List<ProductBean> productList(int id) throws SQLException;
	    
	 public ProductBean productInfo(String productId) throws SQLException;
	 
	 public List<CartEntryBean> getProductId(int cartId) throws SQLException;
	
}
