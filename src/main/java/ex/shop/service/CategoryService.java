package ex.shop.service;

import ex.shop.model.entity.Category;
import ex.shop.model.entity.CategoryName;
import ex.shop.model.service.CategoryServiceModel;

public interface CategoryService {

    void initCategories();

//    CategoryServiceModel findByCategoryName(CategoryName categoryName);

    Category find(CategoryName categoryName);
}
