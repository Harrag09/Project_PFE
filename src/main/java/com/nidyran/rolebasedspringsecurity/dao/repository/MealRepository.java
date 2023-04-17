package com.nidyran.rolebasedspringsecurity.dao.repository;


import com.nidyran.rolebasedspringsecurity.dao.entity.Category;
import com.nidyran.rolebasedspringsecurity.dao.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByNameContainingOrDescContaining(String keyword, String keyword2);

    List<Meal> findByCategory(Category category);
}
