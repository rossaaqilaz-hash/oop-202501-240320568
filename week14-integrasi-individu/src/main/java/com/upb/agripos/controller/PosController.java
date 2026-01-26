package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.PosView;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * PosController - Controller layer connecting View with Service
 * Handles user interactions and delegates business logic to services
 * Implements DIP: View -> Controller -> Service -> DAO
 */
public class PosController {

    private final ProductService productService;
    private final CartService cartService;
    private final PosView view;

    public PosController(ProductService productService, CartService cartService, PosView view) {
        this.productService = productService;
        this.cartService = cartService;
        this.view = view;
        
        initializeEvents();
        loadProducts();
        updateCartDisplay();
    }

    private void initializeEvents() {
        // Product Management Events
        view.btnAddProduct.setOnAction(e -> handleAddProduct());
        view.btnDeleteProduct.setOnAction(e -> handleDeleteProduct());
        view.btnRefresh.setOnAction(e -> loadProducts());
        
        // Cart Events
        view.btnAddToCart.setOnAction(e -> handleAddToCart());
        view.btnClearCart.setOnAction(e -> handleClearCart());
        view.btnCheckout.setOnAction(e -> handleCheckout());
    }

    private void handleAddProduct() {
        try {
            String code = view.txtCode.getText().trim();
            String name = view.txtName.getText().trim();
            String priceText = view.txtPrice.getText().trim();
            String stockText = view.txtStock.getText().trim();

            if (code.isEmpty() || name.isEmpty() || priceText.isEmpty() || stockText.isEmpty()) {
                showAlert(AlertType.WARNING, "Validation Error", "All fields required!");
                return;
            }

            Product product = new Product(code, name, Double.parseDouble(priceText), Integer.parseInt(stockText));
            productService.insert(product);
            showAlert(AlertType.INFORMATION, "Success", "Product added!");
            view.clearProductForm();
            loadProducts();
            
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Input Error", "Invalid number format!");
        } catch (IllegalArgumentException e) {
            showAlert(AlertType.ERROR, "Validation Error", e.getMessage());
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void handleDeleteProduct() {
        try {
            Product selected = view.tableProducts.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert(AlertType.WARNING, "No Selection", "Select a product to delete!");
                return;
            }

            productService.delete(selected.getCode());
            showAlert(AlertType.INFORMATION, "Success", "Product deleted!");
            loadProducts();
            
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void handleAddToCart() {
        try {
            Product selected = view.tableProducts.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert(AlertType.WARNING, "No Selection", "Select a product!");
                return;
            }

            String qtyText = view.txtQuantity.getText().trim();
            if (qtyText.isEmpty()) {
                showAlert(AlertType.WARNING, "Input Required", "Enter quantity!");
                return;
            }

            cartService.addItem(selected, Integer.parseInt(qtyText));
            showAlert(AlertType.INFORMATION, "Success", "Added to cart!");
            view.txtQuantity.clear();
            updateCartDisplay();
            
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Input Error", "Invalid quantity!");
        } catch (IllegalArgumentException e) {
            showAlert(AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void handleClearCart() {
        cartService.clearCart();
        updateCartDisplay();
        showAlert(AlertType.INFORMATION, "Success", "Cart cleared!");
    }

    private void handleCheckout() {
        if (cartService.isEmpty()) {
            showAlert(AlertType.WARNING, "Empty Cart", "Add items first.");
            return;
        }

        double total = cartService.calculateTotal();
        StringBuilder receipt = new StringBuilder();
        receipt.append("Items:\n");
        cartService.getItems().forEach(item -> {
            receipt.append(String.format(
                "- %s (x%d) @ Rp %.2f = Rp %.2f\n",
                item.getProduct().getName(),
                item.getQuantity(),
                item.getProduct().getPrice(),
                item.getSubtotal()
            ));
        });
        receipt.append(String.format("\nTotal: Rp %.2f\nThank you for your purchase!", total));

        showAlert(AlertType.INFORMATION, "Checkout", receipt.toString());
        cartService.clearCart();
        updateCartDisplay();
    }

    private void loadProducts() {
        try {
            view.tableProducts.getItems().clear();
            view.tableProducts.getItems().addAll(productService.getAllProducts());
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Database Error", "Failed to load products: " + e.getMessage());
        }
    }

    private void updateCartDisplay() {
        view.listCart.getItems().clear();
        cartService.getItems().forEach(item -> {
            view.listCart.getItems().add(item.toString());
        });
        view.lblTotal.setText(String.format("Total: Rp %.2f", cartService.calculateTotal()));
        view.lblItemCount.setText("Items: " + cartService.getItemCount());
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
