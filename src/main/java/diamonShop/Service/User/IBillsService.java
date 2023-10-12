package diamonShop.Service.User;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import diamonShop.Dto.CartDto;
import diamonShop.Entity.Bills;
@Service
public interface IBillsService {
	public int AddBills(Bills bill);
	public void AddBillsDetail(HashMap<Long, CartDto> carts);
}
