package boot.jpa.shop.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class InsertProductImgRequestDto {
    private String productImgName;
}
