package org.chtijbug.drools.console.security;

import org.chtijbug.drools.proxy.persistence.model.User;
import org.chtijbug.drools.proxy.persistence.model.UserRoles;
import org.chtijbug.drools.proxy.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MongoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByLogin(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (UserRoles userRole: user.getUserRoles()){
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
    }
}
