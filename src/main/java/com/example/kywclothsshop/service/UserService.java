package com.example.kywclothsshop.service;

import com.example.kywclothsshop.dto.UserDTO;
import com.example.kywclothsshop.entity.User;
import com.example.kywclothsshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
 private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUname())    //세션에 저장됨
                .password(user.getPassword())// 로그인시 입력한 비밀번호와 비교할값
                .roles(user.getRole().name())
                .build();
    }

    public User saveUser(UserDTO userDTO){
        // 회원가입여부 확인
       validateDuplicateUser(userDTO.getEmail());


        //컨트롤러에서 받은  UserDTO를 user entity로 변경

       User user =
                userDTO.dtoToEntity(userDTO);

        user = userRepository.save(user);

        return user;
    }

    //회원가입시 회원 가입여부 확인하는 메소드
    private  void validateDuplicateUser(String email){


        User user =
                userRepository.findByEmail(email);
        //member 이  null 이라는건 db에 회원가입이
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
