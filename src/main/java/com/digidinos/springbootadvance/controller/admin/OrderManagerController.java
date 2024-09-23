package com.digidinos.springbootadvance.controller.admin;

import com.digidinos.springbootadvance.model.OrderInfo;
import com.digidinos.springbootadvance.model.ProductInfo;
import com.digidinos.springbootadvance.service.OrderService;
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

        model.addAttribute("productPage", orderPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", orderPage.getNumber() + 1);
        model.addAttribute("pageSize", orderPage.getTotalPages());
        return "admin/order/list";
    }

}
