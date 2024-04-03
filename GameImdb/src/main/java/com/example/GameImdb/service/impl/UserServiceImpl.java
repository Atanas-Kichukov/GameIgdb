package com.example.GameImdb.service.impl;

import com.example.GameImdb.model.entity.UserEntity;
import com.example.GameImdb.model.entity.UserRoleEntity;
import com.example.GameImdb.model.entity.enums.UserRoleEnum;
import com.example.GameImdb.model.service.UserServiceModel;
import com.example.GameImdb.repository.UserRepository;
import com.example.GameImdb.repository.UserRoleRepository;
import com.example.GameImdb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final GameImdbUserServiceImpl gameImdbUserService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, GameImdbUserServiceImpl gameImdbUserService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.gameImdbUserService = gameImdbUserService;
    }


    @Override
    public void registerAndLoginUser(UserServiceModel userServiceModel) {
        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity()
                .setAge(userServiceModel.getAge())
                .setEmail(userServiceModel.getEmail())
                .setFirstName(userServiceModel.getFirstName())
                .setLastName(userServiceModel.getLastName())
                .setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
                .setUsername(userServiceModel.getUsername());

        Set<UserRoleEntity> roles = new HashSet<>();
        roles.add(userRole);
        newUser.setRoles(roles);

        userRepository.save(newUser);

        UserDetails principal = gameImdbUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal,newUser.getPassword(), principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean isUsernameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

}
