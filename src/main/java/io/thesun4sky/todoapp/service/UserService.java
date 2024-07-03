package io.thesun4sky.todoapp.service;

import io.thesun4sky.todoapp.dto.UserRequestDto;
import io.thesun4sky.todoapp.dto.UserResponseDto;
import io.thesun4sky.todoapp.entity.User;
import io.thesun4sky.todoapp.entity.UserType;
import io.thesun4sky.todoapp.exception.UserException;
import io.thesun4sky.todoapp.jwt.JwtUtil;
import io.thesun4sky.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDTO) {
        //아이디 유효성 검사
        validateUserId(requestDTO.getUserId());

        //비밀번호 암호화
        String password = passwordEncoder.encode(requestDTO.getPassword());
        User user = User.builder()
                .userId(requestDTO.getUserId())
                .password(password)
                .nickname(requestDTO.getNickname())
                .username(requestDTO.getUsername())
                .userType(UserType.NORMAL)
                .statusChangedAt(LocalDateTime.now())
                .build();

        User saveUser = userRepository.save(user);

        return new UserResponseDto(saveUser);
    }

    private void validateUserId(String id) {
        Optional<User> findUser = userRepository.findByUserId(id);
        if (findUser.isPresent()) {
            throw new UserException("중복된 id 입니다.");
        }
    }
}
