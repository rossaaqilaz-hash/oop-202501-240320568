# Laporan Praktikum Minggu 10
Topik: Design Pattern (Singleton, MVC) dan Unit Testing menggunakan JUnit 

## Identitas
- Nama  : Rossa Aqila Zahra
- NIM   : 240320568
- Kelas : 3DSRA

---

## Tujuan
1. Menjelaskan konsep dasar design pattern dalam rekayasa perangkat lunak.
2. Mengimplementasikan Singleton Pattern dengan benar.
3. Menjelaskan dan menerapkan Model–View–Controller (MVC) pada aplikasi sederhana.
4. Membuat dan menjalankan unit test menggunakan JUnit.
5. Menganalisis manfaat penerapan design pattern dan unit testing terhadap kualitas perangkat lunak.

---

## Dasar Teori
### 1. Design Pattern

Design pattern adalah solusi desain yang telah teruji untuk menyelesaikan masalah umum dalam pengembangan perangkat lunak. Fokus minggu ini:
- Singleton Pattern
- MVC (Model–View–Controller)

### 2. Singleton Pattern

Tujuan: Menjamin suatu class hanya memiliki satu instance dan menyediakan titik akses global.

Karakteristik:
- Constructor `private`
- Atribut `static instance`
- Method `static getInstance()`

Contoh Implementasi:
```java
package com.upb.agripos.config;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
```

Penerapan pada Agri-POS: koneksi database atau service global yang tidak boleh lebih dari satu instance.

### 3. MVC (Model–View–Controller)

Memisahkan tanggung jawab aplikasi:

| Komponen | Tanggung Jawab |
|---------|------------------|
| Model   | Data dan logika bisnis |
| View    | Tampilan/output |
| Controller | Penghubung Model dan View |

Contoh Struktur MVC Sederhana:
- Model → `Product`
- View → `ConsoleView`
- Controller → `ProductController`

---

## Langkah Praktikum
1. Membuat class Singleton untuk Database Connection
2. Membuat struktur MVC sederhana untuk fitur Product
3. Mengintegrasikan Model, View, dan Controller pada main program
4. Membuat unit test menggunakan JUnit
5. Menjalankan pengujian menggunakan Maven
6. Commit message: ```
week10-pattern-testing: [fitur] [deskripsi singkat]```

---

## Kode Program
1. AppMVC.java  

```java
package com.upb.agripos;

import com.upb.agripos.config.DatabaseConnection;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class AppMVC {
    public static void main(String[] args) {
        System.out.println("Hello, Difa Rizkiana Fauziyah - 240320564 (Week-10 Pattern Testing)");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.connect();


        Product product = new Product("P01", "Pupuk Organik");
        ConsoleView view = new ConsoleView();
        ProductController controller = new ProductController(product, view);
        controller.showProduct();
    }
}
```

2. ProductTest.java
```java
package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.upb.agripos.model.Product;

public class ProductTest {

    @Test
    void testProductName() {
        Product p = new Product("P01", "Benih Jagung");
        assertEquals("Benih Jagung", p.getName());
    }
}
```
---

## Hasil Eksekusi
![Screenshot hasil]()
![Screenshot hasil]()
---

## Analisis
1. Cara Kerja Kode Program

   Program ini menerapkan dua design pattern utama, yaitu Singleton Pattern dan Model–View–Controller (MVC), serta dilengkapi dengan unit testing menggunakan JUnit. Singleton Pattern diimplementasikan pada class DatabaseConnection untuk memastikan hanya satu instance koneksi yang digunakan selama aplikasi berjalan, sehingga menghindari konflik atau pemborosan resource.

   Arsitektur MVC diterapkan pada fitur Product dengan memisahkan data produk ke dalam class Product sebagai Model, tampilan output ke dalam class ConsoleView sebagai View, serta penghubung antara keduanya pada class ProductController. Pemisahan ini membuat kode lebih terstruktur dan mudah dikembangkan di masa depan.

   Unit testing dilakukan pada class Product untuk memastikan method getName() mengembalikan nilai yang sesuai. Pengujian dijalankan menggunakan Maven dan JUnit 5, sehingga proses pengujian dapat dilakukan secara otomatis.

2. Perbedaan dengan Praktikum Sebelumnya

   ada praktikum Week 9, fokus utama adalah exception handling dan custom exception untuk validasi proses bisnis. Sedangkan pada praktikum Week 10, fokus beralih pada penerapan design pattern dan pengujian otomatis. Praktikum ini menekankan pentingnya struktur kode yang baik serta pengujian unit untuk menjaga kualitas perangkat lunak.

3. Kendala yang dihadapi

   Kendala utama yang dihadapi adalah konfigurasi Maven dan dependency JUnit yang harus sesuai agar unit test dapat dijalankan tanpa error. Kendala tersebut diatasi dengan memastikan struktur folder, deklarasi package, serta dependency JUnit dan plugin Maven Surefire telah dikonfigurasi dengan benar di dalam file pom.xml.
---

## Kesimpulan
Berdasarkan praktikum Week 10 yang telah dilakukan, dapat disimpulkan bahwa penerapan design pattern seperti Singleton dan MVC dapat meningkatkan struktur dan keterbacaan kode. Selain itu, penggunaan unit testing dengan JUnit membantu memastikan fungsi berjalan sesuai dengan yang diharapkan serta mempermudah proses debugging. Kombinasi design pattern dan unit testing terbukti meningkatkan kualitas dan maintainability perangkat lunak.

---

## Quiz
1. Mengapa constructor pada Singleton harus bersifat private?  
   **Jawaban:** Constructor pada Singleton harus bersifat private agar pembuatan objek dari class tersebut tidak dapat dilakukan secara langsung dari luar class menggunakan keyword new. Dengan membatasi akses constructor, pengembang dapat mengontrol proses instansiasi objek sehingga hanya satu instance yang dapat dibuat melalui method khusus seperti getInstance(). Hal ini memastikan prinsip utama Singleton tetap terjaga, yaitu hanya terdapat satu instance yang digunakan secara global selama aplikasi berjalan. 

2. Jelaskan manfaat pemisahan Model, View, dan Controller!  
   **Jawaban:** Pemisahan Model, View, dan Controller memberikan struktur yang jelas terhadap tanggung jawab masing-masing komponen dalam aplikasi. Model berfokus pada data dan logika bisnis, View bertanggung jawab terhadap tampilan atau output, sedangkan Controller menjadi penghubung antara Model dan View. Dengan pemisahan ini, perubahan pada tampilan tidak akan memengaruhi logika bisnis, dan sebaliknya. Hal ini membuat kode lebih mudah dipelihara, dikembangkan, serta meningkatkan keterbacaan dan fleksibilitas aplikasi.  

3. Apa peran unit testing dalam menjaga kualitas perangkat lunak? 
   **Jawaban:** Unit testing berperan sebagai mekanisme pengujian otomatis untuk memastikan setiap unit atau fungsi dalam program bekerja sesuai dengan yang diharapkan. Dengan adanya unit testing, kesalahan dapat dideteksi lebih awal sebelum aplikasi digunakan secara luas. Selain itu, unit testing meningkatkan kepercayaan terhadap kualitas kode, mempermudah proses debugging, serta membantu menjaga stabilitas aplikasi ketika dilakukan perubahan atau pengembangan fitur baru.

4. Apa risiko jika Singleton tidak diimplementasikan dengan benar?
   **Jawaban:** Jika Singleton tidak diimplementasikan dengan benar, maka dapat terjadi pembuatan lebih dari satu instance dari class yang seharusnya bersifat tunggal. Hal ini dapat menyebabkan konflik data, inkonsistensi state, serta pemborosan resource, terutama jika Singleton digunakan untuk pengelolaan koneksi database atau konfigurasi global. Selain itu, implementasi Singleton yang tidak tepat juga dapat menimbulkan masalah pada concurrency dan membuat aplikasi sulit untuk diuji dan dipelihara.