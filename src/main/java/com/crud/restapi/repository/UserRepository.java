package com.crud.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.restapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailAndPhone(String email, String phone);

}
