package diamonShop.Dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import diamonShop.Dto.CartDto;
import diamonShop.Dto.ProductsDto;
@Repository
public class CartDao extends BaseDao {
	@Autowired
	ProductsDao productDao = new ProductsDao();
	
public HashMap<Long, CartDto> AddCart(long id,HashMap<Long, CartDto> cart) {
	CartDto itemCart = new CartDto();
	ProductsDto product = productDao.FindProductById(id);
	if(product != null && cart.containsKey(id)) {
		itemCart.setProduct(product);
		itemCart.setQuanty(itemCart.getQuanty()+1);
		itemCart.setTotalPrice(itemCart.getQuanty() * itemCart.getProduct().getPrice());
	}else {
		itemCart.setProduct(product);
		itemCart.setQuanty(1);
		itemCart.setTotalPrice(product.getPrice());
	}
	cart.put(id, itemCart);
	return cart;
}
	
public HashMap<Long, CartDto> EditCart (long id, int Quanty,HashMap<Long, CartDto> cart){
	CartDto itemCart = new CartDto();
	if(cart == null) {
		return cart;
	}
	if(cart.containsKey(id)){
		itemCart = cart.get(id);
		itemCart.setQuanty(Quanty);
		double total = Quanty * itemCart.getProduct().getPrice();
		itemCart.setTotalPrice(total);
	}
	cart.put(id, itemCart);
	return cart;
}

public HashMap<Long, CartDto> DeleteCart(long id, HashMap<Long, CartDto> cart){
	
	if(cart == null) {
		return cart;
	}
	if(cart.containsKey(id)) {
		cart.remove(id);
		
	}
	return cart;
}

public int TotalQuanty(HashMap<Long,CartDto> cart){
	int totalQuanty = 0;
	for(Map.Entry<Long, CartDto> itemCart : cart.entrySet()) {
		totalQuanty += itemCart.getValue().getQuanty();
	}
	return totalQuanty;
}

public double TotalPrice(HashMap<Long,CartDto> cart) {
	double totalPrice = 0;
	for(Map.Entry<Long, CartDto> itemCart: cart.entrySet()) {
		totalPrice += itemCart.getValue().getTotalPrice();
	}
	return totalPrice;
}
}
