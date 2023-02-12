package com.example.CRUD.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.CRUD.dtos.UserDto;
import com.example.CRUD.models.UserModel;
import com.example.CRUD.repository.UserRepository;
import com.example.CRUD.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;




@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    
    public List<UserModel> acharTodos(){
        List<UserModel> usuarios = userRepository.findAll();
        return usuarios;
    }
    public Optional<UserModel>  detalhar(Long id){
        Optional<UserModel> usuarioOptional = userRepository.findById(id);
        return usuarioOptional;  

    }
    public UserDto create(UserDto userDto){
        var user = new UserModel();
        BeanUtils.copyProperties(userDto,user);
        userRepository.save(user);
        return userDto;
    }
    public UserDto update( Long id,UserDto userDto){      
        var user = new UserModel();
        BeanUtils.copyProperties(userDto,user);
        user.setId(id);
        userRepository.save(user);
        return userDto;        
    }
    public UserDto  delete(Long id){
        Optional<UserModel> usuarioOptional = userRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            var userDto = new UserDto();
            BeanUtils.copyProperties(usuarioOptional.get(),userDto);
            userRepository.deleteById(id);
            return userDto;  
        }   
        var userDto = new UserDto();
        return userDto;  
    }
}
