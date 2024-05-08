package kart.service.impl;

import java.sql.SQLException;
import java.util.List;

import kart.dao.CategoryDao;
import kart.model.CategoryBean;
import kart.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<CategoryBean> getCategories() throws SQLException {
		return categoryDao.getCategories();
	}

	@Override
	public List<CategoryBean> getSubCategory(int id) throws SQLException {
		return categoryDao.getSubCategory(id);
	}

	@Override
	public List<CategoryBean> getBrands(int brandId) throws SQLException {
		return categoryDao.getBrands(brandId);
	}

	
}