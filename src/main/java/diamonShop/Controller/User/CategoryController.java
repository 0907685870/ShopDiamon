package diamonShop.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import diamonShop.Dto.PaginatesDto;
import diamonShop.Service.User.CategoryserviceImpl;
import diamonShop.Service.User.PaginatesServiceImpl;

@Controller
public class CategoryController extends BaseController {
	@Autowired
	private CategoryserviceImpl categoryService;
	@Autowired
	private PaginatesServiceImpl paginatesService;

	private int totalProductsPage = 9;
	@RequestMapping(value = "/san-pham/{id}")
	public ModelAndView Category(@PathVariable String id) {

		_mvShare.setViewName("user/products/category");
		int totalData = categoryService.GetAllProductById(Integer.parseInt(id)).size();
		PaginatesDto paginateInfo = paginatesService.GetInfoPaginates(totalData, totalProductsPage ,1);
		_mvShare.addObject("idCategory", id);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("ProductsPaginate",categoryService.GetDataProductPaginate(Integer.parseInt(id),paginateInfo.getStart(),totalProductsPage));
		return _mvShare;
	}
	
	@RequestMapping(value = "/san-pham/{id}/{currentPage}")
	public ModelAndView Category(@PathVariable String id, @PathVariable String currentPage) {
		_mvShare.setViewName("user/products/category");
		int totalData = categoryService.GetAllProductById(Integer.parseInt(id)).size();
		PaginatesDto paginateInfo = paginatesService.GetInfoPaginates(totalData, totalProductsPage ,Integer.parseInt(currentPage));
		_mvShare.addObject("idCategory", id);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("ProductsPaginate",categoryService.GetDataProductPaginate(Integer.parseInt(id),paginateInfo.getStart(), totalProductsPage));
		return _mvShare;
	}
}
