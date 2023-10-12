package diamonShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diamonShop.Dao.ProductsDao;
import diamonShop.Dto.ProductsDto;
@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private ProductsDao productDao = new ProductsDao();
	
	@Override
	public ProductsDto GetProductById(long id) {
		List<ProductsDto> listProducts = productDao.GetProductById(id);
		return listProducts.get(0);
	}

	@Override
	public List<ProductsDto> GetProductByIdCategory(int id) {
		return productDao.GetAllProductsById(id);
	}

}
