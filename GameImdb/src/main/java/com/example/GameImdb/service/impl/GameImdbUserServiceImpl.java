package com.example.GameImdb.service.impl;

import com.example.GameImdb.model.entity.UserEntity;
import com.example.GameImdb.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameImdbUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public GameImdbUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity =
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username with name" + username + " not found" ));
        return mapToUserDetails(userEntity);
    }
    private static UserDetails mapToUserDetails(UserEntity userEntity){
        List<GrantedAuthority> authorities =
                userEntity
                        .getRoles()
                        .stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).collect(Collectors.toList());



        return new User(userEntity.getUsername(), userEntity.getPassword(),authorities);
    }
}
