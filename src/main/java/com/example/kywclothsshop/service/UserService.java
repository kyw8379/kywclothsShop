package com.example.kywclothsshop.service;

import com.example.kywclothsshop.constant.Role;
import com.example.kywclothsshop.dto.UserDTO;
import com.example.kywclothsshop.entity.User;
import com.example.kywclothsshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            log.error("User not found with email: {}", email);
            throw new UsernameNotFoundException("해당 이메일로 가입된 사용자를 찾을 수 없습니다.");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

    // 회원가입 처리
    public User saveUser(UserDTO userDTO) {
        validateDuplicateUser(userDTO.getEmail());

        User user = userDTO.dtoToEntity(passwordEncoder);
        user.setRole(Role.USER); // 기본 권한 USER 설정
        userRepository.save(user);

        log.info("회원가입 성공: {}", user.getEmail());

        return user;
    }

    // 회원가입 시 중복된 이메일 확인
    private void validateDuplicateUser(String email) {
        if (userRepository.findByEmail(email) != null) {
            log.warn("이미 존재하는 이메일: {}", email);
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
    }

    // 로그인 처리
    public boolean login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (user == null) {
            log.warn("로그인 실패 - 이메일 없음: {}", userDTO.getEmail());
            return false;
        }

        boolean isPasswordMatch = passwordEncoder.matches(userDTO.getPassword(), user.getPassword());
        if (!isPasswordMatch) {
            log.warn("로그인 실패 - 비밀번호 불일치: {}", userDTO.getEmail());
        }

        return isPasswordMatch;
    }
}
