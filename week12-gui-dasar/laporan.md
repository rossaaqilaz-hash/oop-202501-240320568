# Laporan Praktikum Minggu 12
Topik: GUI Dasar JavaFX (Event-Driven Programming)

## Identitas
- Nama  : Rossa Aqila Zahra
- NIM   : 240320568
- Kelas : 3DSRA

---

## Tujuan
1. Menjelaskan konsep event-driven programming.
2. Membangun antarmuka grafis sederhana menggunakan JavaFX.
3. Membuat form input data produk.
4. Menampilkan daftar produk pada GUI.
5. Mengintegrasikan GUI dengan modul backend yang telah dibuat (DAO & Service).

---

## Dasar Teori
1. Event-Driven Programming

    Event-driven programming adalah paradigma pemrograman di mana alur program ditentukan oleh event atau aksi pengguna, seperti klik tombol atau input teks. Pada JavaFX, event ditangani menggunakan event handler seperti setOnAction().

2. JavaFX

    JavaFX merupakan framework GUI Java yang bersifat modern dan event-driven. JavaFX digunakan sebagai View dalam arsitektur MVC, sehingga hanya bertugas menampilkan UI dan menangkap event dari pengguna.

3. MVC (Model–View–Controller)

    MVC memisahkan aplikasi menjadi:

    - Model → Representasi data dan logika bisnis (Product)
    - View → Antarmuka pengguna (ProductFormView)
    - Controller → Penghubung View dan Service (ProductController)

    Pemisahan ini membuat aplikasi lebih terstruktur dan mudah dikembangkan.

4. DAO (Data Access Object)

    DAO bertugas mengelola seluruh interaksi dengan database. GUI tidak diperbolehkan mengakses database secara langsung, melainkan melalui Service dan DAO.

---

## Langkah Praktikum
1. Menggunakan kembali class Product dari pertemuan sebelumnya
2. Menggunakan ProductDAO dan ProductService sebagai backend
3. Membuat tampilan JavaFX berupa form input produk
4. Menambahkan event handler pada tombol "Tambah Produk"
5. Menghubungkan GUI dengan ProductController
6. Menampilkan daftar produk pada ListView
7. Commit message: week12-gui-dasar: [fitur] [deskripsi singkat]

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
import com.upb.agripos.view.ProductFormView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/agripos",
                    "postgres",
                    "postgres"
            );

            ProductService service = new ProductService(new ProductDAOImpl(conn));
            ProductFormView view = new ProductFormView();
            new ProductController(service, view);

            stage.setScene(new Scene(view, 400, 450));
            stage.setTitle("GUI Produk Agri-POS");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("GAGAL MENJALANKAN APLIKASI");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```
)
---

## Hasil Eksekusi
![Output_GUI_Dasar](screenshots/Output_GUI_Dasar.png)
---

## Analisis
1. Cara Kerja Program

    Aplikasi JavaFX ini mengimplementasikan konsep event-driven programming di mana tombol Tambah Produk bertindak sebagai pemicu event. Ketika tombol ditekan, View mengirimkan event ke Controller, lalu Controller memanggil Service untuk menyimpan data melalui DAO ke database PostgreSQL.

2. Penerapan MVC dan DAO

    View tidak berinteraksi langsung dengan database, sehingga prinsip Dependency Inversion Principle (DIP) terpenuhi. Seluruh alur data mengikuti urutan: View → Controller → Service → DAO → Database.

3. Keterkaitan dengan Bab 6

    Alur tombol Tambah Produk telah sesuai dengan Use Case, Activity Diagram, dan Sequence Diagram pada Bab 6, sehingga terdapat traceability yang jelas antara desain dan implementasi.

4. Kendala yang Dihadapi

    Kendala utama adalah error koneksi database akibat kesalahan konfigurasi username dan password PostgreSQL. Masalah ini diatasi dengan menyesuaikan kredensial database dan memastikan service PostgreSQL berjalan.

---

## Tabel Traceability
| Artefak Bab 6 | Referensi | Handler GUI | Controller/Service | DAO | Dampak UI/DB |
|---------------|-----------|-------------|-------------------|-----|--------------|
| Use Case: Tambah Produk | UC-01 | Button "Tambah Produk" | `ProductController.addProduct()` → `ProductService.insert()` | `ProductDAO.insert()` | ListView bertambah + Insert ke DB |
| Activity Diagram: Tambah Produk | AD-01 | `handleAdd()` | Validasi input → controller → service | DAO INSERT | Form → Validasi → DB → Refresh |
| Sequence Diagram: Tambah Produk | SD-01 | Event btnAdd | View → Controller → Service → DAO | DAO → DB | Urutan panggilan sesuai SD |
| DIP (SOLID) | Principle | View tidak akses DAO | Dependency injection via constructor | Interface DAO | Loose coupling |


---

## Kesimpulan
Praktikum Week 12 berhasil mengimplementasikan GUI JavaFX yang terintegrasi dengan backend menggunakan pola MVC dan DAO. Pemisahan tanggung jawab antar layer membuat aplikasi lebih terstruktur, mudah dipelihara, dan sesuai dengan prinsip OOP. Aplikasi mampu menerima input produk, menyimpan data ke database, dan menampilkan hasilnya pada GUI.

---