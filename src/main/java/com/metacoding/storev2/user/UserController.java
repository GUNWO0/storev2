package com.metacoding.storev2.user;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    // 로그인 페이지 렌더링
    @GetMapping("/login-form")
    public String loginForm() {
        return "login-form";
    }

    // 로그인 처리 요청
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    // 로그 아웃 처리 요청
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    // 회원 가입
    @GetMapping("join-form")
    public String joinForm() {
        return "join-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO JoinDTO) {
        userService.회원가입(JoinDTO);
        return "redirect:/login-form";
    }
}
