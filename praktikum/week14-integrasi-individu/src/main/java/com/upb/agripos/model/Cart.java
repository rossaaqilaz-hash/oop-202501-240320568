package com.upb.agripos.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<String, CartItem> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addItem(Product product, int quantity) {
        String code = product.getCode();
        if (items.containsKey(code)) {
            CartItem existing = items.get(code);
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            items.put(code, new CartItem(product, quantity));
        }
    }

    public void removeItem(String productCode) {
        items.remove(productCode);
    }

    public void updateQuantity(String productCode, int newQuantity) {
        if (items.containsKey(productCode)) {
            if (newQuantity <= 0) {
                removeItem(productCode);
            } else {
                items.get(productCode).setQuantity(newQuantity);
            }
        }
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items.values()) {
            total += item.getSubtotal();
        }
        return total;
    }

    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
