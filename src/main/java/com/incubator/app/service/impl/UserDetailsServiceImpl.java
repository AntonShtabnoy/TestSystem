package com.incubator.app.service.impl;

import com.incubator.app.dao.UserDao;
import com.incubator.app.entity.Role;
import com.incubator.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDaoImpl;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDaoImpl.findByLogin(login);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("User with name " + login + " not found");
        }
        Role role = user.getRole();
        System.out.println(role.getName());
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
        grantList.add(authority);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                String.valueOf(user.getPassword()),
                grantList
        );
        System.out.println(userDetails);
        return userDetails;
    }
}
