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

import kart.model.CategoryBean;
import kart.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView displayCategory(HttpServletResponse response, HttpServletRequest request) throws SQLException {
		ModelAndView model = new ModelAndView();
		List<CategoryBean> data = categoryService.getCategories();
		if (data != null) {
			model.setViewName("home");
			model.addObject("stock", data);
		} else {
			model.addObject("error", "Please Try Again");
		}
		return model;
	}

	@RequestMapping(value = "/subCategory/{id}", method = RequestMethod.GET)
	public ModelAndView displaySubCategory(HttpServletResponse response, @PathVariable int id) throws SQLException {
		ModelAndView model = new ModelAndView();
		List<CategoryBean> subData = categoryService.getSubCategory(id);
		if (subData != null) {
			model.setViewName("subCategory");
			model.addObject("data", subData);
		} else {
			model.addObject("home");
			model.addObject("error", "Please Try Again");
		}
		return model;
	}

	@RequestMapping(value = "/brandCategory/{brandId}", method = RequestMethod.GET)
	public ModelAndView displayingBrands(HttpServletResponse response, @PathVariable int brandId) throws SQLException {
		ModelAndView model = new ModelAndView();
		List<CategoryBean> brand = categoryService.getBrands(brandId);
		if (brand != null) {
			model.setViewName("brandCategory");
			model.addObject("logo", brand);
		} else {
			model.addObject("subCategory");
			model.addObject("error", "Please Try Again");
		}
		return model;
	}
}