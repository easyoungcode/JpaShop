package boot.jpa.shop.user;

import boot.jpa.shop.user.dto.LoginRequestDto;
import boot.jpa.shop.user.dto.SignUpRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public void userJoin(@RequestBody SignUpRequestDto signUpRequestDto) {
        userService.signUp(signUpRequestDto);
    }

    @PostMapping("/login")
    public int userLogin(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) {
        HttpStatusCode httpStatusCode = userService.login(loginRequestDto);
        if (httpStatusCode.is2xxSuccessful()) {
            session.setAttribute("loginCheck", true);
            session.setAttribute("id", loginRequestDto.getId());
            System.out.println("Session ID: " + loginRequestDto.getId());
            return 1;
        } else {
            System.out.println("로그인 실패");
            return 0;
        }
    }

    @GetMapping("/logout")
    public ModelAndView userLogout(HttpSession session) {
        session.removeAttribute("loginCheck");
        session.removeAttribute("id");
        System.out.println("로그아웃 완료");
        ModelAndView mv= new ModelAndView("main");
        return mv;
    }
}
