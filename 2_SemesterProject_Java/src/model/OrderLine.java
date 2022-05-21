package model;

public class OrderLine {
	private StoneProduct stoneProduct;
	private double price;

	public OrderLine(StoneProduct stoneProduct, double price) {
		super();
		this.stoneProduct = stoneProduct;
		this.price = price;
	}

	public StoneProduct getStoneProduct() {
		return stoneProduct;
	}

	public void setStoneProduct(StoneProduct stoneProduct) {
		this.stoneProduct = stoneProduct;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderLine [stoneProduct=" + stoneProduct + ", price=" + price + "]";
	}

}
