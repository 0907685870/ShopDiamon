package diamonShop.Controller.User;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import diamonShop.Dto.CartDto;
import diamonShop.Entity.Bills;
import diamonShop.Entity.Users;
import diamonShop.Service.User.BillsServiceImpl;
import diamonShop.Service.User.CartServiceImpl;

@Controller
public class CartController extends BaseController {
	@Autowired
	CartServiceImpl Cartservice = new CartServiceImpl();

	@Autowired
	BillsServiceImpl billsservice = new BillsServiceImpl();
	@RequestMapping(value = "/gio-hang")
	public ModelAndView Index() {
		_mvShare.addObject("slides", _homeService.GetdataSlides());		
		_mvShare.addObject("categorys", _homeService.GetdataCategorys());
		_mvShare.addObject("products", _homeService.GetdataProducts());
		_mvShare.setViewName("user/cart/list_cart");
		return _mvShare;
	}
	
	@RequestMapping(value = "AddCart/{id}")
	public String AddCart(HttpServletRequest request,HttpSession session, @PathVariable long id) {
		HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("Cart");
		if(cart == null) {
			cart = new HashMap<Long,CartDto>();
		}
		cart = Cartservice.AddCart(id, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalPriceCart", Cartservice.TotalPrice(cart));
		session.setAttribute("TotalQuantyCart", Cartservice.TotalQuanty(cart));
		return "redirect:"+request.getHeader("Referer");
	}
	
	
	@RequestMapping(value = "DeleteCart/{id}")
	public String DeleteCart(HttpServletRequest request, HttpSession session,@PathVariable long id ) {
		HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("Cart");
		if(cart == null) {
			cart = new HashMap<Long, CartDto>();
		}
		cart = Cartservice.DeleteCart(id, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalPriceCart", Cartservice.TotalPrice(cart));
		session.setAttribute("TotalQuantyCart", Cartservice.TotalQuanty(cart));
		return "redirect:"+request.getHeader("Referer");
	}
	
	@RequestMapping(value = "gio-hang/editCart/{id}/{quanty}")
	public String EditCart(HttpServletRequest request,HttpSession session,@PathVariable long id,@PathVariable int quanty) {
		HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("Cart");
		if(cart == null) {
			cart = new HashMap<Long, CartDto>();
		}
		cart = Cartservice.EditCart(id,quanty,cart);
		session.setAttribute("Cart",cart);
		session.setAttribute("TotalPriceCart", Cartservice.TotalPrice(cart));
		session.setAttribute("TotalQuantyCart", Cartservice.TotalQuanty(cart));
		return "redirect:"+request.getHeader("Referer");
	}
	
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public ModelAndView CheckOut(HttpServletRequest request, HttpSession session) {
		_mvShare.setViewName("user/bills/checkout");
		Bills bills = new Bills();
		Users loginInfor = (Users)session.getAttribute("LoginInfor");
		if(loginInfor != null)
		{
			bills.setAddress(loginInfor.getAddress());
			bills.setDisplay_name(loginInfor.getDisplay_name());
			bills.setUser(loginInfor.getUser());
		}
		_mvShare.addObject("bills",bills);
		return _mvShare;
	}
	
	@RequestMapping(value = "checkout",method =RequestMethod.POST)
	public String CheckOutBill(HttpServletRequest request,HttpSession session,@ModelAttribute("bills") Bills bill){
//		bill.setQuanty(Integer.parseInt((String) session.getAttribute("TotalPriceCart")));
//		bill.setTotal(Double.parseDouble((String)session.getAttribute("TotalQuantyCart")));
		
		if(billsservice.AddBills(bill) > 0) {
			HashMap<Long, CartDto> carts = (HashMap<Long, CartDto>)session.getAttribute("Cart");
			billsservice.AddBillsDetail(carts);
		}
		session.removeAttribute("Cart");
		return"redirect:gio-hang";
	}
}
