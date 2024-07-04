package io.thesun4sky.todoapp.controller;

import io.thesun4sky.todoapp.dto.CommonResponseDto;
import io.thesun4sky.todoapp.dto.UserRequestDto;
import io.thesun4sky.todoapp.dto.UserResponseDto;
import io.thesun4sky.todoapp.jwt.JwtUtil;
import io.thesun4sky.todoapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto<UserResponseDto>> createUser(@Valid @RequestBody UserRequestDto requestDTO) {
        UserResponseDto responseDTO = userService.createUser(requestDTO);

        CommonResponseDto<UserResponseDto> responseMessage = CommonResponseDto.<UserResponseDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .msg("회원가입이 완료되었습니다.")
                .data(responseDTO)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}
