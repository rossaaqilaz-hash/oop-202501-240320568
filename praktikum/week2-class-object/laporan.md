# Laporan Praktikum Minggu 2 
Topik: [Class dan Object (Produk Pertanian)]

## Identitas
- Nama  : [Rossa Aqila Zahra]
- NIM   : [240320568]
- Kelas : [3DSRA]

---

## Tujuan
- Mahasiswa mampu **menjelaskan konsep class, object, atribut, dan method** dalam OOP.  
- Mahasiswa mampu **menerapkan access modifier dan enkapsulasi** dalam pembuatan class.  
- Mahasiswa mampu **mengimplementasikan class Produk pertanian** dengan atribut dan method yang sesuai.  
- Mahasiswa mampu **mendemonstrasikan instansiasi object** serta menampilkan data produk pertanian di console.  
- Mahasiswa mampu **menyusun laporan praktikum** dengan bukti kode, hasil eksekusi, dan analisis sederhana.  

---

## Dasar Teori
Class adalah blueprint atau cetak biru dari sebuah objek. Objek merupakan instansiasi dari class yang berisi atribut (data) dan method (perilaku). Dalam OOP, enkapsulasi dilakukan dengan menyembunyikan data menggunakan access modifier (public, private, protected) serta menyediakan akses melalui getter dan setter.  

Dalam konteks Agri-POS, produk pertanian seperti benih, pupuk, dan alat pertanian dapat direpresentasikan sebagai objek yang memiliki atribut nama, harga, dan stok. Dengan menggunakan class, setiap produk dapat dibuat, dikelola, dan dimanipulasi secara lebih terstruktur.  


---

## Langkah Praktikum
1. **Membuat Class Produk**
   - Buat file `Produk.java` pada package `model`.
   - Tambahkan atribut: `kode`, `nama`, `harga`, dan `stok`.
   - Gunakan enkapsulasi dengan menjadikan atribut bersifat private dan membuat getter serta setter untuk masing-masing atribut.  

2. **Membuat Class CreditBy**
   - Buat file `CreditBy.java` pada package `util`.
   - Isi class dengan method statis untuk menampilkan identitas mahasiswa di akhir output: `credit by: <NIM> - <Nama>`.

3. **Membuat Objek Produk dan Menampilkan Credit**
   - Buat file `MainProduk.java`.
   - Instansiasi minimal tiga objek produk, misalnya "Benih Padi", "Pupuk Urea", dan satu produk alat pertanian.
   - Tampilkan informasi produk melalui method getter.  
   - Panggil `CreditBy.print("<NIM>", "<Nama>")` di akhir `main` untuk menampilkan identitas.

4. **Commit dan Push**
   - Commit dengan pesan: `week2-class-object`.  

---

## Kode Program
1. Produk.java
   ``` java
   package model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
        } else {
            System.out.println("Stok tidak mencukupi!");
        }
    }public static void main(String[] args) {
    Produk p1 = new Produk("PPK", "Pupuk konsentrat", 320000, 100);
    System.out.println("Kode: " + p1.kode);
    System.out.println("Nama: " + p1.nama);
    System.out.println("Harga: " + p1.harga);
    System.out.println("Stok: " + p1.stok);
}

}
```

2. CreditBy.java
  ```java
package util;

public class CreditBy {
    public static void print(String nama, String nim) {
        System.out.println("\ncredit by: " + nama + " - " + nim);
    }
}
```

3. MainProduk.java
``` java
// mainproduk.java

import model.Produk;
import util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
        Produk p1 = new Produk("BNH-001", "Benih Tomat IR64", 35000, 100);
        Produk p2 = new Produk("PPU-101", "Pupuk urea 50kg", 420000, 45);
        Produk p3 = new Produk("PPK-501", "Pupuk Konsentrat 50kg", 450000, 20);

        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());

        p1.kurangiStok(25);
        p2.kurangiStok(5);
        p3.tambahStok(10);

        System.out.println("\n=== Setelah Update Stok ===");
        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());


        // Tampilkan identitas mahasiswa nama dan nim
        CreditBy.print("Rossa Aqila Zahra", "240320568");
        
    }
}
```

---

## Hasil Eksekusi
(https://github.com/rossaaqilaz-hash/oop-202501-240320568/blob/bd56748be467370ab9d437093bc0dbf6f7898125/praktikum/week2-class-object/screenshots/Screenshot-MainProduk.png)
---

## Analisis
(
- Jelaskan bagaimana kode berjalan.  
- Pada praktikum minggu ini, pendekatan yang digunakan berbeda dibandingkan minggu sebelumnya. Jika pada minggu sebelumnya program masih menggunakan cara prosedural, di mana seluruh logika dan data dikelola dalam satu file utama tanpa pembagian tanggung jawab yang jelas, maka pada minggu ini sudah diterapkan pendekatan berorientasi objek (Object-Oriented Programming / OOP). Dengan pendekatan ini, program dibagi menjadi beberapa class sesuai dengan fungsi dan perannya masing-masing, seperti Produk untuk representasi data produk, CreditBy untuk menampilkan identitas, serta MainProduk sebagai pengendali utama program. Pendekatan OOP membuat program menjadi lebih terstruktur, modular, dan mudah dikembangkan, karena setiap bagian dapat diperbarui atau digunakan ulang tanpa harus mengubah keseluruhan kode. Hal ini juga meningkatkan pemahaman konsep dasar OOP seperti enkapsulasi, objek, dan method.
  
- Selama praktikum, terdapat beberapa kendala yang muncul, terutama terkait pengaturan struktur folder dan penggunaan package. Salah satu kendala yang sering terjadi adalah error pada saat kompilasi atau import class karena letak file tidak sesuai dengan deklarasi package di bagian atas kode. Selain itu, kesalahan dalam memahami hubungan antar class juga dapat menyebabkan kebingungan dalam proses pemanggilan method. Untuk mengatasinya, penulis memastikan bahwa setiap file Java ditempatkan pada direktori yang sesuai dengan nama package-nya, misalnya com/upb/agripos/model untuk kelas Produk. Selain itu, memahami konsep dasar relasi antar class dan cara kerja import juga membantu memperlancar proses debugging. Dengan latihan dan pemahaman struktur OOP yang lebih baik, kendala tersebut akhirnya dapat diatasi.
)
---

## Kesimpulan
- Lebih terstruktur karena setiap komponen memiliki tanggung jawabnya sendiri.
- Lebih mudah dikembangkan, misalnya jika ingin menambahkan fitur transaksi atau stok otomatis.
- Lebih modular, karena setiap class dapat digunakan ulang di proyek lain (contoh: class Produk dapat dipakai di aplikasi POS, inventori, atau e-commerce).

---

## Quiz
1. [Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?]  
   **Jawaban:** Agar data tidak bisa diubah secara langsung dari luar class, melainkan hanya melalui method khusus (getter/setter). Ini menjaga keamanan data (enkapsulasi) dan mencegah perubahan yang tidak diinginkan.

2. [pa fungsi getter dan setter dalam enkapsulasi?]  
   **Jawaban:**
   - getter digunakan untuk mengambil nilai atribut private.
   - Setter digunakan untuk mengubah nilai atribut private dengan pengawasan (validasi jika perlu).
Dengan getter dan setter, akses terhadap data menjadi terkendali dan aman.

3. [Bagaimana cara class Produk mendukung pengembangan aplikasi POS yang lebih kompleks?]  
   **Jawaban:** Kelas Produk menjadi komponen dasar (model) dalam sistem POS. Dengan atribut dan method yang sudah ada, kita dapat menambahkan fitur seperti:
- Transaksi penjualan dan pembelian.
- Manajemen stok otomatis.
- Integrasi dengan database untuk menyimpan data produk.
- Pembuatan laporan penjualan.
Sehingga Produk bisa menjadi pondasi dari sistem POS yang terorganisir dan scalable.
  
