# Laporan Praktikum Minggu 6
Topik: [Desain Arsitektur Sistem dengan UML dan Prinsip SOLID"]

## Identitas
- Nama  : [Rossa Aqila Zahra]
- NIM   : [240320568]
- Kelas : [3DSRA]

---

## Tujuan
1. Mahasiswa mampu mengidentifikasi kebutuhan sistem ke dalam diagram UML.
2. Mahasiswa mampu menggambar UML Class Diagram dengan relasi antar class yang tepat.
3. Mahasiswa mampu menjelaskan prinsip desain OOP (SOLID).
4. Mahasiswa mampu menerapkan minimal dua prinsip SOLID dalam kode program.

## Langkah Praktikum

1. Pemetaan kebutuhan → daftar aktor & use case; gambar Use Case Diagram versi-1.
2. Activity Diagram proses “Checkout” (lengkap dengan swimlane Kasir/Sistem/Payment Gateway) + skenario normal dan gagal.
3. Sequence Diagram proses pembayaran (variasi: tunai vs e-wallet, alt saldo tidak cukup).
4. Class Diagram (atribut/tipe, method, visibility, multiplicity) + mapping prinsip SOLID; revisi konsistensi lintas diagram.
Setiap iterasi lakukan commit incremental dengan pesan: week6-uml-solid: iterasi-N <deskripsi>.

---

## Deskripsi Singkat Sistem Agri-POS
Agri-POS merupakan sistem kasir berbasis komputer yang dirancang untuk mendukung proses transaksi penjualan pada usaha pertanian. Sistem ini membantu kasir dalam mengelola penjualan produk pertanian, mulai dari pencatatan produk, pengecekan ketersediaan stok, perhitungan total transaksi, hingga proses pembayaran secara tunai maupun non-tunai (e-wallet). Selain itu, Agri-POS mampu memperbarui stok produk secara otomatis setelah transaksi berhasil dan menghasilkan struk pembayaran sebagai bukti transaksi. Dengan adanya sistem ini, proses penjualan menjadi lebih cepat, akurat, dan terintegrasi, sehingga dapat meningkatkan efisiensi operasional dan meminimalkan kesalahan pencatatan pada usaha pertanian.
```

---

## Penjelasan setiap diagram
1. Use Case Diagram
![Uploading uml Agri-POS-use case.png…]


Fungsi: Use Case Diagram digunakan untuk menggambarkan fungsi-fungsi utama sistem serta interaksi antara aktor dengan sistem Agri-POS. Diagram ini menunjukkan apa saja yang dapat dilakukan oleh pengguna tanpa menjelaskan bagaimana proses tersebut dijalankan secara detail.Pada sistem Agri-POS, aktor utama terdiri dari Kasir dan Admin. Kasir berinteraksi dengan sistem untuk melakukan proses penjualan, sedangkan Admin berfokus pada pengelolaan data dan laporan.

Use case utama yang ditampilkan antara lain:

Login
Kelola produk
Checkout
Pembayaran tunai
Pembayaran e-wallet
Cetak struk
Lihat laporan
Keterkaitan dengan Diagram Lain

Use Case Diagram menjadi dasar perancangan diagram lainnya. Setiap use case kemudian:
Diuraikan alur aktivitasnya pada Activity Diagram
Dimodelkan interaksi objeknya pada Sequence Diagram
Direpresentasikan struktur kelas dan relasinya pada Class Diagram

2. Activity Diagram


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
