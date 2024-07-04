package com.example.todo.app.api.services;

import com.example.todo.app.api.data.UserDetailData;
import com.example.todo.app.api.model.UserModel;
import com.example.todo.app.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailServicesImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailServicesImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = repository.findByLogin(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("user ["+ user +"] not found");
        }

        return new UserDetailData(user);
    }
}
