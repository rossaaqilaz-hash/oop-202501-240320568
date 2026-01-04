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


---
## Penjelasan Setiap Diagram

**1. Use Case Diagram**

[praktikum/week6-uml-solid/screenshots/uml Agri-POS-use case.png](https://github.com/rossaaqilaz-hash/oop-202501-240320568/blob/72b7abda58b22085ac44ea34b7b8a1eb6865fe1a/praktikum/week6-uml-solid/screenshots/uml%20Agri-POS-use%20case.png)


Fungsi: Use Case Diagram digunakan untuk menggambarkan fungsi-fungsi utama sistem serta interaksi antara aktor dengan sistem Agri-POS. Diagram ini menunjukkan apa saja yang dapat dilakukan oleh pengguna tanpa menjelaskan bagaimana proses tersebut dijalankan secara detail.Pada sistem Agri-POS, aktor utama terdiri dari Kasir dan Admin. Kasir berinteraksi dengan sistem untuk melakukan proses penjualan, sedangkan Admin berfokus pada pengelolaan data dan laporan.

Use case utama yang ditampilkan antara lain:
- Login
- Kelola produk
- Checkout
- Pembayaran tunai
- Pembayaran e-wallet
- Cetak struk
- Lihat laporan
- Keterkaitan dengan Diagram Lain

Use Case Diagram menjadi dasar perancangan diagram lainnya. Setiap use case kemudian:
- Diuraikan alur aktivitasnya pada Activity Diagram
- Dimodelkan interaksi objeknya pada Sequence Diagram
- Direpresentasikan struktur kelas dan relasinya pada Class Diagram
---

**2. Activity Diagram**

https://github.com/rossaaqilaz-hash/oop-202501-240320568/blob/c6e1475601caeb058d52eb9fc545ddf9123146b6/praktikum/week6-uml-solid/screenshots/uml%20Agri-POS-activity%20diagram.png

Fungsi: Activity Diagram digunakan untuk menggambarkan alur proses atau aliran kerja sistem secara berurutan dari awal hingga akhir. Diagram ini menunjukkan langkah-langkah aktivitas, percabangan keputusan, serta kondisi sukses dan gagal dalam proses bisnis.

Pada sistem Agri-POS, Activity Diagram menggambarkan proses checkout, yang meliputi:
- Login kasir
- Pemilihan dan input produk
- Validasi ketersediaan stok
- Perhitungan total transaksi
- Pemilihan metode pembayaran
- Proses pembayaran (tunai atau e-wallet)
- Pencetakan struk dan pembaruan stok
- Diagram ini juga menunjukkan skenario alternatif, seperti stok tidak mencukupi atau pembayaran e-wallet gagal.
- Keterkaitan dengan Diagram Lain

Activity Diagram merupakan penjabaran detail dari satu use case, misalnya use case Checkout.
Setiap aktivitas pada diagram ini kemudian diterjemahkan menjadi pesan (message) pada Sequence Diagram.
Aktivitas yang berhubungan dengan logika sistem menjadi acuan dalam penentuan method pada Class Diagram.

---
**3. Sequence Diagram**

praktikum/week6-uml-solid/screenshots/uml Agri-POS-sequence diagram.png

Fungsi: Sequence Diagram digunakan untuk menggambarkan urutan interaksi antar objek atau komponen sistem berdasarkan waktu. Diagram ini menampilkan bagaimana pesan dikirim dan diterima antar objek untuk merealisasikan suatu proses.

Pada sistem Agri-POS, Sequence Diagram menggambarkan proses pembayaran secara detail, mulai dari:
- Kasir memilih produk melalui antarmuka
- Sistem memvalidasi stok
- Sistem memproses pembayaran tunai atau e-wallet
- Interaksi dengan payment gateway (untuk e-wallet)
- Penyimpanan transaksi
- Pembaruan stok
- Pencetakan struk
- Sequence Diagram juga menampilkan skenario alternatif menggunakan alt, seperti saldo e-wallet tidak mencukupi.
- Keterkaitan dengan Diagram Lain

Sequence Diagram adalah implementasi teknis dari Activity Diagram.
- Objek yang terlibat pada Sequence Diagram harus sesuai dengan kelas yang ada pada Class Diagram.
- Setiap pesan pada Sequence Diagram harus memiliki representasi method pada Class Diagram.
  
---

**4. Class Diagram**
Fungsi: Class Diagram digunakan untuk menggambarkan struktur statis sistem, yang meliputi kelas, atribut, method, serta hubungan antar kelas. Diagram ini menjadi blueprint untuk implementasi sistem dalam bentuk kode program. Pada sistem Agri-POS, Class Diagram mencakup kelas-kelas utama seperti:

- Kasir
- Transaksi
- Keranjang dan KeranjangItem
- Produk
- Pembayaran (Tunai dan E-Wallet)
- Payment Gateway
- Struk

Diagram ini juga menunjukkan:
- Pewarisan (inheritance)
- Asosiasi
- Multiplicity (kardinalitas)
- Prinsip pemisahan tanggung jawab antar kelas
- Keterkaitan dengan Diagram Lain

Class Diagram merupakan hasil akhir dari seluruh perancangan diagram.
Use Case menentukan kelas apa saja yang dibutuhkan.
Activity dan Sequence Diagram menentukan method dan interaksi antar kelas.
Class Diagram memastikan sistem dapat diimplementasikan secara terstruktur dan konsisten.

---
## Quiz
1. [Jelaskan perbedaan aggregation dan composition serta berikan contoh penerapannya pada desain Anda.]  
  **Jawaban:** Jawaban: Aggregation dan composition merupakan jenis relasi whole–part dalam UML yang membedakan tingkat ketergantungan objek. Aggregation menunjukkan hubungan kepemilikan yang lemah, di mana objek bagian masih dapat berdiri sendiri meskipun objek induknya tidak ada. Sebaliknya, composition menunjukkan hubungan kepemilikan yang kuat, di mana keberadaan objek bagian sepenuhnya bergantung pada objek induk. Pada desain sistem Agri-POS, relasi antara Transaksi dan Keranjang atau Struk merupakan contoh composition, karena keranjang dan struk hanya ada ketika transaksi dibuat dan akan hilang ketika transaksi dihapus. Sementara itu, relasi antara Admin dan Produk merupakan contoh aggregation, karena produk tetap dapat ada dan dikelola meskipun tidak ada aktivitas admin tertentu yang sedang berlangsung.

2. [Bagaimana prinsip Open/Closed dapat memastikan sistem mudah dikembangkan?]  
   **Jawaban:** Prinsip Open/Closed Principle (OCP) menyatakan bahwa sebuah modul atau kelas harus terbuka untuk dikembangkan, tetapi tertutup untuk diubah. Dalam sistem Agri-POS, prinsip ini diterapkan pada mekanisme pembayaran melalui penggunaan kelas abstrak Pembayaran yang memiliki turunan seperti PembayaranTunai dan PembayaranEWallet. Dengan desain ini, penambahan metode pembayaran baru, misalnya QRIS atau transfer bank, dapat dilakukan dengan menambahkan kelas turunan baru tanpa mengubah kode pada kelas transaksi atau pembayaran yang sudah ada. Pendekatan ini mengurangi risiko terjadinya kesalahan pada sistem yang sudah berjalan dan memastikan pengembangan fitur baru dapat dilakukan secara aman dan terstruktur.

3. [Mengapa Dependency Inversion Principle (DIP) meningkatkan testability? Berikan contoh penerapannya.]  
   **Jawaban**:Dependency Inversion Principle (DIP) meningkatkan testability sistem dengan cara mengurangi ketergantungan langsung antara komponen tingkat tinggi dan implementasi konkret. Dalam desain Agri-POS, kelas Transaksi dan CheckoutService tidak bergantung langsung pada implementasi pembayaran tertentu, melainkan pada abstraksi Pembayaran atau antarmuka layanan pembayaran. Dengan demikian, pada saat pengujian, implementasi pembayaran nyata dapat digantikan dengan mock atau stub yang mensimulasikan hasil pembayaran berhasil atau gagal. Pendekatan ini memungkinkan pengujian unit dilakukan secara terisolasi tanpa bergantung pada sistem eksternal seperti payment gateway, sehingga proses pengujian menjadi lebih mudah, cepat, dan andal. 
