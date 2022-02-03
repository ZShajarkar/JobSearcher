package com.example.demo.services;

import com.example.demo.dto.JwtResponseDto;
import com.example.demo.configuration.JwtUtils;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.SignUpUserRequestDto;
import com.example.demo.dto.SignUpUserResponseDto;
import com.example.demo.enums.ERole;
import com.example.demo.mapper.SignUpUserRequestMapper;
import com.example.demo.mapper.SignUpUserResponseMapper;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SignUpUserRequestMapper dtoToModelMapper;
    private final UserValidation userValidation;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;
    private final SignUpUserResponseMapper signUpUserResponseMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SignUpUserRequestMapper dtoToModelMapper, UserValidation userValidation, PasswordEncoder encoder, RoleRepository roleRepository, SignUpUserResponseMapper signUpUserResponseMapper, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.dtoToModelMapper = dtoToModelMapper;
        this.userValidation = userValidation;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.signUpUserResponseMapper = signUpUserResponseMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public SignUpUserResponseDto save(SignUpUserRequestDto userDto) throws Exception {
        userValidation.validateUser(userDto);
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user = dtoToModelMapper.toModel(userDto);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return signUpUserResponseMapper.toDto(savedUser);
    }

    public JwtResponseDto authenticateUser(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponseDto(
                jwt,
                userDetails.getId(),
                userDetails.getEmail(),
              roles);
    }
}
