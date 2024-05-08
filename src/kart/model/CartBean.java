package kart.model;

import java.util.List;

public class CartBean {

	private String email;
	public int totalQuantity;
	public double totalPrice;
	private int cartId;
    private double charges;
    
	private UserBean userData;

	public UserBean getUserData() {
		return userData;
	}

	public void setUserData(UserBean userData) {
		this.userData = userData;
	}

	private OrderBean order;

	public OrderBean getOrder() {
		return order;
	}

	public void setOrder(OrderBean order) {
		this.order = order;
	}

	private List<CartEntryBean> entries;

	public List<CartEntryBean> getEntries() {
		return entries;
	}

	public void setEntries(List<CartEntryBean> entries) {
		this.entries = entries;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}
}