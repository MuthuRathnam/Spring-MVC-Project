package kart.dao;

import java.sql.SQLException;
import java.util.List;

import kart.model.CategoryBean;

public interface CategoryDao {

	List<CategoryBean> getCategories() throws SQLException;
	
	List<CategoryBean> getSubCategory(int id) throws SQLException;
	
	List<CategoryBean> getBrands(int brandId) throws SQLException;

}
