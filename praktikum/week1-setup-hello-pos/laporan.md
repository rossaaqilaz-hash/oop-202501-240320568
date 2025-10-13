# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
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


## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots/hasil.png)
)
---

## Analisis
(
- Jelaskan bagaimana kode berjalan.  
- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
- Kendala yang dihadapi dan cara mengatasinya.  
)
---

## Kesimpulan
(Tuliskan kesimpulan dari praktikum minggu ini.  
Contoh: *Dengan menggunakan class dan object, program menjadi lebih terstruktur dan mudah dikembangkan.*)

---

## Quiz
(1. [Tuliskan kembali pertanyaan 1 dari panduan]  
   **Jawaban:** …  

2. [Tuliskan kembali pertanyaan 2 dari panduan]  
   **Jawaban:** …  

3. [Tuliskan kembali pertanyaan 3 dari panduan]  
   **Jawaban:** …  )
