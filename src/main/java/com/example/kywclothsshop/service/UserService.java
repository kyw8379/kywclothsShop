package com.example.kywclothsshop.service;

import com.example.kywclothsshop.dto.UserDTO;
import com.example.kywclothsshop.entity.User;
import com.example.kywclothsshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;

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
            throw new UsernameNotFoundException(email);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail()) // 유일한 username으로 email 사용
                .password(user.getPassword()) // 암호화된 비밀번호
                .roles(user.getRole().name()) // 역할 설정
                .build();
    }


    public User saveUser(UserDTO userDTO){
        // 회원가입 여부 확인
        validateDuplicateUser(userDTO.getEmail());


        // UserDTO를 User 엔티티로 변환 후 저장
        User user = userDTO.dtoToEntity(passwordEncoder);

        return userRepository.save(user);
    }

    //회원가입시 회원 가입여부 확인하는 메소드
    private  void validateDuplicateUser(String email){


        User user =
                userRepository.findByEmail(email);
        //user가  null 이라는건 db에 회원가입이
        // 되어있지 않은 email이니 회원가입이 가능하고
        // null이 아니라는건 db에 회원이 가입되어있으니
        // 회원가입을 막거나 예외처리등을 수행하자
        if(user != null){
            throw  new IllegalStateException("이미 가입된 회원입니다.");
        }
        // 이 내용은 try{}catch(IllegalStateException e) {
        //          model.att~~("msg", e.get메시지)
        // return "u/signup";}
        // 처리가능


    }


}
