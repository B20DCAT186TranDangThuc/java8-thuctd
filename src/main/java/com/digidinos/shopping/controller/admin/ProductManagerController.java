package com.digidinos.shopping.controller.admin;

import com.digidinos.shopping.dao.ImageDAO;
import com.digidinos.shopping.dao.ProductDAO;
import com.digidinos.shopping.entity.Image;
import com.digidinos.shopping.entity.Product;
import com.digidinos.shopping.form.ProductForm;
import com.digidinos.shopping.model.ProductInfo;
import com.digidinos.shopping.service.UploadService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin/products")
public class ProductManagerController {

    @Autowired
    ProductDAO productDAO;

    @GetMapping()
    public String products(Model model) {
        model.addAttribute("products", productDAO.findAllProductInfo());

        return "/admin/product/list";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductForm());
        return "/admin/product/add";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") ProductForm productForm,
                             RedirectAttributes redirectAttributes) throws IOException {

        boolean check = productDAO.createProduct(productForm);
        if (!check) {
            redirectAttributes.addAttribute("error", "Failed to create product");
            return "redirect:/admin/products/add";
        }

        return "redirect:/admin/products";
    }


    @GetMapping("/detail")
    public String getDetailProduct(@ModelAttribute("code") String code, Model model, RedirectAttributes redirectAttributes) {

        ProductInfo productInfo = productDAO.findProductInfo(code);

        if (productInfo == null) {
            redirectAttributes.addAttribute("error", "Product not found");
            return "redirect:/admin/products";
        }
        model.addAttribute("product", productInfo);
        return "/admin/product/detail";
    }


    @GetMapping("/update")
    public String getUpdatePage(@ModelAttribute("code") String code, Model model) {
        ProductForm productForm = productDAO.findProductForm(code);

        if (productForm == null) {
            return "redirect:/admin/products";
        }
        model.addAttribute("product", productForm);
        return "/admin/product/update";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") ProductForm productForm, RedirectAttributes redirectAttributes) throws IOException {
        boolean check = productDAO.updateProduct(productForm);
        if (!check) {
            redirectAttributes.addAttribute("error", "Failed to update product");
            return "redirect:/admin/products/update";
        }

        return "redirect:/admin/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(@ModelAttribute("code") String code, RedirectAttributes redirectAttributes) {
        // Gọi phương thức xóa tài khoản
        if (productDAO.deleteAccount(code)) {
            redirectAttributes.addFlashAttribute("infoDelete", "Account deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorDelete", "Failed to delete account.");
        }

        // Chuyển hướng về trang danh sách tài khoản
        return "redirect:/admin/products";
    }

    @GetMapping("/uploads/{productCode}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable("productCode") String productCode) {
        Product product = productDAO.findProduct(productCode);
        if (product != null && product.getImageData() != null) {
            // Tạo headers cho phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "image/jpeg"); // hoặc tùy thuộc vào định dạng ảnh của bạn (jpeg, png, etc.)
            return new ResponseEntity<>(product.getImageData(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
