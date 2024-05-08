package kart.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kart.dao.CartDao;
import kart.dao.ProductDao;
import kart.model.CartBean;
import kart.model.CartEntryBean;
import kart.model.ProductBean;
import kart.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	private CartDao cartDao;

	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	@Override
	public List<ProductBean> productList(int id) throws SQLException {
		return productDao.productList(id);
	}

	@Override
	public ProductBean productInfo(String productId) throws SQLException {
		return productDao.productInfo(productId);
	}

	@Override
	public List<CartEntryBean> getProductId(int cartId) throws SQLException {
		List<CartEntryBean> beans = new ArrayList<>();
		CartBean cart = cartDao.getCartData("Chakra@gmail.com");
		List<CartEntryBean> ids = cartDao.getProductInfo(cart.getCartId());
		for (CartEntryBean value : ids) {
			String productId = value.getProductId();
			productDao.productInfo(productId);
		}
		return beans;
	}

}