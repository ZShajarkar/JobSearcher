package com.example.demo.services;

import com.example.demo.dto.JwtResponseDto;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public JwtResponseDto authenticate(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new JwtResponseDto(
                jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles,
                userDetails.getName());
    }

    public Long getIdOutOfBearerToken(String accessToken) {
        String username = jwtUtils.getUserNameFromJwtToken(accessToken.substring(7));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return ((UserDetailsImpl) userDetails).getId();
    }

}
