package com.upb.agripos.dao;

import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.Product;

public class ProductDAOImpl implements ProductDAO {

    private List<Product> products = new ArrayList<>();

    @Override
    public void insert(Product product) throws Exception {
        // Check if product with same code already exists
        for (Product p : products) {
            if (p.getCode().equals(product.getCode())) {
                throw new Exception("Product with code " + product.getCode() + " already exists");
            }
        }
        products.add(product);
    }

    @Override
    public void delete(String code) throws Exception {
        Product toRemove = null;
        for (Product p : products) {
            if (p.getCode().equals(code)) {
                toRemove = p;
                break;
            }
        }
        if (toRemove == null) {
            throw new Exception("Product with code " + code + " not found");
        }
        products.remove(toRemove);
    }

    @Override
    public List<Product> findAll() throws Exception {
        return new ArrayList<>(products);
    }
}
