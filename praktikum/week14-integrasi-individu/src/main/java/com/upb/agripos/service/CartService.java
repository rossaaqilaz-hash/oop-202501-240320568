package com.upb.agripos.service;

import java.util.List;

import com.upb.agripos.model.Cart;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;

public class CartService {

    private Cart cart;

    public CartService() {
        this.cart = new Cart();
    }

    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if (quantity > product.getStock()) {
            throw new IllegalArgumentException("Quantity exceeds available stock");
        }
        cart.addItem(product, quantity);
    }

    public void removeItem(String productCode) {
        if (productCode == null || productCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Product code cannot be empty");
        }
        cart.removeItem(productCode);
    }

    public void updateQuantity(String productCode, int newQuantity) {
        if (productCode == null || productCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Product code cannot be empty");
        }
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        cart.updateQuantity(productCode, newQuantity);
    }

    public List<CartItem> getItems() {
        return cart.getItems();
    }

    public double calculateTotal() {
        return cart.getTotal();
    }

    public int getItemCount() {
        return cart.getItemCount();
    }

    public void clearCart() {
        cart.clear();
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public Cart getCart() {
        return cart;
    }
}
