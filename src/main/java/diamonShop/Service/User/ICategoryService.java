package diamonShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diamonShop.Dto.ProductsDto;
@Service
public interface ICategoryService {
	@Autowired
	public List<ProductsDto> GetAllProductById(int id);
	@Autowired
	public List<ProductsDto> GetDataProductPaginate(int id,int start, int end);

}
