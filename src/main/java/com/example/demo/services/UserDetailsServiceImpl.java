package com.example.demo.services;

import com.example.demo.model.Company;
import com.example.demo.model.User;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails;
        if (username.endsWith("@company"))
            userDetails = loadUserByUserNameOfCompany(username);
        else userDetails = loadUserByEmailOfUser(username);
        return userDetails;
    }

    public UserDetails loadUserByEmailOfUser(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException("User Not Found with email: " + username);
        return UserDetailsImpl.build(user);
    }

    public UserDetails loadUserByUserNameOfCompany(String username) throws UsernameNotFoundException {
        Company user = companyRepository.findByUserName(username);
        if (user == null)
            throw new UsernameNotFoundException("Company Not Found with username: " + username);
        return UserDetailsImpl.build(user);
    }
}