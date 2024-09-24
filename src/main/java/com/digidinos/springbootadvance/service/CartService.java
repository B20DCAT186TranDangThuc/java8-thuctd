package com.digidinos.springbootadvance.service;

import com.digidinos.springbootadvance.entity.Cart;
import com.digidinos.springbootadvance.entity.CartItem;
import com.digidinos.springbootadvance.entity.Product;
import com.digidinos.springbootadvance.form.OrderDetailInfo;
import com.digidinos.springbootadvance.repository.AccountRepository;
import com.digidinos.springbootadvance.repository.CartItemRepository;
import com.digidinos.springbootadvance.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountRepository accountRepository;

    public Cart createCart(Cart cart) {
        cart.setCreateAt(LocalDateTime.now());
        cart.setUpdateAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    public boolean addCartItem(Long productId, Integer quantity, Long accountId) {
        // Tìm kiếm Cart dựa trên accountId, nếu không có thì tạo mới
        Cart cart = cartRepository.findByAccountId(accountId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setAccount(accountRepository.findById(accountId)
                    .orElseThrow(() -> new RuntimeException("Account not found: " + accountId)));
            newCart.setCreateAt(LocalDateTime.now());
            newCart.setUpdateAt(LocalDateTime.now());
            cartRepository.save(newCart);
            return newCart;
        });

        // Tìm Product theo productId
        Product product = productService.findProduct(productId);
        if (product == null) {
            throw new RuntimeException("Product not found: " + productId);
        }

        // Kiểm tra xem sản phẩm này đã có trong giỏ hàng chưa
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

        if (existingCartItem.isPresent()) {
            // Nếu sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng và tổng tiền
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setAmount(cartItem.getQuantity() * product.getPrice());
            cartItem.setUpdateAt(LocalDateTime.now());
            cartItemRepository.save(cartItem);
        } else {
            // Nếu sản phẩm chưa có, thêm mới vào giỏ hàng
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .price(product.getPrice())
                    .quantity(quantity)
                    .amount(quantity * product.getPrice())
                    .build();
            cartItem.setCreateAt(LocalDateTime.now());
            cartItem.setUpdateAt(LocalDateTime.now());
            cartItemRepository.save(cartItem);
        }

        return true; // Đã thêm hoặc cập nhật sản phẩm thành công
    }


    public List<CartItem> getAllCartItemByAccountId(Long accountId) {

        // Tìm Cart theo accountId
        Cart cart = cartRepository.findByAccountId(accountId)
                .orElseThrow(() -> new RuntimeException("Cart not found for accountId: " + accountId));

        return cartItemRepository.findByCartId(cart.getId());

    }

    public Cart findCartByAccountId(Long accountId) {
        return cartRepository.findByAccountId(accountId).get();
    }

}
