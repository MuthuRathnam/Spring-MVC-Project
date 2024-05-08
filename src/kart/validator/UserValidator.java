package kart.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kart.model.UserBean;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {

		return UserBean.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object result, Errors error) {

		String pattern="^(?=.*[A-Z])(?=.*[a-z].*[a-z].*[a-z])(?=.*[0-9].*[0-9])(?=.*[!@#$&*]).{8}$";
		boolean finals= true;         

	   		ValidationUtils.rejectIfEmptyOrWhitespace(error, "firstName", "error.firstName", "FirstName is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "lastName", "error.lastName", "Atleast Enter Initials");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "error.password", "Enter valid Password");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "confirmPassword", "error.confirmPassword","Must match the Password");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "email", "error.email", "Enter the valid email id");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "mobile", "error.mobile", "Mobile must have 10 numbers");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "email", "error.email", "Already Registered");
	}
}