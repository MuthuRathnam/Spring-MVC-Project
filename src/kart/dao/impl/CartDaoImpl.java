package kart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.http.HttpRequest;

import kart.dao.CartDao;
import kart.model.AddToCartForm;
import kart.model.AfterCartForm;
import kart.model.CartBean;
import kart.model.CartEntryBean;
import kart.model.ProductBean;

public class CartDaoImpl implements CartDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private String email = "Chakra@gmail.com";
	//HttpSession session=HttpRequest request;
	//private String email=(String) session.getAttribute(emailId);
	

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
	public boolean createCart(String email, int totalQuantity, double totalPrice) throws SQLException {
		String query = "Insert into cart(email,totalQuantity,totalPrice) Values(?,?,?)";
		PreparedStatement stmt = dataSource.getConnection().prepareStatement(query);
		stmt.setString(1, email);
		stmt.setInt(2, totalQuantity);
		stmt.setDouble(3, totalPrice);
		int result = stmt.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public CartEntryBean getExistingId(String productId,int cartId) throws SQLException {
		String query = "Select * from cartentry where productId=? and cartId=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, productId);
		pstmt.setInt(2, cartId);
		ResultSet result = pstmt.executeQuery();
		CartEntryBean existId = new CartEntryBean();
		if (result.next()) {
			existId.setQuantity(result.getInt("quantity"));
			existId.setCartEntryId(result.getInt("cartEntryId"));
			existId.setPrice(result.getDouble("price"));
		}
		return existId;
	}

	@Override
	public boolean createCartEntry(CartBean cart, AddToCartForm form, ProductBean product) throws SQLException {
		String query = "Insert into cartentry (productId,quantity,price,totalCost,cartId) Values(?,?,?,?,?)";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, form.getProductId());
		pstmt.setInt(2, form.getQuantity());
		pstmt.setDouble(3, product.getPrice());
		pstmt.setDouble(4, cart.getTotalPrice());
		pstmt.setInt(5, cart.getCartId());
		int set = pstmt.executeUpdate();
		if (set > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<CartEntryBean> sumCartEntries(int cartId) throws SQLException {
		List<CartEntryBean> data = new ArrayList<>();
		String query = "Select * from cartentry where cartId=? ";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, cartId);
		ResultSet resultSet = pstmt.executeQuery();
		while (resultSet.next()) {
			CartEntryBean bean = new CartEntryBean();
			bean.setQuantity(resultSet.getInt("quantity"));
			bean.setPrice(resultSet.getDouble("price"));
			data.add(bean);
		}
		return data;
	}

	@Override
	public boolean updateCart(int totalQuantity, double totalPrice, int cartId) throws SQLException {
		String qry = "Update cart set totalQuantity=?,totalPrice=? where cartId=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(qry);
		pstmt.setInt(1, totalQuantity);
		pstmt.setDouble(2, totalPrice);
		pstmt.setInt(3, cartId);
		int result = pstmt.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<CartEntryBean> getProductInfo(int cartId) throws SQLException {
		String query = "Select * from cartentry where cartId=?;";
		PreparedStatement stmt = dataSource.getConnection().prepareStatement(query);
		stmt.setInt(1, cartId);
		ResultSet result = stmt.executeQuery();
		List<CartEntryBean> itemIds = new ArrayList<>();
		while (result.next()) {
			CartEntryBean entries = new CartEntryBean();
			entries.setProductId(result.getString("productId"));
			itemIds.add(entries);
		}
		return itemIds;
	}

	@Override
	public List<CartEntryBean> getCartEntriesData(int cartId) throws SQLException {
		String query = "Select * from cartentry where cartId=? ";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, cartId);
		ResultSet results = pstmt.executeQuery();
		List<CartEntryBean> data = new ArrayList<>();
		while (results.next()) {
			CartEntryBean bean = new CartEntryBean();
			bean.setCartEntryId(results.getInt("cartEntryId"));
			bean.setProductId(results.getString("productId"));
			bean.setPrice(results.getDouble("price"));
			bean.setQuantity(results.getInt("quantity"));
			bean.setTotalPrice(results.getDouble("totalCost"));
			bean.setCartId(results.getInt("cartId"));
			data.add(bean);
		}
		return data;
	}

	@Override
	public boolean cartEntryUpdateQty(CartEntryBean entry) throws SQLException {
		String query = "Update cartentry set quantity=?, totalCost=? where cartEntryId=?";
		PreparedStatement stmt = dataSource.getConnection().prepareStatement(query);
		stmt.setInt(1, entry.getQuantity());
		stmt.setDouble(2, entry.getTotalPrice());
		stmt.setInt(3, entry.getCartEntryId());
		int result = stmt.executeUpdate();
		if (result > 0) {
			CartBean cart = getCartData(email);
			int totalQuantity = 0;
			double totalPrice = 0.0;
			List<CartEntryBean> data = sumCartEntries(cart.getCartId());
			for (CartEntryBean values : data) {
				totalQuantity += values.getQuantity();

				values.setTotalPrice(values.getQuantity() * values.getPrice());
				totalPrice += values.getTotalPrice();
			}

			return updateCart(totalQuantity, totalPrice, cart.getCartId());
		}
		return false;
	
	}

	@Override
	public boolean deleteCartEntry(AfterCartForm form) throws SQLException {
		String query = "DELETE FROM cartentry where cartEntryId=?";
		PreparedStatement stmt = dataSource.getConnection().prepareStatement(query);
		stmt.setInt(1, form.getCartEntryId());
		int result = stmt.executeUpdate();
		if (result > 0) {
			CartBean cart = getCartData(email);
		int totalQuantity = 0;
		double totalPrice = 0.0;
		List<CartEntryBean> data = sumCartEntries(cart.getCartId());
		for (CartEntryBean values : data) {
			totalQuantity += values.getQuantity();

			values.setTotalPrice(values.getQuantity() * values.getPrice());
			totalPrice += values.getTotalPrice();
		}

		return updateCart(totalQuantity, totalPrice, cart.getCartId());
		}
		return false;
	}


	@Override
	public boolean updateCartEntry(CartEntryBean existItemId) throws SQLException {
		String query = "Update cartentry set quantity=?, totalCost=? where cartEntryId=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, existItemId.getQuantity());
	    pstmt.setDouble(2, existItemId.getTotalPrice());
		pstmt.setInt(3, existItemId.getCartEntryId());
		int set = pstmt.executeUpdate();
		if (set > 0) {
			return true;
		}

		return false;
	}

	@Override
	public CartBean getCharges() throws SQLException {
		String query = "Select * from cart where cartId=9090";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet set=pstmt.executeQuery();
		CartBean cost=new CartBean();
		if(set.next()) {
			cost.setCharges(set.getDouble("totalPrice"));
		}
		return cost;
	}

}