package kart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sql.DataSource;

import kart.dao.ProductDao;
import kart.model.ProductBean;

public class ProductDaoImpl implements ProductDao {

	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<ProductBean> productList(int id) throws SQLException {
		String query = "Select * from productdata where id=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();
		List<ProductBean> data = new ArrayList<>();
		while (resultSet.next()) {
			ProductBean pojo = new ProductBean();
			pojo.setProductId(resultSet.getString("productId"));
			pojo.setName(resultSet.getString("name"));
			pojo.setImages(resultSet.getBlob("image"));
			pojo.setColor(resultSet.getString("color"));
			pojo.setPrice(resultSet.getDouble("price"));
			pojo.setSize(resultSet.getString("size"));
			int blob = (int) pojo.getImages().length();
			byte[] img = pojo.getImages().getBytes(1, blob);
			String figure = Base64.getEncoder().encodeToString(img);
			pojo.setPic(figure);
			data.add(pojo);
		}
		return data;
	}

	@Override
	public ProductBean productInfo(String productId) throws SQLException {
		String query = "Select * from productdata where productId=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, productId);
		ResultSet resultSet = pstmt.executeQuery();
		ProductBean pBean = new ProductBean();
		if (resultSet.next()) {
			pBean.setProductId(resultSet.getString("productId"));
			pBean.setName(resultSet.getString("name"));
			pBean.setPrice(resultSet.getDouble("price"));
			pBean.setColor(resultSet.getString("color"));
			pBean.setType(resultSet.getString("model"));
			pBean.setSize(resultSet.getString("size"));
			pBean.setFeatures(resultSet.getString("features"));
			pBean.setDetails(resultSet.getString("details"));
			pBean.setOffers(resultSet.getString("offers"));
			pBean.setSeller(resultSet.getString("seller"));
			byte[] img = resultSet.getBytes("image");
			String figure = Base64.getEncoder().encodeToString(img);
			pBean.setPic(figure);
		}
		return pBean;
	}

	
}