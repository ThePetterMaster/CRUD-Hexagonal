package com.example.CRUD.core.ports;

import com.example.CRUD.core.domain.UserDomain;

import java.util.List;
import java.util.Optional;

public interface UserServicePersistencePort {
    public List<UserDomain> acharTodos();

    public Optional<UserDomain> detalhar(Long id);
    public UserDomain create(UserDomain userDomain);
    public UserDomain update( Long id,UserDomain userDomain);
    public UserDomain  delete(Long id);
}
