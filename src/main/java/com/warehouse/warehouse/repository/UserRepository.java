package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {

//    User findByUserName(String userName);
}