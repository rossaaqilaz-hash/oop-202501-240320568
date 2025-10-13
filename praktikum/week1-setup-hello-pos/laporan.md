# Laporan Praktikum Minggu 1 
Topik: [Pengenalan Paradigma dan Setup Proyek]

## Identitas
- Nama  : [Rossa Aqila Zahra]
- NIM   : [240320568]
- Kelas : [3DSRA]


## Tujuan
- Mahasiswa mampu mendefinisikan paradigma prosedural, OOP, dan fungsional.
- Mahasiswa mampu membandingkan kelebihan dan keterbatasan tiap paradigma.
- Mahasiswa mampu memberikan contoh program sederhana untuk masing-masing paradigma.
- Mahasiswa aktif dalam diskusi kelas (bertanya, menjawab, memberi opini).
  
## Dasar Teori
Paradigma pemrograman adalah cara pandang dalam menyusun program:  
- Prosedural: program dibangun sebagai rangkaian perintah (fungsi/prosedur).  
- OOP (Object-Oriented Programming): program dibangun dari objek yang memiliki data (atribut) dan perilaku (method).  
- Fungsional : program dipandang sebagai pemetaan fungsi matematika, lebih menekankan ekspresi dan transformasi data.  

Dalam konteks Agri-POS, OOP membantu memodelkan entitas nyata seperti Produk, Transaksi, dan Pembayaran sebagai objek. Dengan demikian, sistem lebih mudah dikembangkan dan dipelihara.  


## Langkah Praktikum
1. **Setup Project**
   - Pastikan sudah menginstall **JDK** (Java Development Kit), **IDE** (misal: IntelliJ IDEA, VS Code, NetBeans), **Git**, **PostgreSQL**, dan **JavaFX** di komputer.
   - Buat folder project `oop-pos-<nim>`.
   - Inisialisasi repositori Git.
   - Buat struktur awal `src/main/java/com/upb/agripos/`.
   - Pastikan semua tools dapat berjalan (uji dengan membuat dan menjalankan program Java sederhana).

2. **Program Sederhana dalam 3 Paradigma**
   - Prosedural: program untuk menghitung total harga dua produk.
   - OOP: class `Produk` dengan atribut nama dan harga, buat minimal tiga objek, lalu hitung total.  
   - Fungsional: gunakan `Stream` atau lambda untuk menghitung total harga dari minimal tiga objek.  

3. **Commit dan Push**
   - Commit dengan pesan: `week1-setup-hello-pos`.  


## Kode Program
1. Procedural
// HelloProcedural.java
public class HelloProcedural {
   public static void main(String[] args) {
      String nim = "240320568";
      String nama = "Rossa Aqila";
      String[] produk = {"Beras", "Pupuk", "Benih"};
      int[] harga = {20000, 25000, 32000};
      int total = 0;
      System.out.println("Hello POS World");
      System.out.println("NIM: " + nim + ", Nama: " + nama);
      System.out.println("Daftar Produk:");
      for (int i = 0; i < produk.length; i++) {
         System.out.println("- " + produk[i] + ": " + harga[i]);
         total += harga[i];
      }
      System.out.println("Total harga semua produk: " + total);
   }
}

2. OOP
// HelloOOP.java
class Produk {
   String nama;
   int harga;
   Produk(String nama, int harga) {
      this.nama = nama;
      this.harga = harga;
   }
} public class HelloOOP {
   public static void main(String[] args) {
      String nim = "240320568";
      String namaMhs = "Rossa Aqila";
      Produk[] daftar = {
         new Produk("Beras", 20000),
         new Produk("Pupuk", 25000),
         new Produk("Benih", 32000)
      };
      int total = 0;
      System.out.println("Hello POS World");
      System.out.println("NIM: " + nim + ", Nama: " + namaMhs);
      System.out.println("Daftar Produk:");
      for (Produk p : daftar) {
         System.out.println("- " + p.nama + ": " + p.harga);
         total += p.harga;
      }
      System.out.println("Total harga semua produk: " + total);
   }
}

4. Functional
// HelloFunctional.java
import java.util.*;
import java.util.stream.*;
public class HelloFunctional {
   public static void main(String[] args) {
      String nim = "240320568";
      String nama = "Rossa Aqila";
      List<String> produk = Arrays.asList("Beras", "Pupuk", "Benih");
      List<Integer> harga = Arrays.asList(20000, 25000, 32000);
      System.out.println("Hello POS World");
      System.out.println("NIM: " + nim + ", Nama: " + nama);
      System.out.println("Daftar Produk:");
      IntStream.range(0, produk.size())
         .forEach(i -> System.out.println("- " + produk.get(i) + ": " + harga.get(i)));
      int total = harga.stream().mapToInt(Integer::intValue).sum();
      System.out.println("Total harga semua produk: " + total);
   }
}
## Hasil Eksekusi
[(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots/hasil.png)
)](https://github.com/rossaaqilaz-hash/oop-202501-240320568/tree/f0f967cb378c0fa62e0f947c8b6b107a61a7a444/praktikum/week1-setup-hello-pos/screenshots)
---

## Analisis

1. Jelaskan bagaimana kode berjalan

Ketiga program memiliki tujuan yang sama, yaitu menampilkan pesan “Hello POS World”, data nama dan NIM, serta daftar produk dengan harga dan totalnya.
- **Versi prosedural** menjalankan semua logika langsung di dalam metode main(). Data disimpan dalam array, lalu diolah menggunakan perulangan for.
- **Versi OOP (Object-Oriented Programming)** menggunakan class Produk untuk merepresentasikan data produk. Setiap objek Produk memiliki atribut nama dan harga. Pendekatan ini membuat struktur kode lebih rapi, mudah dikembangkan, dan sesuai dengan konsep pemrograman berbasis objek.
- **Versi fungsional** menggunakan List dan Stream dari Java untuk memproses data secara deklaratif. Data produk dan harga diolah dengan fungsi forEach() dan mapToInt(), tanpa perulangan eksplisit.
Semua versi menghasilkan keluaran yang sama, namun cara pendekatannya berbeda sesuai paradigma. 

2. Perbedaan pendekatan minggu ini dibanding minggu sebelumnya

Minggu sebelumnya (jika masih tahap pengenalan), fokus pada struktur dasar Java seperti main(), variabel, dan output sederhana.
Minggu ini mulai membandingkan tiga paradigma pemrograman:
- **Prosedural :** Fokus pada langkah-langkah eksekusi.
- **OOP :** Fokus pada representasi objek dan relasi antarobjek.
- **Fungsional :** Fokus pada transformasi data menggunakan fungsi tanpa mengubah state.
Pendekatan minggu ini lebih menekankan perbedaan cara berpikir dalam menulis program, bukan hanya hasil akhir.

3. Kendala yang dihadapi dan cara mengatasinya
Beberapa kendala umum:
- Kesalahan sintaks, terutama saat membuat class baru dan mengakses atribut.
Solusi: Memastikan penulisan huruf besar/kecil sesuai, dan setiap class berada dalam file yang benar.
- Import library seperti java.util.stream.* pada versi fungsional kadang terlupa.
Solusi: Menambahkan import di bagian atas file sebelum menjalankan.
- Perbedaan cara mengeksekusi file Java di terminal VSCode atau Git.
Solusi: Memastikan berada di direktori yang sesuai, lalu gunakan perintah javac dan java dengan nama file yang tepat.

## Kesimpulan
Setiap paradigma memiliki cara berbeda dalam menyelesaikan masalah. Pendekatan OOP membuat program lebih terstruktur dan mudah dikembangkan, sedangkan pendekatan fungsional membuat kode lebih ringkas dan deklaratif. Melalui praktikum ini, mahasiswa memahami bahwa pemilihan paradigma sangat bergantung pada konteks dan kebutuhan program.
---

## Quiz
1. [Apakah OOP selalu lebih baik dari prosedural?]  
   **Jawaban:** Tidak selalu. OOP lebih baik digunakan ketika program memiliki banyak entitas yang saling berinteraksi dan membutuhkan struktur yang terorganisir. Namun, untuk program sederhana atau skrip kecil, paradigma prosedural sering kali lebih efisien dan mudah diterapkan. 

2. [Kapan functional programming lebih cocok digunakan dibanding OOP atau prosedural?]  
   **Jawaban:** Functional programming lebih cocok digunakan saat kita perlu mengolah data dalam jumlah besar, melakukan transformasi data yang kompleks, atau saat membutuhkan kode yang ringkas dan mudah diuji. Contohnya pada operasi koleksi data seperti filtering, mapping, dan aggregasi.

3. [Bagaimana paradigma (prosedural, OOP, fungsional) memengaruhi maintainability dan scalability aplikasi?]  
   **Jawaban:**
- Prosedural: Mudah dibuat tetapi sulit dikelola saat program menjadi besar karena semua logika bercampur.
- OOP: Meningkatkan maintainability dan scalability karena kode dibagi dalam class dan object yang terstruktur dengan baik.
- Fungsional: Membuat kode lebih singkat dan bebas efek samping, sehingga lebih mudah diuji dan di-maintain dalam sistem berskala besar.
   
4. [Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?]
   **Jawaban:** Karena aplikasi POS (Point of Sale) memiliki banyak objek nyata seperti Produk, Transaksi, Kasir, dan Pelanggan. Dengan OOP, setiap entitas bisa direpresentasikan sebagai class dengan atribut dan perilakunya sendiri, membuat program lebih modular, mudah dikembangkan, dan dapat digunakan kembali.
   
5. [Bagaimana paradigma fungsional dapat membantu mengurangi kode berulang (*boilerplate code*)?]
   **Jawaban:** Paradigma fungsional menggunakan konsep fungsi murni, lambda expression, dan operasi koleksi seperti map(), filter(), dan reduce(), sehingga tidak perlu menulis perulangan dan logika manual berulang kali. Ini menjadikan kode lebih singkat, jelas, dan bebas duplikasi.
