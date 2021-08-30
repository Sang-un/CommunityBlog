package com.sanbon.backend.model.dto.request.user;


import com.sanbon.backend.model.enumclass.Role;
import com.sanbon.backend.model.user.User;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class SignupRequestDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",
            message = "전화번호는 01x-xxxx-xxxx 형식이 필요합니다.")
    private String phone;

    @NotBlank(message = "사진은 필수 입력 값입니다.")
    private String picture;


    public User toEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return User.builder().
                name(name)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .phone(phone)
                .role(Role.USER)
                .build();
    }
}
