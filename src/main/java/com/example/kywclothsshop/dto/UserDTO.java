package com.example.kywclothsshop.dto;

import com.example.kywclothsshop.constant.Role;
import com.example.kywclothsshop.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {

    private Long uno;  //유저번호 pk값

    private String uname;   //유저이름

    private String password;  //패스워드

    private LocalDate birthDate;  //생년월일

    private String phoneNumber;  //핸드폰번호

    private String address;  //주소

    private String email; //이메일

    private String bankAccount; //계좌번호


    // UserDTO 내에서 dtoToEntity 메소드 수정
    public  User dtoToEntity (PasswordEncoder passwordEncoder){
        User user = new User();
        user.setUname(this.uname);
        user.setEmail(this.email);
        user.setAddress(this.address);
        user.setBankAccount(this.bankAccount);
        user.setPhoneNumber(this.phoneNumber);
        user.setBirthDate(this.birthDate);

        user.setPassword(passwordEncoder.encode(this.password)); // 주입받은 PasswordEncoder 사용
        user.setRole(Role.ADMIN); // 기본 Role 설정
        return user;
    }

}
