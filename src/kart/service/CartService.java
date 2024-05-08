package kart.service;
 
import java.sql.SQLException;

import kart.model.AddToCartForm;
import kart.model.AfterCartForm;
import kart.model.CartBean;
import kart.model.CartEntryBean;
import kart.model.ProductBean;

public interface CartService {
	
	public boolean addToCart(AddToCartForm form) throws SQLException;
	
	public CartBean getCartData(String email) throws SQLException;
	
	public boolean createCart(String email,int totalQuantity,double totalPrice) throws SQLException;
	
	public boolean updateCartEntry(CartEntryBean existProductId) throws SQLException;

	public boolean createCartEntry(CartBean cart,AddToCartForm form,ProductBean product) throws SQLException;
	
	boolean updateCart(int totalQuantity,double totalPrice,int cartId) throws SQLException;
	
	public CartBean showCartData(String email) throws SQLException;
	
	public boolean cartEntryUpdateQty(CartEntryBean entry) throws SQLException;
	
	public boolean deleteCartEntry(AfterCartForm form) throws SQLException;
	
} 