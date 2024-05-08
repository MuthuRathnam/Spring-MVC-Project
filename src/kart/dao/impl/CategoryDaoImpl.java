package kart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sql.DataSource;

import kart.dao.CategoryDao;
import kart.model.CategoryBean;

public class CategoryDaoImpl implements CategoryDao {

	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<CategoryBean> getCategories() throws SQLException {
		String query = "Select * from category where parentId=0";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();
		List<CategoryBean> category = new ArrayList<>();
		while (resultSet.next()) {
			CategoryBean bean = new CategoryBean();
			bean.setId(resultSet.getInt("id"));
			bean.setName(resultSet.getString("name"));
			byte[] poto = resultSet.getBytes("image");
			String image = Base64.getEncoder().encodeToString(poto);
			bean.setPic(image);
			category.add(bean);
		}
		return category;
	}

	@Override
	public List<CategoryBean> getSubCategory(int id) throws SQLException {
		String query = "Select * from category where parentId=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();
		List<CategoryBean> subCat = new ArrayList<>();
		while (resultSet.next()) {
			CategoryBean page = new CategoryBean();
			page.setId(resultSet.getInt("id"));
			page.setName(resultSet.getString("name"));
			byte[] pic = resultSet.getBytes("image");
			String Foto = Base64.getEncoder().encodeToString(pic);
			page.setPic(Foto);
			subCat.add(page);
		}
		return subCat;
	}

	@Override
	public List<CategoryBean> getBrands(int brandId) throws SQLException {
		String query = "Select * from category where parentId=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, brandId);
		ResultSet resultSet = pstmt.executeQuery();
		List<CategoryBean> brand = new ArrayList<>();
		while (resultSet.next()) {
			CategoryBean logo = new CategoryBean();
			logo.setId(resultSet.getInt("id"));
			logo.setName(resultSet.getString("name"));
			byte[] img = resultSet.getBytes("image");
			String album = Base64.getEncoder().encodeToString(img);
			logo.setPic(album);
			brand.add(logo);
		}
		return brand;
	}
}