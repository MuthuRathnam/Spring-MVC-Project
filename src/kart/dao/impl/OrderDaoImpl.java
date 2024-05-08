package kart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import kart.dao.OrderDao;
import kart.model.CartBean;
import kart.model.OrderBean;

public class OrderDaoImpl implements OrderDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean setDeliveryAddress(OrderBean orders) throws SQLException {
		String query = "Insert into orderdata (`userId`,`address`) Values(?,?)";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, orders.getUserId());
		pstmt.setString(2, orders.getAddress());
		int results = pstmt.executeUpdate();
		if (results > 0) {
			return true;
		}

		return false;
	}

	@Override
	public CartBean getCartData(String email) throws SQLException {
		String query = "Select * from cart where email=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, email);
		ResultSet result = pstmt.executeQuery();
		CartBean cart = new CartBean();
		if (result.next()) {
			cart.setCartId(result.getInt("cartId"));
			cart.setEmail(result.getString("email"));
			cart.setTotalPrice(result.getDouble("totalPrice"));
			cart.setTotalQuantity(result.getInt("totalQuantity"));
		}
		return cart;
	}

	@Override
	public boolean setPaymentMethod(OrderBean bean) throws SQLException {
		String query = "Update orderdata set orderdate=?,paytype=?,charges=? where userId=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setTimestamp(1, bean.getDate());
		pstmt.setString(2, bean.getPayType());
		pstmt.setDouble(3, bean.getCharges());
		pstmt.setString(4, bean.getUserId());
		int results = pstmt.executeUpdate();
		if (results > 0) {
			return true;
		}
		return false;
	}

	@Override
	public OrderBean getorderId(String email) throws SQLException {
		String query = "Select * from orderdata where userId=?";
		PreparedStatement stmt = dataSource.getConnection().prepareStatement(query);
		stmt.setString(1, email);
		ResultSet resultSet = stmt.executeQuery();
		OrderBean order = new OrderBean();
		if (resultSet.next()) {
			order.setOrderId(resultSet.getInt("orderId"));
			order.setUserId(resultSet.getString("userId"));
			order.setDate(resultSet.getTimestamp("orderdate"));
			order.setPayType(resultSet.getString("paytype"));
			order.setCharges(resultSet.getDouble("charges"));
			order.setAddress(resultSet.getString("address"));
		}
		return order;
	}

}