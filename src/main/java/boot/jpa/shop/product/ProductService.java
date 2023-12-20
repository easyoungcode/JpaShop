package boot.jpa.shop.product;

import boot.jpa.shop.domain.Product;
import boot.jpa.shop.domain.ProductImg;
import boot.jpa.shop.product.dto.InsertProductImgRequestDto;
import boot.jpa.shop.product.dto.InsertProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImgRepository productImgRepository;

    void insertProduct(InsertProductRequestDto insertProductRequestDto, InsertProductImgRequestDto insertProductImgRequestDto, String path) {

        // 게시글 저장
        Product product = Product.builder()
                .productName(insertProductRequestDto.getProductName())
                .price(insertProductRequestDto.getPrice())
                .inventory(insertProductRequestDto.getInventory())
                .build();
        productRepository.save(product);

        File file = new File(path);

        /* MultipartFile uploadFile;
        try {
            uploadFile.transferTo(new File(path + "/" + insertProductImgRequestDto.getProductImgName()));
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        } */

        // 게시글 이미지 저장
        ProductImg productImg = ProductImg.builder()
                .productImgName(UUID.randomUUID().toString())
                .product(product)
                .build();
//        productImgRepository.save(productImg);
    }


}
