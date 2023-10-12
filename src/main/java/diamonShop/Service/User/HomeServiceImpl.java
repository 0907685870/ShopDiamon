package diamonShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diamonShop.Entity.Categorys;
import diamonShop.Entity.Menus;
import diamonShop.Entity.Slides;
import diamonShop.Dao.CategorysDao;
import diamonShop.Dao.MenusDao;
import diamonShop.Dao.ProductsDao;
import diamonShop.Dao.SlidesDao;

import diamonShop.Dto.ProductsDto;

@Service
public class HomeServiceImpl implements IHomeService {
	@Autowired
	private SlidesDao slidesDao;
	@Autowired
	private CategorysDao categorysDao;
	@Autowired
	private MenusDao menusDao;
	@Autowired
	private ProductsDao productsDao;
	@Override
	
	public List<Slides> GetdataSlides() {

		return slidesDao.GetdataSlides();
	}

	@Override
	public List<Categorys> GetdataCategorys() {
		return categorysDao.GetdataCategorys();
	}

	@Override
	public List<Menus> GetdataMenus() {
		return menusDao.GetdataMenus();
	}

	@Override
	public List<ProductsDto> GetdataProducts() {
		List<ProductsDto> listProducts = productsDao.GetdataProducts();
		return listProducts;
	}

	@Override
	public List<ProductsDto> GetdatanewProducts() {
		List<ProductsDto> listProducts = productsDao.GetdatanewProducts();
		return listProducts;
	}




}
