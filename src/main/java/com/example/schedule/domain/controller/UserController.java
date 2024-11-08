package com.example.schedule.domain.controller;

import com.example.schedule.common.exception.code.SuccessCode;
import com.example.schedule.common.response.CommonResponse;
import com.example.schedule.domain.dto.request.UserSignUpRequestDto;
import com.example.schedule.domain.dto.response.TokenResponseDto;
import com.example.schedule.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<CommonResponse<TokenResponseDto>> singUp(
            @Valid @RequestBody UserSignUpRequestDto request
    ) {
        return CommonResponse.success(SuccessCode.SUCCESS_INSERT, userService.register(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<CommonResponse<TokenResponseDto>> signIn(
            @Valid @RequestBody UserSignUpRequestDto request
    ) {
        return CommonResponse.success(SuccessCode.SUCCESS_INSERT, userService.singIn(request));
    }
}


//
// MethodArgumentNotValidException : Validation failed for argument [0]
// in public org.springframework.http.ResponseEntity<com.example.schedule.common.response.CommonResponse<com.example.schedule.dto.response.UserResponseDto>> com.example.schedule.controller.UserController.register(com.example.schedule.dto.request.UserRequestDto) with 6 errors: [Field error in object 'userRequestDto' on field 'password': rejected value [null]; codes [NotEmpty.userRequestDto.password,NotEmpty.password,NotEmpty.java.lang.String,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [userRequestDto.password,password]; arguments []; default message [password]]; default message [password must not be empty]] [Field error in object 'userRequestDto' on field 'username': rejected value [null]; codes [NotEmpty.userRequestDto.username,NotEmpty.username,NotEmpty.java.lang.String,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [userRequestDto.username,username]; arguments []; default message [username]]; default message [username must not be empty]] [Field error in object 'userRequestDto' on field 'email': rejected value [null]; codes [NotEmpty.userRequestDto.email,NotEmpty.email,NotEmpty.java.lang.String,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [userRequestDto.email,email]; arguments []; default message [email]]; default message [email must not be empty]] [Field error in object 'userRequestDto' on field 'username': rejected value [null]; codes [NotNull.userRequestDto.username,NotNull.username,NotNull.java.lang.String,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [userRequestDto.username,username]; arguments []; default message [username]]; default message [username must not be null]] [Field error in object 'userRequestDto' on field 'email': rejected value [null]; codes [NotNull.userRequestDto.email,NotNull.email,NotNull.java.lang.String,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [userRequestDto.email,email]; arguments []; default message [email]]; default message [email must not be null]] [Field error in object 'userRequestDto' on field 'password': rejected value [null]; codes [NotNull.userRequestDto.password,NotNull.password,NotNull.java.lang.String,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [userRequestDto.password,password]; arguments []; default message [password]]; default message [password must not be null]]
//  "result": "PreparedStatementCallback; Column 'created_at' cannot be null"