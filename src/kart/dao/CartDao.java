
package kart.dao;

import java.sql.SQLException;
import java.util.List;

import kart.model.AddToCartForm;
import kart.model.AfterCartForm;
import kart.model.CartBean;
import kart.model.CartEntryBean;
import kart.model.ProductBean;

public interface CartDao {
	
	public CartBean getCharges() throws SQLException;
	
	public CartBean getCartData(String email) throws SQLException;
	
	public boolean createCart(String email,int totalQuantity,double totalPrice) throws SQLException;
	
	public boolean updateCartEntry(CartEntryBean existItemId) throws SQLException;

   	public boolean createCartEntry(CartBean cart1,AddToCartForm form, ProductBean product) throws SQLException;
	
	public List<CartEntryBean> sumCartEntries(int cartId) throws SQLException;

	public boolean updateCart(int totalQuantity,double totalPrice, int cartId) throws SQLException;
	
	public List<CartEntryBean> getProductInfo(int cartId) throws SQLException;
	
	public List<CartEntryBean> getCartEntriesData(int cartId) throws SQLException;
	
	public boolean cartEntryUpdateQty(CartEntryBean entry) throws SQLException;

	public boolean deleteCartEntry(AfterCartForm form) throws SQLException;
	
	public CartEntryBean getExistingId(String productId,int cartId) throws SQLException;
	

	}