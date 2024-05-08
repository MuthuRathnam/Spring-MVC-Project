package kart.dao;

import java.sql.SQLException;
import java.util.List;

import kart.model.CityBean;
import kart.model.CountryBean;
import kart.model.StateBean;
import kart.model.UserBean;

public interface UserDao {

	public boolean validUser(String email, String password) throws SQLException;

	public boolean forget(UserBean userBean) throws SQLException;

	public boolean addUser(UserBean userBean) throws SQLException;

	public UserBean userInfo(String emailId) throws SQLException;

	public boolean editUser(UserBean userBean) throws SQLException;

	public boolean newAddress(UserBean userBean) throws SQLException;

	//public List<CountryBean> getCountry()throws SQLException;

	public List<StateBean> getState(int countryId) throws SQLException;

	public List<CityBean> getCity(int stateId) throws SQLException;

}