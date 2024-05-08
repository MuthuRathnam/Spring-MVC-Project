package kart.service.impl;

import java.sql.SQLException;
import java.util.List;

import kart.dao.CartDao;
import kart.dao.OrderDao;
import kart.dao.ProductDao;
import kart.dao.UserDao;
import kart.model.CartBean;
import kart.model.CartEntryBean;
import kart.model.OrderBean;
import kart.model.ProductBean;
import kart.model.UserBean;
import kart.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

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

	@Override
	public boolean setDeliveryAddress(OrderBean orders) throws SQLException {
		return orderDao.setDeliveryAddress(orders);
	}

	@Override
	public boolean setPaymentMethod(OrderBean bean) throws SQLException {
		return orderDao.setPaymentMethod(bean);
	}

	@Override
	public CartBean getCartData(String email) throws SQLException {
		return orderDao.getCartData(email);
	}

	@Override
	public OrderBean getOrderDetails(String email) throws SQLException {

		    OrderBean order = orderDao.getorderId(email);

			CartBean cart = cartDao.getCartData(email);

			CartBean cost = cartDao.getCharges();

			cart.setCharges(cost.getCharges());
			
			order.setCharges(cart.getTotalPrice()+cost.getCharges());

			List<CartEntryBean> cartEntryDatas = cartDao.getCartEntriesData(cart.getCartId());

			for (CartEntryBean cartEntryData : cartEntryDatas) {
				ProductBean itemData = productDao.productInfo(cartEntryData.getProductId());
				cartEntryData.setProduct(itemData);
			}
			cart.setEntries(cartEntryDatas);

			order.setCart(cart);

			UserBean user = userDao.userInfo(email);

			order.setUsers(user);

			order.setTotalCost(cart.getTotalPrice() + order.getCharges());

			return order;
		}

	
}