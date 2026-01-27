# Laporan Praktikum Minggu 14
Topik: Integrasi Individu (OOP + Database + GUI)

## Identitas
- Nama  : Rossa Aqila Zahra
- NIM   : 240320568
- Kelas : 3DSRA

---

## Tujuan
- Mampu mengintegrasikan konsep OOP (Bab 1-5) ke dalam satu aplikasi yang utuh.
- Mampu mengimplementasikan rancangan UML + SOLID (Bab 6) menjadi kode nyata.
- Mampu mengintegrasikan Collections + Keranjang (Bab 7) ke alur aplikasi.
- Mampu menerapkan exception handling (Bab 9) untuk validasi dan error flow.
- Mampu menerapkan pattern + unit testing (Bab 10) pada bagian yang relevan.
- Mampu menghubungkan aplikasi dengan database via DAO + JDBC (Bab 11).
- Mampu menyajikan aplikasi berbasis JavaFX (Bab 12-13) yang terhubung ke backend.

---

## Ringkasan Aplikasi

**Agri-POS** adalah aplikasi Point of Sale (kasir sederhana) berbasis JavaFX yang mengintegrasikan konsep OOP, database PostgreSQL, dan GUI.

Fitur yang didukung:
1. Manajemen Produk: Menampilkan, menambah, dan menghapus produk dari database.
2. Keranjang Belanja: Menambahkan produk ke keranjang, menampilkan daftar item, menghitung total, clear cart, dan checkout dengan itemized receipt.
3. Validasi & Exception Handling: Validasi input (kosong, format angka, stok tersedia) dengan IllegalArgumentException dan Exception.

---

## Keterangan Integrasi Bab 1-13

Berikut integrasi konsep OOP dari Bab 1-13 dalam aplikasi Agri-POS:

- **Bab 1**: Menampilkan identitas di console: "Hello World, I am Difa Rizkiana-240320564". Setup project Java dengan Maven.
- **Bab 2**: Class Product dengan enkapsulasi (getter/setter). Class Cart, CartItem sebagai model domain.
- **Bab 3**: Exception extend dari Exception class.
- **Bab 4**: Interface ProductDAO dengan implementasi JdbcProductDAO (polymorphism).
- **Bab 5**: Interface ProductDAO sebagai abstraksi data access. Implementasi konkret JdbcProductDAO.
- **Bab 6**: SOLID principles - SRP (setiap class single responsibility), OCP (interface terbuka extension), LSP (implementasi replaceable), ISP (interface tidak memaksa unused methods), DIP (View → Controller → Service → DAO).
- **Bab 7**: Cart menggunakan Map<String, CartItem> untuk efisiensi lookup. List<CartItem> untuk return items. Collections digunakan dalam alur aplikasi.
- **Bab 9**: Exception handling di Service layer (IllegalArgumentException), Controller dengan try-catch, error flow ke user via Alert.
- **Bab 10**: MVC Pattern (Model-View-Controller separation). JUnit Testing (CartServiceTest).
- **Bab 11**: JDBC connection PostgreSQL via DriverManager. CRUD operations via ProductDAO interface. PreparedStatement untuk SQL injection prevention.
- **Bab 12-13**: JavaFX application dengan TableView dan ListView. Event-driven programming. MVC architecture. Itemized receipt pada checkout.

---

## Langkah Praktikum

1. Setup database `agripos` dan tabel `products`, insert sample data.
2. Buat Model layer (Product, Cart, CartItem) dengan Collections.
3. Buat DAO layer (ProductDAO interface, JdbcProductDAO implementation).
4. Buat Service layer (ProductService, CartService) dengan validasi.
5. Buat Controller (PosController) dengan event handlers dan exception handling.
6. Buat View (PosView) dengan JavaFX components.
7. Buat Main (AppJavaFX) dengan identity log dan dependency injection.
8. Buat Unit Test (CartServiceTest).
9. Commit dan push dengan pesan `week14-integrasi-individu`.

---

## Kode Program
1. AppJavaFX.java
```java
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
            System.out.println("Hello World, I am Difa Rizkiana-240320564");

            Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos",
                "postgres",
                "postgres"
            );
            
            JdbcProductDAO productDAO = new JdbcProductDAO(connection);
            ProductService productService = new ProductService(productDAO);
            CartService cartService = new CartService();
            PosView view = new PosView();
            new PosController(productService, cartService, view);
            
            stage.setScene(new Scene(view, 900, 600));
            stage.setTitle("Agri-POS - Difa Rizkiana Fauziyah 240320564");
            stage.setOnCloseRequest(e -> {
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                    }
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
```

---

## Hasil Eksekusi

### Screenshot 1: Aplikasi Utama
https://github.com/rossaaqilaz-hash/oop-202501-240320568/blob/76dc6704830e30dd4961f87f416227befdd30deb/praktikum/week14-integrasi-individu/screenshots/image.jpeg

### Screenshot 2: JUnit Test Results
![Screenshot JUnit](screenshots/junit_result.png)

---

## Artefak UML

Aplikasi menggunakan UML dari Bab 6 yang diupdate untuk integrasi:

- **Use Case**: UC-Produk (Tambah, Lihat, Hapus), UC-Cart (Add to Cart, Clear, Checkout).
- **Class Diagram**: Menunjukkan layer AppJavaFX → PosController ↔ PosView, ProductService + CartService, ProductDAO (interface) → JdbcProductDAO, Cart → CartItem → Product.
- **Sequence Diagram**: Tambah Produk (User → View → Controller → Service → DAO → DB).
- **Activity Diagram**: Tambah ke Keranjang dengan validasi selection dan quantity.

---

## Tabel Traceability Bab 6 → Implementasi

| Artefak | Referensi | Handler/Trigger | Controller/Service | DAO | Dampak |
|---------|-----------|-----------------|-------------------|-----|--------|
| Use Case | UC-Produk-01 Tambah | Button "Add Product" | `PosController.handleAddProduct()` → `ProductService.insert()` | `ProductDAO.insert()` | DB insert + TableView reload |
| Use Case | UC-Produk-02 Hapus | Button "Delete Product" | `PosController.handleDeleteProduct()` → `ProductService.delete(code)` | `ProductDAO.delete(code)` | DB delete + TableView reload |
| Use Case | UC-Produk-03 Lihat | Application startup + Refresh | `PosController.loadProducts()` → `ProductService.getAll()` | `ProductDAO.findAll()` | TableView populated |
| Use Case | UC-Cart-01 Tambah ke Keranjang | Button "Add to Cart" | `PosController.handleAddToCart()` → `CartService.addItem()` | - | Cart updated + ListView + Total |
| Use Case | UC-Cart-02 Clear Cart | Button "Clear Cart" | `PosController.handleClearCart()` → `CartService.clearCart()` | - | Cart cleared + UI reset |
| Use Case | UC-Cart-03 Checkout | Button "Checkout" | `PosController.handleCheckout()` → `CartService.calculateTotal()` | - | Show itemized receipt + Clear cart |
| Sequence | SD-Produk-01 Insert | Form submit | `PosController` → `ProductService` → `ProductDAO` → DB | JDBC INSERT | Product added to DB |
| Sequence | SD-Produk-02 Delete | Delete button | `PosController` → `ProductService` → `ProductDAO` → DB | JDBC DELETE | Product removed |
| Activity | AD-Cart-01 Add to Cart | Select + Quantity + Add | `PosController` → validate → `CartService` → `Cart` | - | Item in cart |
| Class | CLS-Model Product | N/A | Used by all layers | Passed to DAO | Data entity |
| Class | CLS-Service ProductService | N/A | Business logic validation | Calls DAO | Service layer |
| Class | CLS-DAO ProductDAO | N/A | Interface abstraction | Implemented by JDBC | Data access |
| Pattern | MVC Pattern | N/A | View-Controller-Service separation | Service-DAO layer | Clean architecture |

---

## Analisis

### 1. Cara Kerja Kode

Program ini mengintegrasikan konsep OOP Bab 1-13 dalam aplikasi Agri-POS dengan arsitektur MVC. Layer Model memiliki Product (data entity), Cart (menggunakan Map<String, CartItem> untuk efficient lookup), dan CartItem. Layer DAO menggunakan interface ProductDAO dan implementasi JdbcProductDAO dengan PreparedStatement. Layer Service (ProductService, CartService) melakukan validasi dengan IllegalArgumentException sebelum memanggil DAO. Layer Controller (PosController) sebagai mediator View-Service, menerapkan DIP (View tidak akses DAO langsung). Layer View (PosView) hanya berisi JavaFX UI components tanpa logika SQL. AppJavaFX menampilkan identity log dan melakukan dependency injection DAO → Service → Controller → View.

### 2. Perbedaan dengan Praktikum Sebelumnya

Week 14 melakukan integrasi end-to-end dari semua konsep OOP. Week sebelumnya fokus topik spesifik, sedangkan week 14 menggabungkan semuanya dengan arsitektur konsisten. SOLID principles (khususnya DIP) diterapkan ketat, exception handling terstruktur (validation di Service, database di DAO), GUI terintegrasi penuh dengan database via Controller, Collections digunakan nyata untuk keranjang belanja, dan unit testing komprehensif.

### 3. Kendala yang Dihadapi

Kendala utama adalah memastikan dependency flow (View → Controller → Service → DAO) tanpa skip layer, solusinya code review setiap class. Exception handling di multiple layers diatasi dengan validation di Service (throw IllegalArgumentException) dan try-catch di Controller untuk Alert ke user. Format itemized receipt diatasi dengan StringBuilder dan String.format(). Testing CartService dengan stock validation diatasi dengan mock Product objects di @BeforeEach.

---

## Kesimpulan

Program ini berhasil mengintegrasikan konsep OOP Bab 1-13 ke dalam aplikasi Agri-POS yang fungsional. Arsitektur MVC dengan pemisahan layer jelas memenuhi prinsip SOLID (khususnya DIP). Collections Framework (Map dan List) digunakan efektif dalam Cart. Exception handling diterapkan benar (IllegalArgumentException untuk validation, Exception untuk database errors). Unit testing memastikan business logic berjalan benar. Database integration dengan PostgreSQL via JDBC dan PreparedStatement untuk CRUD operations berfungsi baik. JavaFX UI menyediakan interface user-friendly dengan itemized receipt pada checkout. Implementasi ini menunjukkan bagaimana prinsip OOP diterapkan nyata dalam membangun aplikasi terstruktur, maintainable, dan mudah dikembangkan.

---
