package model;

public class OrderLine {
	private StoneProduct stoneProduct;

	public OrderLine(StoneProduct stoneProduct) {
		super();
		this.stoneProduct = stoneProduct;
	}

	public StoneProduct getStoneProduct() {
		return stoneProduct;
	}

	public void setStoneProduct(StoneProduct stoneProduct) {
		this.stoneProduct = stoneProduct;
	}

	@Override
	public String toString() {
		return "OrderLine [stoneProduct=" + stoneProduct + "]";
	}

}
