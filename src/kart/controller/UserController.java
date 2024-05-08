package kart.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kart.dao.impl.UserDaoImpl;
import kart.model.StateBean;
import kart.model.UserBean;
import kart.service.UserService;
import kart.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValid;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, UserBean userBean) {
		ModelAndView model = null;
		model = new ModelAndView("login");
		model.addObject("userBean", userBean);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String executeLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("userBean") UserBean userBean, BindingResult result, Model model) throws SQLException {
		userValid.validate(userBean, result);
		if (result.hasErrors()) {
			boolean isValid = userService.validUser(userBean.getEmail(), userBean.getPassword());
			if (isValid) {
				model.addAttribute("message", "LoggedIn Successfully");
				request.setAttribute("emailId", userBean.getEmail());
				return "welcome";
			} else {
				request.setAttribute("error", "Enter Valid UserName or Password");
				return "login";
			}
		} else {
			request.setAttribute("error", "Enter Valid UserName or Password");
			return "login";
		}
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView displayProfile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String emailId, UserBean userBean) throws SQLException {

		ModelAndView model = null;
		UserBean userDetail = userService.userInfo(emailId);
		if (userDetail != null) {
			model = new ModelAndView("profile");
			model.addObject("userBean", userDetail);
			request.setAttribute(emailId, userDetail.getEmail());
		}
		return model;
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ModelAndView displayForgot(HttpServletRequest request, HttpServletResponse response, UserBean userBean) {
		ModelAndView model = new ModelAndView("forgot");
		model.addObject("userBean", userBean);
		return model;
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String executeForgot(HttpServletRequest request, HttpServletResponse response, @Valid UserBean userBean,
			BindingResult result, Model model) throws SQLException {

		boolean forgotIt = userService.forget(userBean);
		if (userBean.getConfirmPassword().equals(userBean.getPassword())) {
			if (forgotIt) {
				model.addAttribute("message", "Password Changed Successfully");
				return "login";
			} else {
				model.addAttribute("error", "Invalid Password");
				return "forgot";
			}
		} else {
			model.addAttribute("error", " Password Miss Match");
			return "forgot";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView displayRegister(HttpServletRequest request, HttpServletResponse response, UserBean userBean
			) throws SQLException {
		ModelAndView model = null;
		model = new ModelAndView("register");
		model.addObject("userBean", userBean);
		return model;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String executeRegister(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("userBean") @Valid UserBean userBean, BindingResult result) throws SQLException {

		userValid.validate(userValid, result);
		if (result.hasErrors()) {
			model.addAttribute("error", "Please Enter Valid details");
			return "register";
		}
		if (UserDaoImpl.existsEmail(userBean.getEmail())) {
			model.addAttribute("error", "Email is Already Registered");
			return "register";
		}
		if (!userBean.getPassword().equals(userBean.getConfirmPassword())) {
			model.addAttribute("error", "Password MissMatch");
			return "register";
		} else {
			model.addAttribute(userService.addUser(userBean));
			model.addAttribute("message", "Details Registered Successfully");
			return "login";
		}
	}

	@RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
	public ModelAndView displayEdit(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String emailId, @ModelAttribute("userBean") UserBean userBean) throws SQLException {

		ModelAndView model = null;
		UserBean userDetail = userService.userInfo(emailId);

		if (userDetail != null) {
			model = new ModelAndView("edit");
			model.addObject("userBean", userDetail);
			request.setAttribute(emailId, userDetail.getEmail());
		}
		return model;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView displayDetails(HttpServletRequest request, HttpServletResponse response, UserBean userBean,
			@RequestParam String emailId) throws SQLException {
		ModelAndView model = null;
		UserBean details = userService.userInfo(emailId);
		if (details != null) {
			model = new ModelAndView("update");
			model.addObject("userBean", details);
		}
		return model;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView executeUpdate(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("userBean") UserBean userBean) throws SQLException {
		ModelAndView model = null;
		boolean updateDetails = userService.editUser(userBean);

		if (updateDetails) {
			model=new ModelAndView("update");
			model.addObject("message", "User Details Updated Successfully");
			request.setAttribute("emailId", userBean.getEmail());
		} else {
			model=new ModelAndView("edit");
			model.addObject("error", "Details Not Updated Successfully");
		}
		return model;
	}

	@RequestMapping(value = "/newAddress", method = RequestMethod.GET)
	public ModelAndView displayOrder(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String emailId, UserBean userBean) throws SQLException {
		ModelAndView model = null;
		UserBean userDetail = userService.userInfo(emailId);
		if (userDetail != null) {
			model = new ModelAndView("newAddress");
			model.addObject("userData", userDetail);
		}
		return model;
	}

	@RequestMapping(value = "/newAddress", method = RequestMethod.POST)
	public String executeOrder(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("userBean") UserBean userBean) throws SQLException {
		ModelAndView model = new ModelAndView();
		boolean usersId = userService.newAddress(userBean);
		if (usersId) {
			model.addObject("msg", "Work Address added Successfully");
			return "redirect:/deliveryAddress";
		} else {
			model.addObject("err", "Invalid Address");
			return "newAddress";
		}
	}

	@RequestMapping(value = "/states", method = RequestMethod.GET)
	public ModelAndView displayCountries(HttpServletRequest request, HttpServletResponse response, StateBean stateBean)
			throws SQLException {
		ModelAndView model = null;
		List<StateBean> states = userService.getState(44);
		if (states != null) {
			model = new ModelAndView("register");
			model.addObject("datas", states);
		}
		return model;
	}

}