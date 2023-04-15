package com.nidyran.rolebasedspringsecurity.dao.repository;


import com.nidyran.rolebasedspringsecurity.dao.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}
