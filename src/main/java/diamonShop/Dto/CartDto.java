package diamonShop.Dto;

public class CartDto {
	private int quanty;
	private double TotalPrice;
	private ProductsDto product;

	public CartDto(int quanty, double totalPrice, ProductsDto product) {
		super();
		this.quanty = quanty;
		TotalPrice = totalPrice;
		this.product = product;
	}

	public CartDto() {

	}

	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

	public double getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		TotalPrice = totalPrice;
	}

	public ProductsDto getProduct() {
		return product;
	}

	public void setProduct(ProductsDto product) {
		this.product = product;
	}

}
