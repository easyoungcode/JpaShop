package boot.jpa.shop.product;

import boot.jpa.shop.domain.Product;
import boot.jpa.shop.domain.ProductImg;
import boot.jpa.shop.product.dto.InsertProductImgRequestDto;
import boot.jpa.shop.product.dto.InsertProductRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImgRepository productImgRepository;

    @Transactional
    public HttpStatusCode insertProduct(InsertProductRequestDto insertProductRequestDto, List<MultipartFile> productImage, String path) {

        // 게시글 저장
        Product product = Product.builder()
                .productName(insertProductRequestDto.getProductName())
                .price(insertProductRequestDto.getPrice())
                .inventory(insertProductRequestDto.getInventory())
                .build();
        productRepository.save(product);

        StringBuilder uuid = new StringBuilder();
        String ext;
        try {
            for(MultipartFile multipartFile:productImage) {
                uuid.append(UUID.randomUUID());
                ext = extractExt(multipartFile.getOriginalFilename());
                multipartFile.transferTo(new File(path + "/" + uuid + "." + ext));

                // 게시글 이미지 저장
                ProductImg productImg = ProductImg.builder()
                        .productImgName(uuid.toString() + "." + ext)
                        .product(product)
                        .build();
                productImgRepository.save(productImg);
            }
                return HttpStatusCode.valueOf(202);
            } catch (IOException | IllegalStateException e) {
                e.printStackTrace();
                return HttpStatusCode.valueOf(500);
            }
    }

    //원래 파일명에서 확장자 뽑기(.jpg, .png ...)
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
