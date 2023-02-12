package com.example.CRUD.controller;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUD.dtos.UserDto;
import com.example.CRUD.models.UserModel;
import com.example.CRUD.repository.UserRepository;
import com.example.CRUD.service.UserService;



@RestController
@CrossOrigin
@Api(value="API REST")
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
	@GetMapping("/users")
	public ResponseEntity<List<UserModel>> acharTodos() {
        List<UserModel> usuarios = userService.acharTodos();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> detalhar(@PathVariable Long id) {
        Optional<UserModel> user = userService.detalhar(id);
        if(user.isPresent()){
            var userDto = new UserDto();
            BeanUtils.copyProperties(user.get(),userDto);
            return ResponseEntity.status(HttpStatus.OK).body(userDto);  
        }
        var userDto = new UserDto();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userDto);
	}

    @PostMapping("/users")
    @Transactional
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        userDto =userService.create(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(userDto); 
    }

    @PutMapping("/users/{id}")
    @Transactional
    public ResponseEntity<UserDto> update(@PathVariable Long id,@RequestBody UserDto userDto){
        Optional<UserModel> usuarioOptional = userRepository.findById(id);
        if (usuarioOptional.isPresent()) {        
            userService.update(id, userDto);
            return ResponseEntity.status(HttpStatus.OK).body(userDto);        
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userDto);
    }

	@DeleteMapping("/users/{id}")
    @Transactional
    public ResponseEntity<Object>  delete(@PathVariable Long id){
        Optional<UserModel> usuarioOptional = userRepository.findById(id);
        UserDto userDto = userService.delete(id);  
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userDto);  
        }   
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userDto);  
    }
}