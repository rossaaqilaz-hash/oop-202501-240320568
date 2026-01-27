# Laporan Praktikum Minggu 13
Topik: GUI Lanjutan JavaFX (TableView dan Lambda Expression)

## Identitas
- Nama  : Rossa Aqila Zahra
- NIM   : 240320568
- Kelas : 3DSRA

---

## Tujuan
1. Menampilkan data menggunakan TableView JavaFX.
2. Mengintegrasikan koleksi objek dengan GUI.
3. Menggunakan lambda expression untuk event handling.
4. Menghubungkan GUI dengan DAO secara penuh.
5. Membangun antarmuka GUI Agri-POS yang lebih interaktif.

---

## Dasar Teori 
1. TableView JavaFX
   
   TableView merupakan komponen JavaFX yang digunakan untuk menampilkan data dalam bentuk tabel. Setiap baris merepresentasikan satu objek, sedangkan kolom merepresentasikan atribut dari objek tersebut. Pada praktikum ini, TableView<Product> digunakan untuk menampilkan daftar produk dari database.

2. Lambda Expression 

   Lambda expression adalah fitur Java yang memungkinkan penulisan kode event handler menjadi lebih ringkas dan mudah dibaca. Pada JavaFX, lambda expression sering digunakan pada event seperti klik tombol, misalnya pada tombol tambah dan hapus produk.

3. Integrasi MVC dan DAO

   Pada implementasi ini:
   - View hanya bertanggung jawab terhadap tampilan GUI.
   - Controller menangani event dan memanggil service.
   - Service mengelola logika bisnis.
   - DAO menangani interaksi langsung dengan database PostgreSQL.
   
   Pendekatan ini memastikan pemisahan tanggung jawab sesuai prinsip Single Responsibility dan Dependency Inversion Principle (DIP).

---

## Langkah Praktikum
1. Mengganti ListView menjadi TableView.
2. Menambahkan tombol Hapus Produk.
3. Membuat metode loadData() untuk reload data dari database.
4. Menggunakan lambda expression pada event handler.
5. Integrasi penuh MVC + DAO.
6. Commit message: week12-gui-lanjutan: [fitur] [deskripsi singkat]

---

## Kode Program
AppJavaFX.java  

```java
package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductTableView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos",
                "postgres",
                "postgres"
        );

        ProductService service = new ProductService(new ProductDAOImpl(conn));
        ProductTableView view = new ProductTableView();
        new ProductController(service, view);

        stage.setScene(new Scene(view, 600, 500));
        stage.setTitle("Agri-POS TableView - Difa Rizkiana Fauziyah (240320564)");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

---

## Hasil Eksekusi

[![Output_TableViewProduct](screenshots/Output_TableViewProduct.png)](https://github.com/rossaaqilaz-hash/oop-202501-240320568/blob/5d60050030e13553388bb5135a5091a463a660ad/praktikum/week13-gui-lanjutan/week13-gui-lanjutan/screenshots/Output.png)
---

## Analisis
1. Cara Kerja Kode Program

   Program GUI lanjutan Agri-POS menggunakan ```TableView``` JavaFX untuk menampilkan data produk yang diambil langsung dari database PostgreSQL. Data dimuat melalui method ```loadData()``` yang memanggil ```ProductService.findAll()``` dan diteruskan ke ```ProductDAO.findAll()```, kemudian ditampilkan dalam bentuk ObservableList pada ```TableView```.

   Event handling pada tombol Tambah Produk dan Hapus Produk menggunakan lambda expression sehingga kode menjadi lebih ringkas. Saat tombol Hapus Produk ditekan, sistem akan mengambil data yang dipilih pada ```TableView```, memanggil proses penghapusan melalui service, lalu memuat ulang data agar tampilan selalu sinkron dengan database.

   Arsitektur MVC tetap diterapkan dengan baik, di mana ```View``` hanya bertugas menampilkan data dan menerima input pengguna, sedangkan logika bisnis dan akses database ditangani oleh ```Service``` dan ```DAO```. Alur ini telah sesuai dengan desain UML Bab 6 dan memastikan aplikasi mudah dikembangkan serta terstruktur dengan rapi.

2. Perbedaan dengan Praktikum Sebelumnya

   Pada praktikum sebelumnya (Week 12), aplikasi Agri-POS hanya menampilkan GUI dasar menggunakan komponen ```TextField```, ```Button```, dan ```ListView```. Pada praktikum Week 13, tampilan daftar produk ditingkatkan menggunakan ```TableView``` sehingga data dapat ditampilkan secara terstruktur dalam bentuk kolom dan baris.

3. Kendala yang dihadapi

   Kendala utama yang dihadapi adalah pengaturan ```TableView``` agar dapat menampilkan data dari database secara dinamis, khususnya dalam penggunaan ```ObservableList```. Kendala lain adalah memastikan koneksi database dan pemanggilan DAO berjalan melalui service tanpa melanggar konsep MVC.
---

## Table Traceability
Implementasi ini telah mengikuti desain Bab 6, di mana:
- View hanya menangani tampilan
- Controller mengatur alur proses
- Service mengelola logika bisnis
- DAO menangani akses database

Alur penghapusan produk mengikuti Sequence Diagram: ```View → Controller → Service → DAO → Database```, kemudian kembali ke View melalui ```loadData()```.

| Artefak Bab 6 | Referensi | Handler GUI | Controller/Service | DAO | Dampak UI/DB |
|---------------|-----------|-------------|-------------------|-----|--------------|
| Use Case: Lihat Daftar Produk | UC-02 | `loadData()` method | `ProductController.getAllProducts()` → `ProductService.findAll()` | `ProductDAO.findAll()` | TableView terisi data dari DB |
| Use Case: Tambah Produk | UC-01 | Button "Tambah Produk" dengan lambda `e -> handleAdd()` | `ProductController.addProduct()` → `ProductService.insert()` | `ProductDAO.insert()` | Row baru di TableView + Insert DB |
| Use Case: Hapus Produk | UC-03 | Button "Hapus Produk" dengan lambda `e -> handleDelete()` | `ProductController.deleteProduct()` → `ProductService.delete()` | `ProductDAO.delete()` | Row hilang dari TableView + Delete DB |
| Activity Diagram: Hapus | AD-02 | Select item → Konfirmasi Alert → Delete | Validasi selection → Confirm → Delete → Reload | DAO DELETE SQL | Alur: Select→Confirm→Service→DAO→DB→Reload |
| Sequence Diagram: Hapus | SD-02 | Event btnDelete | View → Controller → Service → DAO | DAO → DB | Urutan: View→Controller→Service→DAO→DB→DAO→Service→Controller→View(reload) |
| Lambda Expression | Java 8+ | `btnAdd.setOnAction(e -> handleAdd())` | - | - | Event handling ringkas |
| DIP (SOLID) | Principle | View hanya tahu Controller | Controller hanya tahu Service | Service uses DAO interface | Loose coupling |


---

## Kesimpulan
Praktikum Week 13 berhasil mengembangkan aplikasi Agri-POS menjadi lebih interaktif dengan penerapan TableView dan lambda expression. Integrasi penuh antara GUI, service, dan DAO memastikan data yang ditampilkan selalu sinkron dengan database. Dengan tetap menerapkan arsitektur MVC dan prinsip SOLID, aplikasi menjadi lebih terstruktur, mudah dipelihara, dan siap dikembangkan ke tahap selanjutnya.

---
