package com.sanbon.backend.model.dto.request.user;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class UserUpdateRequestDto {


    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",
            message = "전화번호는 01x-xxxx-xxxx 형식이 필요합니다.")
    private String phone;
}
