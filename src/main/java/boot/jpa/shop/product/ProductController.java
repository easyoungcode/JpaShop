package boot.jpa.shop.product;

import boot.jpa.shop.product.dto.InsertProductImgRequestDto;
import boot.jpa.shop.product.dto.InsertProductRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(value = "/product/new", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public int insertProduct(@RequestPart(value = "productInfo") InsertProductRequestDto productRequestDto,
                              @RequestPart(value = "productImg", required = false) List<MultipartFile> productImage,
                              HttpSession session)
    {
        String path = session.getServletContext().getRealPath("/save");
        HttpStatusCode statusCode = productService.insertProduct(productRequestDto, productImage, path);
        if(statusCode.is2xxSuccessful()) {
            return 1;
        } else {
            return 0;
        }
    }
}
