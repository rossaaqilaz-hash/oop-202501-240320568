# Laporan Praktikum Minggu 3 (sesuaikan minggu ke berapa?)
Topik: [Inheritance (Kategori Produk)]

## Identitas
- Nama  : [Rossa Aqila Zahra]
- NIM   : [240320568]
- Kelas : [3DSRA]

---

## Tujuan
- Mahasiswa mampu **menjelaskan konsep inheritance (pewarisan class)** dalam OOP.  
- Mahasiswa mampu **membuat superclass dan subclass** untuk produk pertanian.  
- Mahasiswa mampu **mendemonstrasikan hierarki class** melalui contoh kode.  
- Mahasiswa mampu **menggunakan `super` untuk memanggil konstruktor dan method parent class**.  
- Mahasiswa mampu **membuat laporan praktikum** yang menjelaskan perbedaan penggunaan inheritance dibanding class tunggal.  

---

## Dasar Teori
Inheritance adalah mekanisme dalam OOP yang memungkinkan suatu class mewarisi atribut dan method dari class lain.  
- **Superclass**: class induk yang mendefinisikan atribut umum.  
- **Subclass**: class turunan yang mewarisi atribut/method superclass, dan dapat menambahkan atribut/method baru.  
- `super` digunakan untuk memanggil konstruktor atau method superclass.  

Dalam konteks Agri-POS, kita dapat membuat class `Produk` sebagai superclass, kemudian `Benih`, `Pupuk`, dan `AlatPertanian` sebagai subclass. Hal ini membuat kode lebih reusable dan terstruktur.


---

## Langkah Praktikum
1. **Membuat Superclass Produk**  
   - Gunakan class `Produk` dari Bab 2 sebagai superclass.  

2. **Membuat Subclass**  
   - `Benih.java` → atribut tambahan: varietas.  
   - `Pupuk.java` → atribut tambahan: jenis pupuk (Urea, NPK, dll).  
   - `AlatPertanian.java` → atribut tambahan: material (baja, kayu, plastik).  

3. **Membuat Main Class**  
   - Instansiasi minimal satu objek dari tiap subclass.  
   - Tampilkan data produk dengan memanfaatkan inheritance.  

4. **Menambahkan CreditBy**  
   - Panggil class `CreditBy` untuk menampilkan identitas mahasiswa.  

5. **Commit dan Push**  
   - Commit dengan pesan: `week3-inheritance`.  

---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

```java
// Contoh
Produk p1 = new Produk("BNH-001", "Benih Padi", 25000, 100);
System.out.println(p1.getNama());
```
)
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots/hasil.png)
)
---

## Analisis
Pada praktikum minggu ini, konsep inheritance (pewarisan) pada pemrograman berorientasi objek diterapkan untuk menghindari duplikasi kode dan meningkatkan keteraturan program.
Class Produk berperan sebagai superclass yang menyimpan atribut umum seperti kode, nama, harga, dan stok.
Kemudian, class Benih, Pupuk, dan AlatPertanian dijadikan subclass yang mewarisi semua atribut dan method dari Produk, sambil menambahkan atribut spesifik seperti varietas, jenis, dan material.

Saat program dijalankan pada MainInheritance.java, masing-masing subclass di-instansiasi menjadi objek (b, p, dan a).
Setiap objek memanggil method getNama() dari superclass serta getVarietas(), getJenis(), dan getMaterial() dari subclass untuk menampilkan data produk sesuai jenisnya.
Bagian akhir program memanggil CreditBy.print("<NIM>", "<Nama Mahasiswa>") untuk menampilkan identitas pembuat program.

Perbedaan pendekatan minggu ini dibandingkan minggu sebelumnya adalah pada pemanfaatan pewarisan (inheritance).
Jika pada minggu sebelumnya setiap class dibuat berdiri sendiri (mengakibatkan kode berulang), maka minggu ini struktur program lebih efisien karena kode umum didefinisikan sekali di Produk dan digunakan kembali di subclass.

Kendala yang dihadapi biasanya terkait pemanggilan konstruktor superclass menggunakan super(...).
Kesalahan penulisan atau urutan parameter sering menyebabkan error.
Kendala tersebut diatasi dengan memastikan setiap subclass memanggil konstruktor superclass dengan parameter yang sesuai urutan dan tipe datanya.

---

## Kesimpulan
Dengan menerapkan konsep inheritance, program menjadi lebih terstruktur, efisien, dan mudah dikelola.
Pewarisan memungkinkan subclass menggunakan atribut dan method dari superclass tanpa menulis ulang kode yang sama.
Konsep ini sangat berguna dalam pengembangan sistem besar seperti aplikasi Point of Sales (POS) pertanian, karena mempermudah pengelolaan berbagai jenis produk yang memiliki kesamaan karakteristik tetapi tetap dapat dikustomisasi sesuai kebutuhan.

---

## Quiz
1. [Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?]  
   **Jawaban:** Keuntungan utama penggunaan inheritance adalah memungkinkan reuse (penggunaan ulang) kode dari superclass sehingga tidak perlu menulis ulang atribut dan method yang sama di setiap class.
Dengan inheritance, struktur program menjadi lebih terorganisir, efisien, dan mudah dipelihara. Jika terjadi perubahan pada atribut umum, cukup dilakukan di superclass tanpa perlu mengubah semua subclass. 


2. [Bagaimana cara subclass memanggil konstruktor superclass?]
   **Jawaban:** Subclass memanggil konstruktor superclass menggunakan keyword super() di dalam konstruktor subclass.
Pemanggilan ini dilakukan pada baris pertama konstruktor untuk mengirimkan nilai dari subclass ke konstruktor milik superclass.
Contoh:
public Benih(String kode, String nama, double harga, int stok, String varietas) {
    super(kode, nama, harga, stok);  // memanggil konstruktor superclass Produk
    this.varietas = varietas;
   }


4. [Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.]
   **Jawaban:** Contoh lain yang bisa dijadikan subclass adalah ObatTanaman yang memiliki atribut tambahan seperti kandunganAktif atau tanggalKadaluarsa.
   Misalnya:

```public class ObatTanaman extends Produk {
    private String kandunganAktif;
    private String tanggalKadaluarsa;

    public ObatTanaman(String kode, String nama, double harga, int stok, String kandunganAktif, String tanggalKadaluarsa) {
        super(kode, nama, harga, stok);
        this.kandunganAktif = kandunganAktif;
        this.tanggalKadaluarsa = tanggalKadaluarsa;```
    }
}


Dengan demikian, sistem POS pertanian dapat menampung berbagai jenis produk tanpa harus membuat class dari nol. )
