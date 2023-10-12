package diamonShop.Controller.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import diamonShop.Service.User.IProductService;

@Controller
public class ProductsController extends BaseController {
	@Autowired
	private IProductService _iproductService;
 @RequestMapping(value = { "chi-tiet-san-pham/{id}" })
 public ModelAndView Index(@PathVariable long id) {
	 _mvShare.setViewName("user/products/product");
	 _mvShare.addObject("product", _iproductService.GetProductById(id));
	 int idCategory = _iproductService.GetProductById(id).getId_category();
	 _mvShare.addObject("productByIDCategory", _iproductService.GetProductByIdCategory(idCategory));
	 return _mvShare;
 }
 
}
