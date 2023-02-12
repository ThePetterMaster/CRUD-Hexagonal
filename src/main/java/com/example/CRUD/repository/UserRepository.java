package com.example.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CRUD.models.UserModel;

@SuppressWarnings("unused")
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
