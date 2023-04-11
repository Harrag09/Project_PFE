package com.nidyran.rolebasedspringsecurity.dao.repository;


import com.nidyran.rolebasedspringsecurity.dao.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
