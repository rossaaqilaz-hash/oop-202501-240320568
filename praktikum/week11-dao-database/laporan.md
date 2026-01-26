# Laporan Praktikum Minggu 11
Topik: Data Access Object (DAO) dan CRUD Database dengan JDBC

## Identitas
- Nama  : Rossa Aqila Zahra
- NIM   : 240320568
- Kelas : 3DSRA

---

## Tujuan
1. Menjelaskan konsep Data Access Object (DAO) dalam pengembangan aplikasi OOP.
2. Menghubungkan aplikasi Java dengan basis data menggunakan JDBC.
3. Mengimplementasikan operasi CRUD (Create, Read, Update, Delete) secara lengkap.
4. Mengintegrasikan DAO dengan class aplikasi OOP sesuai prinsip desain yang baik.

---

## Dasar Teori
### 1. Konsep Data Access Object (DAO)

DAO adalah pola desain yang memisahkan logika akses data dari logika bisnis aplikasi. Dengan DAO, perubahan teknologi basis data tidak memengaruhi logika utama aplikasi.

Manfaat DAO:
- Kode lebih terstruktur dan mudah dipelihara
- Mengurangi tight coupling antara aplikasi dan database
- Mendukung pengujian dan pengembangan lanjutan

---

### 2. JDBC dan Koneksi Database

JDBC (Java Database Connectivity) digunakan untuk menghubungkan aplikasi Java dengan basis data relasional, dalam praktikum ini menggunakan PostgreSQL.

Komponen utama JDBC:
- DriverManager
- Connection
- PreparedStatement
- ResultSet
---

## Langkah Praktikum
1. Membuat database PostgreSQL dan tabel products
2. Membuat class model Product
3. Membuat interface ProductDAO
4. Mengimplementasikan DAO menggunakan JDBC
5. Mengintegrasikan DAO ke dalam program utama
6. Menguji seluruh operasi CRUD
7. Commit message: ```week11-dao-database: [fitur] [deskripsi singkat]```

---

## Kode Program
MainDAOTest.java  

```java
package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class MainDAOTest {
    public static void main(String[] args) {
        try {
            System.out.println("====================================");
            System.out.println("MENGHUBUNGKAN KE DATABASE...");
            System.out.println("====================================");

            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos",
                "postgres",
                "postgres"
            );

            System.out.println("KONEKSI DATABASE BERHASIL");
            System.out.println("Database : agripos");
            System.out.println("====================================\n");

            ProductDAO dao = new ProductDAOImpl(conn);

            // INSERT
            System.out.println("[INSERT]");
            dao.insert(new Product("P01", "Pupuk Organik", 25000, 10));
            System.out.println("Produk berhasil ditambahkan\n");

            // UPDATE
            System.out.println("[UPDATE]");
            dao.update(new Product("P01", "Pupuk Organik Premium", 30000, 8));
            System.out.println("Produk berhasil diperbarui\n");

            // READ
            System.out.println("[READ]");
            Product p = dao.findByCode("P01");
            if (p != null) {
                System.out.println("Kode  : " + p.getCode());
                System.out.println("Nama  : " + p.getName());
                System.out.println("Harga : " + p.getPrice());
                System.out.println("Stok  : " + p.getStock());
            }

            // DELETE
            System.out.println("\n[DELETE]");
            dao.delete("P01");
            System.out.println("Produk berhasil dihapus\n");

            conn.close();

            System.out.println("====================================");
            System.out.println("PROGRAM SELESAI");
            System.out.println("====================================");

        } catch (Exception e) {
            System.out.println("KONEKSI DATABASE GAGAL");
            e.printStackTrace();
        }
    }
}
```
---

## Hasil Eksekusi 
![Screenshot hasil](screenshots/Output_MainDAOTest.png)
![Screenshot hasil](screenshots/Test%20Build.png)

---

## Analisis
1. Cara Kerja Kode Program

   Program ini mengimplementasikan pola Data Access Object (DAO) untuk mengelola data produk pada database PostgreSQL. Class Product berperan sebagai model yang merepresentasikan tabel products. Interface ProductDAO mendefinisikan operasi CRUD, sedangkan class ProductDAOImpl mengimplementasikan operasi tersebut menggunakan JDBC dan PreparedStatement.

   Class MainDAOTest bertugas sebagai penguji integrasi antara aplikasi Java dan database. Program melakukan koneksi database menggunakan DriverManager, kemudian menjalankan operasi insert, update, read, dan delete secara berurutan.

   Penggunaan PreparedStatement membuat query lebih aman dari SQL Injection serta memudahkan pengaturan parameter.

2. Perbedaan dengan Praktikum Sebelumnya

   Pada praktikum sebelumnya, fokus utama adalah penerapan Design Pattern (Singleton) dan Unit Testing. Sedangkan pada praktikum ini, fokus berpindah ke integrasi aplikasi Java dengan database menggunakan JDBC dan penerapan DAO untuk memisahkan logika bisnis dan akses data. Praktikum ini memperkenalkan interaksi langsung dengan database nyata serta pengelolaan data.

3. Kendala yang dihadapi  

   Kendala utama yang dihadapi adalah kegagalan koneksi database akibat kesalahan autentikasi PostgreSQL. Masalah ini terjadi karena password atau konfigurasi database tidak sesuai. Solusi yang dilakukan adalah memastikan username, password, nama database, serta status service PostgreSQL sudah benar dan aktif.

---

## Kesimpulan
Praktikum ini berhasil mengimplementasikan pola Data Access Object (DAO) dan operasi CRUD menggunakan JDBC pada aplikasi Java. Dengan penerapan DAO, struktur program menjadi lebih rapi, modular, dan mudah dikembangkan.

Integrasi JDBC memungkinkan aplikasi Java berinteraksi langsung dengan database PostgreSQL secara aman dan efisien. Praktikum ini memberikan pemahaman penting mengenai pengelolaan data persisten yang sangat dibutuhkan dalam pengembangan aplikasi skala besar.

---