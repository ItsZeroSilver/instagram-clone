package com.cos.photogramstart.web;

import com.cos.photogramstart.Service.AuthService;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor//final필드를 DI할때 사
@Controller//1. IoC 2. 파일을 리턴하는 컨트롤러
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    //@RequiredArgsConstructor써주면 생성자 생성
//    public AuthController(AuthService authService){
//        this.authService = authService;
//    }

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    //bindingResult: class
    //signupDTO에서 에러(20자 초과, Null등)가 발생하면 모두 bindingResult에 넣어줌
    //ResponseBody: 데이터를 응답함
    public @ResponseBody String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }
            return "오류남";
        } else {
            //log.info(signupDto.toString());
            //signupDto값을 받아서 User(MariaDB)에 집어 넣을거임.
            User user = signupDto.toEntity();
            User userEntity = authService.회원가입(user);
            System.out.println(userEntity);
            //log.info(user.toString());
            return "auth/signin";//회원가입이 성공시 로그인 페이지로 이동
        }
    }
}
