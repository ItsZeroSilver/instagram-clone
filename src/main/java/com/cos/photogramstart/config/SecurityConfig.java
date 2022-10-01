package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//시큐리티 세팅
@EnableWebSecurity//해당 파일로 시큐리티 활성화
@Configuration //IoC에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();//비밀번호 암호화
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //csrf 토큰을 비활성화
        /** CSRF 토큰
         *  회원가입 페이 지(signin)에서 로그인페이지(signup)으로 값을 보낼 때 시큐리티 CSRF토큰을 보내줌
         *  서버에서는 CSFR토큰이 있는지 확인 후 로그인페이지로 이동할 수 있음.
         *  만약 CSFR토큰이 없다면 , 403에러가 뜸.
         *  해당 기능은 정상적인 방법으로 접근했는지, 안했는지를 구분해주기 위함.
         */
        http.csrf().disable();
        //super.configure(http); --기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
        http.authorizeRequests()
                //인증이 필요한 페이지 이무나 못들어가므로
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**").authenticated()
                //어떤 요청이 오든 제한을 먼저하고
                .anyRequest().permitAll()
                .and()
                //해당 페이지 대신 폼로그인로 가게 하겠다.
                .formLogin()
                //폼로그인 페이지 = /auth/signin
                .loginPage("/auth/signin")
                //로그인을 정상적으로 처리하면 /로 가게하겠다.
                .defaultSuccessUrl("/");
    }
}
