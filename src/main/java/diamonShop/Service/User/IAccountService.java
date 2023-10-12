package diamonShop.Service.User;

import org.springframework.stereotype.Service;

import diamonShop.Entity.Users;
@Service
public interface IAccountService {
	public int AddAccount(Users user);
	
	public Users CheckUser(Users user);
}
