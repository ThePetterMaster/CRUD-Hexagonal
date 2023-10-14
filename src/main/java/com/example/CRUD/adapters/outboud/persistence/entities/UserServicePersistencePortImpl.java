package com.example.CRUD.adapters.outboud.persistence.entities;

import com.example.CRUD.core.domain.UserDomain;
import com.example.CRUD.core.ports.UserServicePersistencePort;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class UserServicePersistencePortImpl implements UserServicePersistencePort {
    @Autowired
    UserJpaRepository userJpaRepository;
    @Override
    public List<UserDomain> acharTodos() {
        List<UserEntity> listUserEntity= userJpaRepository.findAll();
        List<UserDomain> listUserDomain=new ArrayList<>();
        for(int i=0;i<listUserEntity.size();i++){
            UserDomain userDomain=new UserDomain();
            BeanUtils.copyProperties(listUserEntity.get(i),userDomain);
            listUserDomain.add(userDomain);
        }
        return listUserDomain;
    }

    @Override
    public Optional<UserDomain> detalhar(Long id) {
        Optional<UserEntity> optionalUserEntity= userJpaRepository.findById(id);
        UserDomain userDomain=new UserDomain();
        if(optionalUserEntity.isPresent()){
            BeanUtils.copyProperties(userDomain,optionalUserEntity.get());
            return Optional.of(userDomain);
        }
        return Optional.empty();
    }

    @Override
    public UserDomain create(UserDomain userDomain) {
        UserEntity userEntity =new UserEntity();
        BeanUtils.copyProperties(userDomain,userEntity);
        userJpaRepository.save(userEntity);
        return userDomain;
    }

    @Override
    public UserDomain update(Long id, UserDomain userDomain) {
        UserEntity userEntity =new UserEntity();
        BeanUtils.copyProperties(userDomain,userEntity);
        userJpaRepository.save(userEntity);
        return userDomain;
    }

    @Override
    public UserDomain delete(Long id) {
        Optional<UserEntity> optionalUserEntity= userJpaRepository.findById(id);

        if(optionalUserEntity.isPresent()){
            userJpaRepository.delete(optionalUserEntity.get());
            UserDomain userDomain =new UserDomain();
            BeanUtils.copyProperties(optionalUserEntity.get(),userDomain);
            return userDomain;
        }
        return null;
    }
}
