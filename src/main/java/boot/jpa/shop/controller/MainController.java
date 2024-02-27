package boot.jpa.shop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(HttpSession session) {
        return "main";
    }

    // 회원가입 폼
    @GetMapping("/join")
    public String joinForm() { return "join"; }

    // 로그인 폼
    @GetMapping("/login")
    public String loginForm() { return "login"; }

    /* 신상품 리스트
    @GetMapping("/product/list/new")
    public String newProductList() { return "newProduct"; }

    /* 신상품 폼
    @GetMapping("/product/new")
    public String newProductForm() { return "newProductAdd"; }*/
}
