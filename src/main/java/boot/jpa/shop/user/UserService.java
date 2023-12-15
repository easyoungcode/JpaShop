package boot.jpa.shop.user;

import boot.jpa.shop.domain.User;
import boot.jpa.shop.user.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    void signUp(SignUpRequestDto signUpRequestDto) {
        User user = User.builder()
                .name(signUpRequestDto.getName())
                .id(signUpRequestDto.getId())
                .password(signUpRequestDto.getPassword())
                .phone(signUpRequestDto.getPhone())
                .address(signUpRequestDto.getAddress())
                .userCode(UUID.randomUUID())
                .build();

        userRepository.save(user);
    }
}
