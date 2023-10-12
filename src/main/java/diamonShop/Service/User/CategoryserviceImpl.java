package diamonShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diamonShop.Dao.ProductsDao;
import diamonShop.Dto.ProductsDto;
@Service
public class CategoryserviceImpl implements ICategoryService {
	@Autowired
	ProductsDao productsDao;
	@Override
	public List<ProductsDto> GetAllProductById(int id) {
		return productsDao.GetAllProductsById(id);
	}

	@Override
	public List<ProductsDto> GetDataProductPaginate(int id, int start,int totalPage) {
		
		return productsDao.GetDataProductPaginate(id,start, totalPage);
	}


	
	

}
