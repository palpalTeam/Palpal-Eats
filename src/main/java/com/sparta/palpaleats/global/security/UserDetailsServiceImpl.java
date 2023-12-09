package com.sparta.palpaleats.global.security;

import com.sparta.palpaleats.domain.user.entity.User;
import com.sparta.palpaleats.domain.user.repository.UserRepository;
import com.sparta.palpaleats.global.exception.CustomException;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("존재하지 않는 사용자입니다")
        );

        return new UserDetailsImpl(user);
    }
}
