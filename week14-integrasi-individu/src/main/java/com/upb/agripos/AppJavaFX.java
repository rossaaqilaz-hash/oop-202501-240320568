package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.dao.JdbcProductDAO;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.PosView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        try {
            System.out.println("Hello World, I am Azzahra Ramadhani 240320561");

            JdbcProductDAO productDAO = JdbcProductDAO.getInstance();
            ProductService productService = new ProductService(productDAO);
            CartService cartService = new CartService();
            PosView view = new PosView();
            new PosController(productService, cartService, view);

            stage.setScene(new Scene(view, 900, 600));
            stage.setTitle("Agri-POS - Azzahra Ramadhani 240320561");
            stage.setOnCloseRequest(e -> {
                try {
                    productDAO.closeConnection();
                    System.out.println("Application closed successfully.");
                } catch (Exception ex) {
                    System.err.println("Error closing database connection: " + ex.getMessage());
                }
            });
            stage.show();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
