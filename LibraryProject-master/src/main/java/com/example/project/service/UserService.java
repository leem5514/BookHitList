package com.example.project.service;

import com.example.project.dto.MemberDTO;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.UserEntity;
import com.example.project.dto.AddUserRequest;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(AddUserRequest req) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserEntity newUser = UserEntity.builder()
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .name(req.getName())
                .build();
        return userRepository.save(newUser).getId();
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unexpected User"));
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Unexpected User"));
    }

    public UserEntity login(AddUserRequest req) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단

         */
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<UserEntity> byUserEmail = userRepository.findByEmail(req.getEmail());
        if (byUserEmail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            UserEntity userEntity = byUserEmail.get();
            if (encoder.matches(req.getPassword(), userEntity.getPassword())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                return userEntity;
            } else {
                // 비밀번호 불일치(로그인실패)
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
            return null;
        }
    }
}
