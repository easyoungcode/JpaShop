package boot.jpa.shop.product;

import boot.jpa.shop.domain.Product;
import boot.jpa.shop.product.dto.InsertProductImgRequestDto;
import boot.jpa.shop.product.dto.InsertProductRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(value = "/product/new", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public int insertProduct(@RequestPart(value = "productInfo") InsertProductRequestDto productRequestDto,
                              @RequestPart(value = "productFile", required = false) List<MultipartFile> productFiles,
                              HttpSession session)
    {
        String path = session.getServletContext().getRealPath("/save");
        HttpStatusCode statusCode = productService.insertProduct(productRequestDto, productFiles, path);
        if(statusCode.is2xxSuccessful()) {
            return 1;
        } else {
            return 0;
        }
    }

    @GetMapping(value = "/product/new")
    public ResponseEntity<List<Product>> allNewProduct() {
        return ResponseEntity.ok(productService.findAllProduct());
    }
}
