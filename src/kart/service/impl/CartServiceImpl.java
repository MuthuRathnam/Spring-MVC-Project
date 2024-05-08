package kart.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import kart.dao.CartDao;
import kart.dao.ProductDao;
import kart.dao.UserDao;
import kart.model.AddToCartForm;
import kart.model.AfterCartForm;
import kart.model.CartBean;
import kart.model.CartEntryBean;
import kart.model.ProductBean;
import kart.model.UserBean;
import kart.service.CartService;

public class CartServiceImpl implements CartService {

	private CartDao cartDao;

	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	private String email="Chakra@gmail.com";

	//HttpSession session;
	//private String email=(String) session.getAttribute("emailId");
	
	public boolean addToCart(AddToCartForm form) throws SQLException {

		int totalQuantity = 0; double totalPrice = 0;

		ProductBean product = new ProductBean();
		CartBean cart = getCartData(email);
		if (cart != null) {

		} else {
			boolean result = createCart(email, totalQuantity, totalPrice);
		}

		product = productDao.productInfo(form.getProductId());

		CartEntryBean existingId = cartDao.getExistingId(form.getProductId(), cart.getCartId());

		if (existingId != null && existingId.getPrice() > 0) {

			existingId.setQuantity(existingId.getQuantity() + (form.getQuantity()));
			existingId.setTotalPrice(existingId.getQuantity() * existingId.getPrice());
			boolean set = updateCartEntry(existingId);
			
		} else {

			boolean results = createCartEntry(cart, form, product);
		}

		boolean set = updateCart(totalQuantity, totalPrice, cart.getCartId());
		if (set) {

		}
		return true;
	}

	@Override
	public CartBean getCartData(String email) throws SQLException {
		return cartDao.getCartData(email);
	}

	@Override
	public boolean createCart(String email, int totalQuantity, double totalPrice) throws SQLException {
		return cartDao.createCart(email, totalQuantity, totalPrice);
	}

	@Override
	public boolean updateCartEntry(CartEntryBean existingId) throws SQLException {
		return cartDao.updateCartEntry(existingId);
	}

	@Override
	public boolean createCartEntry(CartBean cart, AddToCartForm form, ProductBean product) throws SQLException {
		cart.setTotalPrice(form.getQuantity() * product.getPrice());
		return cartDao.createCartEntry(cart, form, product);
	}

	@Override

	public boolean updateCart(int totalQuantity, double totalPrice, int cartId) throws SQLException {

		List<CartEntryBean> data = cartDao.sumCartEntries(cartId);
		for (CartEntryBean values : data) {
			
			totalQuantity += values.getQuantity();

			values.setTotalPrice(values.getQuantity() * values.getPrice());
			totalPrice += values.getTotalPrice();
		}
		return cartDao.updateCart(totalQuantity, totalPrice, cartId);
	}

	public CartBean showCartData(String email) throws SQLException {

		CartBean cart = cartDao.getCartData(email);
         	
		List<CartEntryBean> cartEntryDatas = cartDao.getCartEntriesData(cart.getCartId());

		for (CartEntryBean cartEntryData : cartEntryDatas) {
			ProductBean itemData = productDao.productInfo(cartEntryData.getProductId());
			cartEntryData.setProduct(itemData);
		}
		
		cart.setEntries(cartEntryDatas);
	
		UserBean user = userDao.userInfo(email);

		cart.setUserData(user);

		return cart;
	}

	@Override
	public boolean cartEntryUpdateQty(CartEntryBean entry) throws SQLException {
		entry.setTotalPrice(entry.getQuantity() * entry.getPrice());
		return cartDao.cartEntryUpdateQty(entry);
	}

	@Override
	public boolean deleteCartEntry(AfterCartForm form) throws SQLException {
		return cartDao.deleteCartEntry(form);
	}
}