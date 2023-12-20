package boot.jpa.shop.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class InsertProductRequestDto {
    private String productName;
    private int price;
    private int inventory;
}
