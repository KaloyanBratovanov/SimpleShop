package ex.shop.repository;

import ex.shop.model.entity.Category;
import ex.shop.model.entity.CategoryName;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Registered
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByCategoryName(CategoryName categoryName);
}
