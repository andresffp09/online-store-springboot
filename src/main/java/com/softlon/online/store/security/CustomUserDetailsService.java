package com.softlon.online.store.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.softlon.online.store.entities.Client;
import com.softlon.online.store.repositories.IClientRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    private IClientRepository clientRepository;

    @Autowired
    public CustomUserDetailsService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client user = clientRepository.findByEmail(username);
        if (user != null){
            System.out.println("User Found with ID: " + user.getId());
            User userAuth = new User(user.getEmail(), user.getPassword(), new ArrayList<>());
            return userAuth;
        } else {
            System.out.println("Invalid username or password");
            throw new UsernameNotFoundException("Invalid username or password");
        }  
    }
    
    
}
