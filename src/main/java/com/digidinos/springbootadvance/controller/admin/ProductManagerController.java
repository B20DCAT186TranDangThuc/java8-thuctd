package com.digidinos.springbootadvance.controller.admin;


import com.digidinos.springbootadvance.entity.Product;
import com.digidinos.springbootadvance.form.ProductForm;
import com.digidinos.springbootadvance.model.ProductInfo;
import com.digidinos.springbootadvance.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/products")
public class ProductManagerController {

    @Autowired
    private ProductService productService;


    @GetMapping()
    public String getListProduct(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                 @RequestParam(name = "keyword", required = false) String keyword,
                                 Model model) {
        Pageable pageable = (Pageable) PageRequest.of(page, 5, Sort.by("updateAt").descending());
        Page<ProductInfo> productPage = productService.handleSearchAndFilterAccount(keyword, pageable);

        model.addAttribute("productPage", productPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", productPage.getNumber() + 1);
        model.addAttribute("pageSize", productPage.getTotalPages());

        return "admin/product/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("newProduct", new ProductForm());
        return "admin/product/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newProduct") ProductForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "admin/product/create";
        }

        if (productService.createProduct(form) == null) {
            redirectAttributes.addAttribute("error", "Create product failed");
            return "redirect:admin/products/create";
        }

        return "redirect:/admin/products";
    }

    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable("id") Long id, Model model) {

        model.addAttribute("product", productService.findProduct(id));
        return "admin/product/detail";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable("id") Long id, Model model) {
        Product product = productService.findProduct(id);
        ProductForm productForm = ProductForm.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();

        model.addAttribute("newProduct", productForm);
        return "admin/product/update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("newProduct") ProductForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasFieldErrors("code")) {
            bindingResult.rejectValue("code", null);
        }

        if (productService.updateProduct(form) == null) {
            redirectAttributes.addAttribute("error", "Create product failed");
            return "redirect:admin/products/create";
        }

        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        productService.deleteProduct(id);

        return "redirect:/admin/products";

    }

}
