package com.example.demo.services;

import com.example.demo.dto.*;
import com.example.demo.enums.ERole;
import com.example.demo.mapper.MainUserInfoMapper;
import com.example.demo.mapper.SignUpUserRequestMapper;
import com.example.demo.mapper.SignUpUserResponseMapper;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SignUpUserRequestMapper dtoToModelMapper;
    private final UserValidation userValidation;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;
    private final SignUpUserResponseMapper signUpUserResponseMapper;
    private final AuthenticationService authenticationService;
    private final MainUserInfoMapper mainUserInfoMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SignUpUserRequestMapper dtoToModelMapper, UserValidation userValidation, PasswordEncoder encoder, RoleRepository roleRepository, SignUpUserResponseMapper signUpUserResponseMapper, AuthenticationService authenticationService, MainUserInfoMapper mainUserInfoMapper) {
        this.userRepository = userRepository;
        this.dtoToModelMapper = dtoToModelMapper;
        this.userValidation = userValidation;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.signUpUserResponseMapper = signUpUserResponseMapper;
        this.authenticationService = authenticationService;
        this.mainUserInfoMapper = mainUserInfoMapper;
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

    public List<MainUserInfoDto> getUsersSentResume(Long jobId, String token) throws Exception {
        userValidation.validateCompanyAccess(token,jobId);
        List<User> usersWhoSentResume = userRepository.findByJobId(jobId);
        return (List<MainUserInfoDto>) mainUserInfoMapper.toDto(usersWhoSentResume);
    }

    public JwtResponseDto authenticateUser(LoginRequestDto loginRequestDto) {
        return authenticationService.authenticate(loginRequestDto);
    }
}
