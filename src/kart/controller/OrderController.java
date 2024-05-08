package kart.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kart.model.CartBean;
import kart.model.OrderBean;
import kart.model.UserBean;
import kart.service.CartService;
import kart.service.OrderService;
import kart.service.UserService;

@Controller
public class OrderController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartService cartService;

	private String email = "chakra@gmail.com";

	@RequestMapping(value = "/deliveryAddress", method = RequestMethod.GET)
	public String displayOrder(HttpServletRequest request, HttpServletResponse response, Model model)
			throws SQLException {
		UserBean user = userService.userInfo(email);
		if (user != null) {
			model.addAttribute("userData", user);
			return "deliveryAddress";
		} else {
			return "redirect:/showCart";
		}
	}

	@RequestMapping(value = "/deliveryAddress", method = RequestMethod.POST)
	public String executeOrder(HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("orderBean") OrderBean orderBean, Model model) throws SQLException {
		boolean trueAddress = orderService.setDeliveryAddress(orderBean);
		if (trueAddress) {
			model.addAttribute("msg", "Address set Successfully");
			return "redirect:/payment";
		} else {
			model.addAttribute("error", "Please Try again");
			return "deliveryAddress";
		}
	}

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public ModelAndView displayPay(HttpServletRequest request, HttpServletResponse response, CartBean cartData)
			throws SQLException {
		ModelAndView model = null;
		cartData = cartService.getCartData(email);
		if (cartData != null) {
			model=new ModelAndView("payment");
			model.addObject("datas", cartData);
		}
		return model;
	}

	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String executePay(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("orderBean") OrderBean orderBean, Model model) throws SQLException {
		orderBean.setDate(Timestamp.valueOf(LocalDateTime.now()));
		boolean paymentresult = orderService.setPaymentMethod(orderBean);
		if (paymentresult) {
			return "redirect:/summary";
		} else {
			model.addAttribute("error", "Select anyone Payment data");
			return "payment";
		}
	}

	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	public String createOrderSummary(HttpServletRequest request, HttpServletResponse response, Model model)
			throws SQLException {
		OrderBean order = orderService.getOrderDetails(email);
		if (order != null && order.getAddress() != null) {
			model.addAttribute("detail", order);
			return "summary";
		} else {
			model.addAttribute("error", "Please try Again");
			return "payment";
		}
	}

}