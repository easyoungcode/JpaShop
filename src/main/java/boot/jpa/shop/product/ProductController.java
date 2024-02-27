package boot.jpa.shop.product;

import boot.jpa.shop.domain.Product;
import boot.jpa.shop.product.dto.InsertProductImgRequestDto;
import boot.jpa.shop.product.dto.InsertProductRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
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

    // 상품 리스트
    @GetMapping("/product/list/new")
    public ModelAndView allNewProduct() {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.findAllProduct();
        log.debug("list: {}", productList);
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("newProduct");
        return modelAndView;
    }
}
