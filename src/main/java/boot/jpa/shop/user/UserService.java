package boot.jpa.shop.user;

import boot.jpa.shop.domain.User;
import boot.jpa.shop.user.dto.LoginRequestDto;
import boot.jpa.shop.user.dto.SignUpRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    void signUp(SignUpRequestDto signUpRequestDto) {
        // 비밀번호 암호화 알고리즘 적용 (SHA-256)
        String encryptPw = getEncrypt(signUpRequestDto.getPassword());

        User user = User.builder()
                .name(signUpRequestDto.getName())
                .id(signUpRequestDto.getId())
                .password(encryptPw)
                .phone(signUpRequestDto.getPhone())
                .address(signUpRequestDto.getAddress())
                .userCode(UUID.randomUUID())
                .build();

        userRepository.save(user);
    }

    // SHA-256 적용
    String getEncrypt(String pwd) {
        String pw="";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pwd.getBytes());
            byte [] pwdSalt = md.digest();

            StringBuffer sb = new StringBuffer();
            for(byte b: pwdSalt) {
                sb.append(String.format("%02x",b));
            }
            pw=sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return pw;
    }

    HttpStatusCode login(LoginRequestDto loginRequestDto) {
        String encPwd=getEncrypt(loginRequestDto.getPassword());

        User check = userRepository.findUserByIdAndPassword(loginRequestDto.getId(), encPwd);
        if(check!=null) {
            return HttpStatusCode.valueOf(202);
        } else {
            return HttpStatusCode.valueOf(404);
        }
    }
}
