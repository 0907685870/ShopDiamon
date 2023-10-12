package diamonShop.Controller.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import diamonShop.Entity.Users;
import diamonShop.Service.User.AccountServiceImpl;

	@Controller
	public class UserController extends BaseController {

	@Autowired
	AccountServiceImpl account = new AccountServiceImpl();

	@RequestMapping(value = "dang-ky", method = RequestMethod.GET)
	public ModelAndView Register() {
		_mvShare.setViewName("user/account/register");
		_mvShare.addObject("user", new Users());
		return _mvShare;
	}

	@RequestMapping(value = "dang-ky", method = RequestMethod.POST)
	public ModelAndView CreataAccount(@ModelAttribute("user") Users user) {
		int count = account.AddAccount(user);
		if (count > 0) {
			_mvShare.addObject("status", "đăng ký tài khoản thành công");
		} else {
			_mvShare.addObject("status", "đăng ký tài khoản không thành công");
		}
		_mvShare.setViewName("user/account/register");
		return _mvShare;
	}

	@RequestMapping(value = "dang-nhap", method = RequestMethod.POST)
	public ModelAndView Login(HttpSession session,@ModelAttribute("user") Users user) {
		user = account.CheckUser(user);
		if(user != null) {
			_mvShare.setViewName("redirect:trang-chu");
			session.setAttribute("LoginInfor", user);
		}else {
			_mvShare.addObject("statusLogin", "Đăng nhập thất bại!");
		}
		return _mvShare;
	}
	
	@RequestMapping(value = "dang-xuat", method = RequestMethod.GET)
	public String Login(HttpSession session, HttpServletRequest request) {
		session.removeAttribute("LoginInfor");
		return "redirect:"+request.getHeader("Referer");
	}
}