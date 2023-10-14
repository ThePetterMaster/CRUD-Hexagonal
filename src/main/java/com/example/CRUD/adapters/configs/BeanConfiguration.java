package com.example.CRUD.adapters.configs;

import com.example.CRUD.CrudApplication;
import com.example.CRUD.core.ports.UserServicePersistencePort;
import com.example.CRUD.core.services.UserServicePortImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CrudApplication.class)
public class BeanConfiguration {

    @Bean
    UserServicePortImpl userServicePortImpl(UserServicePersistencePort persistence) {
        return new UserServicePortImpl(persistence);
    }


}

