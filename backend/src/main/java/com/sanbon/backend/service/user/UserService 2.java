package com.sanbon.backend.service.user;

import com.sanbon.backend.component.advice.ErrorCode;
import com.sanbon.backend.component.advice.exception.EmailAlreadyExistException;
import com.sanbon.backend.component.advice.exception.UserNotFoundException;
import com.sanbon.backend.config.auth.PrincipalDetails;
import com.sanbon.backend.model.dto.request.user.SignupRequestDto;
import com.sanbon.backend.model.dto.request.user.UserUpdateRequestDto;
import com.sanbon.backend.model.user.User;
import com.sanbon.backend.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public ResponseEntity<User> signup(SignupRequestDto signupRequestDto) {
        if (checkEmailDuplicate(signupRequestDto.getEmail()))
            throw new EmailAlreadyExistException(ErrorCode.INVALID_PARAMETER);
        return new ResponseEntity<>(userRepository.save(signupRequestDto.toEntity(bCryptPasswordEncoder)), HttpStatus.OK);
    }

    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public ResponseEntity<User> update(Authentication authentication, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UserNotFoundException(ErrorCode.INVALID_PARAMETER));
        user.update(userUpdateRequestDto.getName(), userUpdateRequestDto.getPhone());
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);

    }

    public ResponseEntity<User> userInfo(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println(principalDetails.getUser());
        return new ResponseEntity<>(principalDetails.getUser(), HttpStatus.OK);
    }


}
