package io.thesun4sky.todoapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "이름을 입력해 주세요.")
    private String nickname;

    @NotBlank(message = "아이디를 입력해 주세요")
    @Size(min = 4, max = 10, message = "ID는 10글자 이상, 20글자 이하여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영문 대소문자와 숫자만 입력 가능합니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    @Size(min = 8, max = 15, message = "비밀번호는 최소 8글자 이상이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영어 대소문자와 숫자 최소 1글자씩 포함해야 합니다.")
    private String password;
}
