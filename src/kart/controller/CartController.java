package kart.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kart.model.AddToCartForm;
import kart.model.AfterCartForm;
import kart.model.CartBean;
import kart.model.CartEntryBean;
import kart.service.CartService;

@Controller
public class CartController {
	
	//HttpSession session;
   // private String email=(String) session.getAttribute("userId");
	
	@Autowired
	private CartService cartService;

	private String email="chakra@gmail.com"	;

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public String addToCart(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bean") AddToCartForm form, Model model) throws SQLException {
		boolean result = cartService.addToCart(form);

		if (result) {
			return "redirect:/showCart";
		} else {
			return "redirect:/productInfo";
		}

	}

	@RequestMapping(value = "/showCart", method = RequestMethod.GET)
	public String redirectShowCart(HttpServletRequest request, HttpServletResponse response, Model model,String userId)
			throws SQLException {
		
			CartBean cartData = cartService.showCartData(email);
			if (cartData != null) {
				model.addAttribute("data", cartData);
				request.setAttribute("message", "Added to Cart Successfully");
				request.getSession().getAttribute(userId);
				return "cart";
			} else {
				request.setAttribute("error", "Please Try Again");
				return "redirect:/productInfo";
			}

	}

	@RequestMapping(value = "/updateCartEntry", method = RequestMethod.POST)
	public String executeUpdateCartEntry(HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("quantity") CartEntryBean entry) throws SQLException {
		boolean result = cartService.cartEntryUpdateQty(entry);
		if (result) {
			return "redirect:/showCart";
		} else {
			return "redirect:/cart";
		}
	}

	@RequestMapping(value = "/deleteCartEntry", method = RequestMethod.POST)
	public String executeDeleteCartEntry(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("delete") AfterCartForm bean) throws SQLException {
		boolean deleted = cartService.deleteCartEntry(bean);
		if (deleted) {
			return "redirect:/showCart";
		} else {
			return "redirect:/cart";
		}
	}

}