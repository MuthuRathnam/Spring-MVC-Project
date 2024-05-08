package kart.service.impl;

import java.sql.SQLException;
import java.util.List;

import kart.dao.UserDao;
import kart.model.CityBean;
import kart.model.StateBean;
import kart.model.UserBean;
import kart.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean validUser(String email, String password) throws SQLException {
		return userDao.validUser(email, password);
	}

	@Override
	public boolean forget(UserBean userBean) throws SQLException {
		return userDao.forget(userBean);
	}

	@Override
	public boolean addUser(UserBean userBean) throws SQLException {
		return userDao.addUser(userBean);
	}

	@Override
	public boolean editUser(UserBean userBean) throws SQLException {
		return userDao.editUser(userBean);
	}

	@Override
	public UserBean userInfo(String emailId) throws SQLException {
		return userDao.userInfo(emailId);
	}

	@Override
	public boolean newAddress(UserBean userBean) throws SQLException {
		return userDao.newAddress(userBean);
	}

	@Override
	public List<StateBean> getState(int countryId) throws SQLException {
		return userDao.getState(countryId);
	}

	@Override
	public List<CityBean> getCity(int stateId) throws SQLException {
		return userDao.getCity(stateId);
	}

	
}