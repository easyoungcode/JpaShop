package boot.jpa.shop.product;

import boot.jpa.shop.product.dto.InsertProductImgRequestDto;
import boot.jpa.shop.product.dto.InsertProductRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product/new")
    public void insertProduct(@RequestPart(value = "productInfo") InsertProductRequestDto productRequestDto,
                              @RequestPart(value = "productImg")InsertProductImgRequestDto insertProductImgRequestDto,
                              HttpSession session)
    {
        String path = session.getServletContext().getRealPath("/save");
        productService.insertProduct(productRequestDto,insertProductImgRequestDto, path);
    }
}
