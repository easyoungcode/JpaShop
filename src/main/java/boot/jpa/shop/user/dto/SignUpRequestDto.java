package boot.jpa.shop.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class SignUpRequestDto {
    private String name;
    private String id;
    private String password;
    private String phone;
    private String address;
}
