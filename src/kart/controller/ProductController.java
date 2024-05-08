package kart.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kart.model.ProductBean;
import kart.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/productList/{id}", method = RequestMethod.GET)
	public ModelAndView displayProductList(HttpServletResponse response, @PathVariable int id, ProductBean bean)
			throws SQLException {
		ModelAndView model = new ModelAndView();
		List<ProductBean> file = productService.productList(id);
		if (file != null) {
			model.setViewName("productList");
			model.addObject("list", file);
		} else {
			model.addObject("brandCategory");
			model.addObject("error", "Please try again");
		}
		return model;
	}

	@RequestMapping(value = "/productInfo/{productId}", method = RequestMethod.GET)
	public ModelAndView displayProductDetails(HttpServletResponse response, HttpServletRequest request,
			@PathVariable String productId, ProductBean pBean) throws SQLException {

		ModelAndView model = new ModelAndView();
		pBean = productService.productInfo(productId);
		if (pBean != null) {
			model.setViewName("productInfo");
			model.addObject("detail", pBean);
		} else {
			model.setViewName("productList");
			model.addObject("error", "Please Try Again");
		}
		return model;
	}
}