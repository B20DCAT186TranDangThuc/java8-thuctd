package com.digidinos.springbootadvance.controller.client;

import com.digidinos.springbootadvance.form.OrderDetailInfo;
import com.digidinos.springbootadvance.model.ProductInfo;
import com.digidinos.springbootadvance.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/detail/{productId}")
    public String productDetail(@PathVariable("productId") Long productId, Model model) {

        model.addAttribute("product", productService.findProduct(productId));

        model.addAttribute("orderItem", new OrderDetailInfo());

        return "client/product/detail";
    }

    @PostMapping("/switch")
    public String handleOrderItem(@ModelAttribute("orderItem") OrderDetailInfo orderDetailInfo,
                                  @RequestParam("submit") String action, Model model) {

        if ("addtocart".equals(action)) {
            // xử lý add to cart rồi rả về thông báo


            return "redirect:/products/addToCart";
        } else if ("buy".equals(action)) {
            // chuyển hướng sang trang mua ngay


            return "redirect:/products/buyNow";     // Gọi phương thức xử lý buy now
        }
        return "redirect:/error";          // Trường hợp không hợp lệ
    }

}

