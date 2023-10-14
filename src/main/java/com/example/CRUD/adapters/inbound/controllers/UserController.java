package com.example.CRUD.adapters.inbound.controllers;

import com.example.CRUD.adapters.outboud.persistence.entities.UserJpaRepository;
import com.example.CRUD.core.domain.UserDomain;
import com.example.CRUD.core.ports.UserServicePort;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    final UserServicePort userServicePort;

    public UserController(UserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDomain>> acharTodos() {
        List<UserDomain> usuarios = userServicePort.acharTodos();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDomain> detalhar(@PathVariable Long id) {
        Optional<UserDomain> user = userServicePort.detalhar(id);
        if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/users")
    @Transactional
    public ResponseEntity<UserDomain> create(@RequestBody UserDomain userDomain){

        return ResponseEntity.status(HttpStatus.OK).body(userServicePort.create(userDomain));
    }

    @PutMapping("/users/{id}")
    @Transactional
    public ResponseEntity<UserDomain> update(@PathVariable Long id,@RequestBody UserDomain userDomain){
        Optional<UserDomain> user = userServicePort.detalhar(id);
        if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(userServicePort.update(id,user.get()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/users/{id}")
    @Transactional
    public ResponseEntity<Object>  delete(@PathVariable Long id){
        Optional<UserDomain> user = userServicePort.detalhar(id);
        if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(userServicePort.delete(id));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
