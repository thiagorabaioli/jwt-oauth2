package com.devsuperior.demo.services;

import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //Buscar utilizador passando um nome do utilizador como argumento.
      User user = repo.findByEmail(username);
       if (user == null) {
           throw new UsernameNotFoundException("User not found " +username);
       }
       return user;
    }

}
