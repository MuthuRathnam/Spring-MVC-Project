package kart.model;

import java.sql.Timestamp;

public class OrderBean {
	private int orderId;
	private String userId;
	private int cartId;
	private String payType;
	private double charges;
	private Timestamp date;
	private String address;
	private double totalCost;

	private CartBean cart;
	private UserBean users;

	public CartBean getCart() {
		return cart;
	}

	public void setCart(CartBean cart) {
		this.cart = cart;
	}

	public UserBean getUsers() {
		return users;
	}

	public void setUsers(UserBean users) {
		this.users = users;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

}