package com.digidinos.springbootadvance.controller.admin;

import com.digidinos.springbootadvance.form.OrderItem;
import com.digidinos.springbootadvance.form.StatusOrderForm;
import com.digidinos.springbootadvance.model.OrderInfo;
import com.digidinos.springbootadvance.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/admin/orders")
public class OrderManagerController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public String orderManager(@RequestParam(name = "page", defaultValue = "0") Integer page,
                               @RequestParam(name = "keyword", required = false) String keyword,
                               Model model) {
        Pageable pageable = (Pageable) PageRequest.of(page, 5, Sort.by("orderDate").descending());
        Page<OrderInfo> orderPage = orderService.searchOrders(keyword, pageable);

        model.addAttribute("localDateTimeFormat", new SimpleDateFormat("yyyy-MM-dd'T'hh:mm"));
        model.addAttribute("orderPage", orderPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", orderPage.getNumber() + 1);
        model.addAttribute("pageSize", orderPage.getTotalPages());

        return "admin/order/list";
    }

    @GetMapping("/detail/{orderId}")
    public String detail(@PathVariable("orderId") Long orderId, Model model) {

        model.addAttribute("orderDetail", orderService.handleFindByOrderId(orderId).get());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        model.addAttribute("formatter", formatter);
        return "admin/order/detail";
    }

    @GetMapping("/delete/{orderId}")
    public String delete(@PathVariable("orderId") Long orderId, RedirectAttributes redirectAttributes) {
        try {

            if (orderService.deleteOrder(orderId)) {
                redirectAttributes.addFlashAttribute("successMessage", "Order deleted successfully.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Order not found or could not be deleted.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while deleting the order: " + e.getMessage());
        }

        return "redirect:/admin/orders";
    }

    @GetMapping("/update/{orderId}")
    public String update(@PathVariable("orderId") Long orderId, Model model) {

        OrderInfo orderInfo = orderService.handleFindByOrderId(orderId).get();

        model.addAttribute("orderStatus", StatusOrderForm.builder().
                orderId(orderId).
                status(orderInfo.getStatus())
                .build());

        model.addAttribute("orderInfo", orderInfo);

        return "admin/order/update";
    }

    @PostMapping("/update/status")
    public String updateStatus(@ModelAttribute("orderStatus") StatusOrderForm form) {

        orderService.updateStatus(form);

        return "redirect:/admin/orders/update/" + form.getOrderId();
    }

    @DeleteMapping("/removeProduct/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long productId) {
        // Logic để xóa sản phẩm khỏi đơn hàng
        boolean isRemoved = orderService.removeOrderItemFromOrder(productId);

        if (isRemoved) {
            return ResponseEntity.noContent().build(); // Trả về 204 No Content nếu xóa thành công
        } else {
            return ResponseEntity.notFound().build(); // Trả về 404 Not Found nếu không tìm thấy sản phẩm
        }
    }

    @PostMapping("/updateOrderItem")
    public String updateOrderItem(@ModelAttribute("orderInfo") OrderInfo OrderInfo) {


        return "redirect:/admin/orders/update/" + OrderInfo.getOrderId();

    }
}
