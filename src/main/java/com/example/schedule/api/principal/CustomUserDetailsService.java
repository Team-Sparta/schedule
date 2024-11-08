package com.example.schedule.api.principal;

import com.example.schedule.common.exception.InvalidParamException;
import com.example.schedule.common.exception.code.ErrorCode;
import com.example.schedule.domain.entity.User;
import com.example.schedule.domain.repository.query.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

//    private final MemberReader memberReader;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByUserEmail(email);

        if (user == null) {
            throw new InvalidParamException(ErrorCode.NOT_FOUND_USER);
        }

        return new UserPrincipal(user);

    }
}