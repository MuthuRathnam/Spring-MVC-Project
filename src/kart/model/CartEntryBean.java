package kart.model;

public class CartEntryBean {

	private int cartEntryId;
	private int cartId;
	private int quantity;
	private double price;
	private double totalPrice;
	private String productId;
	private String payType;
	private double charges;

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

	private ProductBean product;

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	public int getCartEntryId() {
		return cartEntryId;
	}

	public void setCartEntryId(int cartEntryId) {
		this.cartEntryId = cartEntryId;
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (product != null) {
			product.setPrice(price);
		}
		this.price = price;
	}

	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}