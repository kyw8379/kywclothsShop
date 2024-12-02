package com.example.kywclothsshop.config;

import jakarta.servlet.annotation.WebListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 권한 페이지 접속권한
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers("/user/login/**").permitAll() // 로그인 페이지는 누구나 접속 가능
                        .requestMatchers("/board/register").authenticated() // 로그인 한 사람만 접속 가능
                        .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN만 접속 가능
                        .requestMatchers("/user/list").hasRole("ADMIN") // ADMIN만 접속 가능
                        .anyRequest().permitAll() // 그 외 모든 요청은 허용
                )
                // CSRF 보호 비활성화 (API 등에서 필요할 수 있음)
                .csrf(csrf -> csrf.disable())
                // 로그인 처리
                .formLogin(formLogin -> formLogin
                        .loginPage("/user/login")  // 로그인 페이지 경로 확인
                        .defaultSuccessUrl("/user/main", true)
                        .usernameParameter("email")
                )

                // 로그아웃 처리
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 URL 설정
                        .invalidateHttpSession(true) // 세션 초기화
                        .logoutSuccessUrl("/") // 로그아웃 후 리다이렉트
                )
                // 예외 처리
                .exceptionHandling(a -> a
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 인증 예외 처리
                        .accessDeniedHandler(new CustomAccessDeniedHandler()) // 권한 예외 처리
                );
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
