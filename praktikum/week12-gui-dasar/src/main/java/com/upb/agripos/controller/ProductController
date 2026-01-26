package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;

import javafx.scene.control.Alert;

public class ProductController {

    private final ProductService productService;
    private final ProductFormView view;

    public ProductController(ProductService productService, ProductFormView view) {
        this.productService = productService;
        this.view = view;
        loadProducts();
        initEvent();
    }
    private void loadProducts() {
        try {
            view.listView.getItems().clear();
            for (Product p : productService.getAll()) {
                view.listView.getItems().add(
                        p.getCode() + " - " + p.getName()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Gagal memuat data produk!");
        }
    }

    private String formatProduct(Product p) {
        return p.getCode() + " - " + p.getName() + " - Rp" + p.getPrice() + " - Stok: " + p.getStock();
    }
    private void initEvent() {
        view.btnAdd.setOnAction(event -> {
            try {
                Product p = new Product(
                        view.txtCode.getText(),
                        view.txtName.getText(),
                        Double.parseDouble(view.txtPrice.getText()),
                        Integer.parseInt(view.txtStock.getText())
                );

                productService.insert(p);
                view.clearForm();
                loadProducts();

            } catch (NumberFormatException e) {
                showAlert("Input angka tidak valid!");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Gagal menyimpan produk!");
            }
        });
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }
}