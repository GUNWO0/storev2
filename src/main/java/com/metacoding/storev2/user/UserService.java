package com.metacoding.storev2.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());

        if (user == null) {
            throw new RuntimeException("해당 username이 없습니다");
        }

        if (!(user.getPassword().equals(loginDTO.getPassword()))) {
            throw new RuntimeException("해당 password가 틀렸습니다");
        }

        return user;
    }

    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        // 1. 동일 유저 네임이 있는지 검사
        User user = userRepository.findByUsername(joinDTO.getUsername());

        // 2. 있으면 터트리기
        if (user != null) throw new RuntimeException("동일한 username이 존재합니다");

        // 3. 없으면 회원가입 하기
        userRepository.save(joinDTO.getUsername(), joinDTO.getPassword(), joinDTO.getFullname());
    }
}
