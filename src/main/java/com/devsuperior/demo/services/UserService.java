package com.devsuperior.demo.services;

import com.devsuperior.demo.entities.Role;
import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.projections.UserDetailsProjection;
import com.devsuperior.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //Buscar utilizador passando um nome do utilizador como argumento.
      List<UserDetailsProjection> result = repo.searchUserAndRolesByEmail(username);
      if(result.size() == 0){
          throw new UsernameNotFoundException("User not found " +username);
      }
       User user = new User();
      user.setEmail(username);
      user.setPassword(result.get(0).getPassword());
      for (UserDetailsProjection projection : result) {
          user.userAddRole(new Role(projection.getRoleId(), projection.getAuthority()));
      }
    return user;}



}
