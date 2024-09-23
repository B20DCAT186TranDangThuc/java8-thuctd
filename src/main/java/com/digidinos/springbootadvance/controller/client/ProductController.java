package com.digidinos.springbootadvance.controller.client;

import com.digidinos.springbootadvance.model.ProductInfo;
import com.digidinos.springbootadvance.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public String getPageProduct(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                 Model model) {

        Pageable pageable = (Pageable) PageRequest.of(page, 4, Sort.by("updateAt").descending());
        Page<ProductInfo> productPage = productService.handleSearchAndFilterAccount("", pageable);

        model.addAttribute("productPage", productPage);
        model.addAttribute("page", productPage.getNumber() + 1);
        model.addAttribute("pageSize", productPage.getTotalPages());
        return "client/product/list";
    }

}
