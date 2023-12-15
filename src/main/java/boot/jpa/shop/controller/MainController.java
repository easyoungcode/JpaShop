package boot.jpa.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main() {
        return "main";
    }

    // 회원가입 폼
    @GetMapping("/join")
    public String joinForm() { return "join"; }

    // 로그인 폼
    @GetMapping("/login")
    public String loginForm() { return "login"; }
}
