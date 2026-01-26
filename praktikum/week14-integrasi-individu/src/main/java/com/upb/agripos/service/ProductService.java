package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;
import java.util.List;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() throws Exception {
        return productDAO.findAll();
    }

    public void addProduct(Product product) throws Exception {
        if (product.getCode() == null || product.getCode().isEmpty()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Harga harus > 0");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif");
        }

        productDAO.insert(product);
    }

    public void insert(Product product) throws Exception {
        addProduct(product);
    }

    public void deleteProduct(String code) throws Exception {
        productDAO.delete(code);
    }

    public void delete(String code) throws Exception {
        deleteProduct(code);
    }
}
