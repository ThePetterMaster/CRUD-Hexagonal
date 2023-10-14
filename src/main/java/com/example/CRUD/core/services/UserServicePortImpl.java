package com.example.CRUD.core.services;

import com.example.CRUD.core.domain.UserDomain;
import com.example.CRUD.core.ports.UserServicePersistencePort;
import com.example.CRUD.core.ports.UserServicePort;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServicePortImpl implements UserServicePort {

    UserServicePersistencePort userServicePersistencePort;
    public UserServicePortImpl(UserServicePersistencePort userServicePersistencePort){
        this.userServicePersistencePort=userServicePersistencePort;
    }


    @Override
    public List<UserDomain> acharTodos() {
        List<UserDomain> usuarios = userServicePersistencePort.acharTodos();
        return usuarios;
    }

    @Override
    public Optional<UserDomain> detalhar(Long id) {
        Optional<UserDomain> usuarioOptional = userServicePersistencePort.detalhar(id);
        return usuarioOptional;
    }

    @Override
    public UserDomain create(UserDomain userDomain) {
        var user = new UserDomain();
        BeanUtils.copyProperties(userDomain,user);
        userServicePersistencePort.create(user);
        return userDomain;
    }

    @Override
    public UserDomain update(Long id, UserDomain userDomain) {
        var user = new UserDomain();
        BeanUtils.copyProperties(userDomain,user);
        user.setId(id);
        userServicePersistencePort.update(id,user);
        return userDomain;
    }

    @Override
    public UserDomain delete(Long id) {
        Optional<UserDomain> usuarioOptional = userServicePersistencePort.detalhar(id);
        if (usuarioOptional.isPresent()) {
            var userDomain = new UserDomain();
            BeanUtils.copyProperties(usuarioOptional.get(),userDomain);
            userServicePersistencePort.delete(id);
            return userDomain;
        }
        var userDomain = new UserDomain();
        return userDomain;
    }
}
