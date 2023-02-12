package com.example.CRUD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.CRUD.dtos.UserDto;
import com.example.CRUD.models.UserModel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public interface UserService {

    
    public List<UserModel> acharTodos();
    
    public Optional<UserModel>  detalhar(Long id);
    public UserDto create(UserDto userDto);
    public UserDto update( Long id,UserDto userDto);
    public UserDto  delete(Long id);
}
