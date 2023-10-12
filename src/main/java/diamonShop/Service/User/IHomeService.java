package diamonShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diamonShop.Dto.ProductsDto;
import diamonShop.Entity.Categorys;
import diamonShop.Entity.Menus;
import diamonShop.Entity.Slides;
@Service
public interface IHomeService {
	@Autowired
public List<Slides> GetdataSlides();
public List<Categorys> GetdataCategorys();
public List<Menus> GetdataMenus();
public List<ProductsDto> GetdataProducts();
public List<ProductsDto> GetdatanewProducts();
}
