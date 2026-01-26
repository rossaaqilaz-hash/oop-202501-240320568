package com.upb.agripos;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;

class CartServiceTest {

    private CartService cartService;
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void setUp() {
        cartService = new CartService();
        product1 = new Product("BNH-001", "Benih Padi", 25000, 100);
        product2 = new Product("PUP-001", "Pupuk Organik", 50000, 50);
        product3 = new Product("PES-001", "Pestisida", 75000, 30);
    }

    @Test
    @DisplayName("Test add single item to cart")
    void testAddItemToCart() {
        cartService.addItem(product1, 2);
        
        assertEquals(1, cartService.getItemCount());
        assertEquals(50000, cartService.calculateTotal());
        assertFalse(cartService.isEmpty());
    }

    @Test
    @DisplayName("Test add multiple items to cart")
    void testAddMultipleItems() {
        cartService.addItem(product1, 2);
        cartService.addItem(product2, 1);
        cartService.addItem(product3, 3);
        
        assertEquals(3, cartService.getItemCount());
        assertEquals(50000 + 50000 + 225000, cartService.calculateTotal());
    }

    @Test
    @DisplayName("Test add same product increases quantity")
    void testAddSameProductIncreasesQuantity() {
        cartService.addItem(product1, 2);
        cartService.addItem(product1, 3);
        
        assertEquals(1, cartService.getItemCount());
        assertEquals(125000, cartService.calculateTotal()); // 5 * 25000
    }
    @Test
    @DisplayName("Test remove item from cart")
    void testRemoveItem() {
        cartService.addItem(product1, 2);
        cartService.addItem(product2, 1);
        
        cartService.removeItem("BNH-001");
        
        assertEquals(1, cartService.getItemCount());
        assertEquals(50000, cartService.calculateTotal());
    }

    @Test
    @DisplayName("Test update quantity")
    void testUpdateQuantity() {
        cartService.addItem(product1, 2);
        cartService.updateQuantity("BNH-001", 5);
        
        assertEquals(125000, cartService.calculateTotal());
    }

    @Test
    @DisplayName("Test update quantity to zero removes item")
    void testUpdateQuantityToZeroRemovesItem() {
        cartService.addItem(product1, 2);
        cartService.updateQuantity("BNH-001", 0);
        
        assertTrue(cartService.isEmpty());
    }

    @Test
    @DisplayName("Test clear cart")
    void testClearCart() {
        cartService.addItem(product1, 2);
        cartService.addItem(product2, 1);
        
        cartService.clearCart();
        
        assertTrue(cartService.isEmpty());
        assertEquals(0, cartService.getItemCount());
        assertEquals(0, cartService.calculateTotal());
    }

    @Test
    @DisplayName("Test get cart items")
    void testGetItems() {
        cartService.addItem(product1, 2);
        cartService.addItem(product2, 1);
        
        List<CartItem> items = cartService.getItems();
        
        assertEquals(2, items.size());
    }

    @Test
    @DisplayName("Test add null product throws exception")
    void testAddNullProductThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cartService.addItem(null, 1);
        });
        
        assertEquals("Product cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Test add with zero quantity throws exception")
    void testAddWithZeroQuantityThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cartService.addItem(product1, 0);
        });
        
        assertEquals("Quantity must be greater than 0", exception.getMessage());
    }

    @Test
    @DisplayName("Test add with negative quantity throws exception")
    void testAddWithNegativeQuantityThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cartService.addItem(product1, -5);
        });
        
        assertEquals("Quantity must be greater than 0", exception.getMessage());
    }

    @Test
    @DisplayName("Test add quantity exceeding stock throws exception")
    void testAddQuantityExceedingStockThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cartService.addItem(product1, 150); // stock is only 100
        });
        
        assertEquals("Quantity exceeds available stock", exception.getMessage());
    }

    @Test
    @DisplayName("Test calculate total with empty cart")
    void testCalculateTotalWithEmptyCart() {
        assertEquals(0, cartService.calculateTotal());
    }

    @Test
    @DisplayName("Test isEmpty returns true for new cart")
    void testIsEmptyForNewCart() {
        assertTrue(cartService.isEmpty());
    }
}
