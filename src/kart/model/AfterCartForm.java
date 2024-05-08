package kart.model;

public class AfterCartForm {
	
	private int cartId;
	private int quantity;
	private int cartEntryId;
	private String productId;
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCartEntryId() {
		return cartEntryId;
	}
	public void setCartEntryId(int cartEntryId) {
		this.cartEntryId = cartEntryId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	

}
