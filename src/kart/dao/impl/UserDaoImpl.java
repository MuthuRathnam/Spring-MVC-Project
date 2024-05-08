
package kart.dao.impl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

import kart.dao.UserDao;
import kart.model.CityBean;
import kart.model.CountryBean;
import kart.model.StateBean;
import kart.model.UserBean;

/**
 * @author CENTAUR
 */

public class UserDaoImpl implements UserDao {

	DataSource dataSource;

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean validUser(String email, String password) throws SQLException {
		byte[] passkey = password.getBytes();
		String encode = Base64.getEncoder().encodeToString(passkey);

		String query = "Select * from userdata where email = ? and password = ?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, email);
		pstmt.setString(2, encode);
		ResultSet resultSet = pstmt.executeQuery();
		if (resultSet.next()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean forget(UserBean userBean) throws SQLException {
		byte[] passcode = userBean.getPassword().getBytes();
		String convert = Base64.getEncoder().encodeToString(passcode);

		String query = "Update userdata set password=? where email=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, convert);
		pstmt.setString(2, userBean.getEmail());
		int resultSet = pstmt.executeUpdate();
		if (resultSet > 0) {
			return true;
		}
		return false;
	}

	public boolean addUser(UserBean userBean) throws SQLException {
		byte[] picture = null;
		try {
			picture = userBean.getImage().getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] word = userBean.getPassword().getBytes();
		String encoder = Base64.getEncoder().encodeToString(word);

		String query = "INSERT INTO userdata (`firstName`,`lastName`,`gender`,`email`,`password`,`mobile`,`image`,`home`,`area`,`landMark`,`city`,`state`,`country`,`pinCode`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, userBean.getFirstName());
		pstmt.setString(2, userBean.getLastName());
		pstmt.setNString(3, userBean.getGender());
		pstmt.setString(4, userBean.getEmail());
		pstmt.setString(5, encoder);
		pstmt.setString(6, userBean.getMobile());
		pstmt.setBytes(7, picture);
		pstmt.setString(8, userBean.getHouse());
		pstmt.setString(9, userBean.getArea());
		pstmt.setString(10, userBean.getLandMark());
		pstmt.setString(11, userBean.getCity());
		pstmt.setString(12, userBean.getState());
		pstmt.setString(13, userBean.getCountry());
		pstmt.setInt(14, userBean.getPincode());
		int resultSet = pstmt.executeUpdate();
		if (resultSet > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserBean userInfo(String emailId) throws SQLException {
		String query = "Select * from userdata where email=?";
		PreparedStatement stmt = dataSource.getConnection().prepareStatement(query);
		stmt.setString(1, emailId);
		ResultSet result = stmt.executeQuery();
		UserBean user = new UserBean();
		if (result.next())
		user.setUserId(result.getInt("userId"));
		user.setFirstName(result.getString("firstName"));
		user.setLastName(result.getString("lastname"));
		user.setGender(result.getString("gender"));
		user.setEmail(result.getString("email"));
		user.setMobile(result.getString("mobile"));
		user.setHouse(result.getString("home"));
		user.setArea(result.getString("area"));
		user.setLandMark(result.getString("landMark"));
		user.setCity(result.getString("city"));
		user.setState(result.getString("state"));
		user.setCountry(result.getString("country"));
		user.setPincode(result.getInt("pinCode"));
		byte[] image = result.getBytes("image");
		String photo = Base64.getEncoder().encodeToString(image);
		user.setPic(photo);

		return user;
	}

	@Override
	public boolean editUser(UserBean userBean) throws SQLException {
		byte[] doodle = null;
		try {
			doodle = userBean.getImage().getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String query = "Update userdata set firstName=?,lastName=?,gender=?,mobile=?,image=?,home=?,area=?,landMark=?,city=?,state=?,country=?,pinCode=? where email=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, userBean.getFirstName());
		pstmt.setString(2, userBean.getLastName());
		pstmt.setString(3, userBean.getGender());
		pstmt.setString(4, userBean.getMobile());
		pstmt.setBytes(5, doodle);
		pstmt.setString(6, userBean.getHouse());
		pstmt.setString(7, userBean.getArea());
		pstmt.setString(8, userBean.getLandMark());
		pstmt.setString(9, userBean.getCity());
		pstmt.setString(10, userBean.getState());
		pstmt.setString(11, userBean.getCountry());
		pstmt.setInt(12, userBean.getPincode());
		pstmt.setString(13, userBean.getEmail());
		int resultSet = pstmt.executeUpdate();
		if (resultSet > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean existsEmail(String email) {
		return false;
	}

	@Override
	public boolean newAddress(UserBean userBean) throws SQLException {
		String query = "Update userdata set address2=? where email=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, userBean.getAddress2());
		pstmt.setString(2, userBean.getEmail());
		int result = pstmt.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	/*
	 * @Override public List<CountryBean> getCountry() throws SQLException {
	 * List<CountryBean> regions = new ArrayList<>(); String query =
	 * "Select * from countrieslist"; PreparedStatement stmt =
	 * dataSource.getConnection().prepareStatement(query); ResultSet result =
	 * stmt.executeQuery(); while (result.next()) { CountryBean country = new
	 * CountryBean(); country.setCountryId(result.getInt("countryId"));
	 * country.setCountry(result.getString("name")); regions.add(country); } return
	 * regions; }
	 */

	@Override
	public List<StateBean> getState(int countryId) throws SQLException {
		List<StateBean> states = new ArrayList<>();
		String query = "Select * from states where countryId=?";
		PreparedStatement stmt = dataSource.getConnection().prepareStatement(query);
		stmt.setInt(1, countryId);
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			StateBean state = new StateBean();
			state.setStateId(result.getInt("stateId"));
			state.setState(result.getString("name"));
			states.add(state);
		}
		return states;
	}

	@Override
	public List<CityBean> getCity(int stateId) throws SQLException {
		List<CityBean> cities = new ArrayList<>();
		String query = "Select * from cities where stateId=?";
		PreparedStatement stmt = dataSource.getConnection().prepareStatement(query);
		stmt.setInt(1, stateId);
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			CityBean city = new CityBean();
			city.setCityId(result.getInt("cityId"));
			city.setCity(result.getString("name"));
			cities.add(city);
		}
		return cities;
	}

}