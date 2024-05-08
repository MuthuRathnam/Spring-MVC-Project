package kart.dao;

import java.sql.SQLException;

import kart.model.CartBean;
import kart.model.OrderBean;

public interface OrderDao {

	public boolean setDeliveryAddress(OrderBean orders) throws SQLException;
	
	public CartBean getCartData(String email) throws SQLException;

	public boolean setPaymentMethod(OrderBean bean) throws SQLException;

	public OrderBean getorderId(String email) throws SQLException;

}
