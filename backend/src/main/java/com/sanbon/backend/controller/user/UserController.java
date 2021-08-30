package com.sanbon.backend.controller.user;

import com.sanbon.backend.model.dto.request.user.SignupRequestDto;
import com.sanbon.backend.model.dto.request.user.UserUpdateRequestDto;
import com.sanbon.backend.model.user.User;
import com.sanbon.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/guest/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @GetMapping("/user/userinfo")
    public ResponseEntity<User> userInfo(Authentication authentication) {
        return userService.userInfo(authentication);
    }

    @PutMapping("/user/userinfo")
    public ResponseEntity<User> update(Authentication authentication,@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.update(authentication,userUpdateRequestDto);
    }

}
