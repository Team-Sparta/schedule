package com.example.schedule.domain.repository.query;

import com.example.schedule.domain.dto.request.UserSignUpRequestDto;
import com.example.schedule.domain.entity.User;

public interface UserRepository {

    User findByUserid(Long userid);

    User findByUsername(String username);

    User findByUserEmail(String email);


    Long register(UserSignUpRequestDto request);

}
