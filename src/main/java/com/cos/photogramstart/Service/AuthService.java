package com.cos.photogramstart.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service //1.IoC 2. 트랜잭션 관리
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Transactional//Write(Insert, Update, Delete)
    public User 회원가입(User user){//user 외부에서 통신해서 얻은 데이터
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");//관리자는 추후 Role_Admin
        User userEntity = userRepository.save(user);//userEntity db에 담을 user 데이터
        return userEntity;
    }
}
