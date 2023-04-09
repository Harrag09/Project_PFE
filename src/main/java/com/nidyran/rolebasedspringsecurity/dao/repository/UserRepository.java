package com.nidyran.rolebasedspringsecurity.dao.repository;

import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
