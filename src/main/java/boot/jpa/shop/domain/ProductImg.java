package boot.jpa.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productImgId;
    private String productImgName;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
