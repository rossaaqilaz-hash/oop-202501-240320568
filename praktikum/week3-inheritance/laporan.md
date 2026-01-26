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
### Benih.java
```java
//Subclass benih

package com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    public String getVarietas() { return varietas; }
    public void setVarietas(String varietas) { this.varietas = varietas; }

    //Method tambahan latihan mandiri
    public void deskripsi() {
        System.out.println("Benih varietas " + varietas + " memiliki keunggulan daya tahan tumbuh tinggi");
    }
}
```

### Pupuk.java
```java
//subclass pupuk

package com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    //method tambahan
    public void deskripsi() {
        System.out.println("Pupuk jenis " + jenis + " direkomendasikan untuk tanaman padi dan jagung");
    }

    public static void main(String[] args) {
        Pupuk pupuk = new Pupuk("PK-001", "Urea", 75000, 50, "Nitrogen");
        System.out.println("Kode: " + pupuk.getKode());
        System.out.println("Nama: " + pupuk.getNama());
        System.out.println("Harga: Rp" + pupuk.getHarga());
        System.out.println("Stok: " + pupuk.getStok());
        System.out.println("Jenis: " + pupuk.getJenis());
        pupuk.deskripsi();
    }
}
```

### AlatPertanian.java
```java
package com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String material;

    public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    //method tambahan
    public void deskripsi() {
        System.out.println("Alat pertanian cangkul berbahan " + material + " ini kuat dan tahan lama ");
    }
}
```

### MainInheritance.java
```java
//MainInheritance

package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        // Inisialisasi objek produk
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64");
        Pupuk p = new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea");
        AlatPertanian a = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja");

        // Header
        System.out.println("==============================");
        System.out.println("        Program Agri-POS     ");
        System.out.println("==============================");
        
        // Daftar Produk
        System.out.println("\n=== Daftar Produk ===");
        System.out.println("1. Benih    : " + b.getNama() + " (Varietas: " + b.getVarietas() + ")");
        System.out.println("2. Pupuk    : " + p.getNama() + " (Jenis: " + p.getJenis() + ")");
        System.out.println("3. Alat     : " + a.getNama() + " (Material: " + a.getMaterial() + ")");
        System.out.println("------------------------------");
        
        // Deskripsi Produk
        System.out.println("\n=== Deskripsi Produk ===");
        b.deskripsi();
        p.deskripsi();
        a.deskripsi();
        
        // Footer
        System.out.println("\n==============================");
        CreditBy.print("Rossa Aqila Zahra", "240320568");
        System.out.println("==============================");
    }
}
```
### CreaditBy.java
```java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nama, String nim) {
        System.out.println("\nCredit by: " + nama + " - " + nim);
    }
}
```
---

## Hasil Eksekusi
![MainInheritance](https://github.com/rossaaqilaz-hash/oop-202501-240320568/blob/060a8fd452f202eb281bda8cd8211186043a5228/praktikum/week3-inheritance/screenshots/MainInheritance.png)
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


3. [Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.]
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
