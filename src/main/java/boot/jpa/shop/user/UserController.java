package boot.jpa.shop.user;

import boot.jpa.shop.user.dto.LoginRequestDto;
import boot.jpa.shop.user.dto.SignUpRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public void userJoin(@RequestBody SignUpRequestDto signUpRequestDto) {
        userService.signUp(signUpRequestDto);
    }

    @PostMapping("/login")
    public void userLogin(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) {
        HttpStatusCode httpStatusCode = userService.login(loginRequestDto);
        if (httpStatusCode.is2xxSuccessful()) {
            session.setAttribute("loginCheck", true);
            session.setAttribute("id", loginRequestDto.getId());
            System.out.println("Session ID: " + loginRequestDto.getId());
        } else {
            System.out.println("로그인 실패");
        }
    }

}
