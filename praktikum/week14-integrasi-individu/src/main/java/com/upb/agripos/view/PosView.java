package com.upb.agripos.view;

import com.upb.agripos.model.Product;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PosView extends BorderPane {

    // Product Management Components
    public TextField txtCode = new TextField();
    public TextField txtName = new TextField();
    public TextField txtPrice = new TextField();
    public TextField txtStock = new TextField();
    public Button btnAddProduct = new Button("Add Product");
    public Button btnDeleteProduct = new Button("Delete Product");
    public Button btnRefresh = new Button("Refresh");
    public TableView<Product> tableProducts = new TableView<>();

    // Cart Components
    public TextField txtQuantity = new TextField();
    public Button btnAddToCart = new Button("Add to Cart");
    public Button btnClearCart = new Button("Clear Cart");
    public Button btnCheckout = new Button("Checkout");
    public ListView<String> listCart = new ListView<>();
    public Label lblTotal = new Label("Total: Rp 0.00");
    public Label lblItemCount = new Label("Items: 0");

    public PosView() {
        setupUI();
    }

    private void setupUI() {
        setPadding(new Insets(15));
        
        // Left Side: Product Management
        VBox leftPane = createProductManagementPane();
        
        // Right Side: Shopping Cart
        VBox rightPane = createCartPane();
        
        // Layout
        HBox mainLayout = new HBox(16);
        mainLayout.getChildren().addAll(leftPane, rightPane);
        setCenter(mainLayout);

        // Title (left aligned, simple)
        Label title = new Label("Agri-POS");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        BorderPane topPane = new BorderPane();
        topPane.setPadding(new Insets(0, 0, 12, 0));
        topPane.setLeft(title);
        setTop(topPane);
    }

    private VBox createProductManagementPane() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setPrefWidth(500);

        Label lblTitle = new Label("Product Management");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Form
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        
        formGrid.add(new Label("Code:"), 0, 0);
        formGrid.add(txtCode, 1, 0);
        formGrid.add(new Label("Name:"), 0, 1);
        formGrid.add(txtName, 1, 1);
        formGrid.add(new Label("Price:"), 0, 2);
        formGrid.add(txtPrice, 1, 2);
        formGrid.add(new Label("Stock:"), 0, 3);
        formGrid.add(txtStock, 1, 3);


        // Buttons
        HBox btnBox = new HBox(10);
        btnBox.getChildren().addAll(btnAddProduct, btnDeleteProduct, btnRefresh);

        // Table
        setupProductTable();
        tableProducts.setPrefHeight(300);

        vbox.getChildren().addAll(
            lblTitle,
            new Separator(),
            formGrid,
            btnBox,
            new Label("Product List:"),
            tableProducts
        );

        return vbox;
    }

    private void setupProductTable() {
        TableColumn<Product, String> colCode = new TableColumn<>("Code");
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCode.setPrefWidth(100);

        TableColumn<Product, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setPrefWidth(150);

        TableColumn<Product, Double> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPrice.setPrefWidth(100);

        TableColumn<Product, Integer> colStock = new TableColumn<>("Stock");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colStock.setPrefWidth(80);

        tableProducts.getColumns().addAll(colCode, colName, colPrice, colStock);
    }

    private VBox createCartPane() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setPrefWidth(350);

        Label lblTitle = new Label("Shopping Cart");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Quantity input
        HBox qtyBox = new HBox(10);
        qtyBox.setAlignment(Pos.CENTER_LEFT);
        Label lblQty = new Label("Quantity:");
        txtQuantity.setPrefWidth(80);
        txtQuantity.setPromptText("1");
        qtyBox.getChildren().addAll(lblQty, txtQuantity, btnAddToCart);
        btnAddToCart.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");

        // Cart list
        listCart.setPrefHeight(300);

        // Summary
        VBox summaryBox = new VBox(5);
        summaryBox.setPadding(new Insets(10));
        
        lblItemCount.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        lblTotal.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        summaryBox.getChildren().addAll(lblItemCount, lblTotal);

        // Action buttons
        HBox actionBox = new HBox(10);
        btnCheckout.setPrefWidth(100);
        actionBox.getChildren().addAll(btnClearCart, btnCheckout);

        vbox.getChildren().addAll(
            lblTitle,
            new Separator(),
            new Label("Select product and enter quantity:"),
            qtyBox,
            new Label("Cart Items:"),
            listCart,
            summaryBox,
            actionBox
        );

        return vbox;
    }

    public void clearProductForm() {
        txtCode.clear();
        txtName.clear();
        txtPrice.clear();
        txtStock.clear();
    }
}
