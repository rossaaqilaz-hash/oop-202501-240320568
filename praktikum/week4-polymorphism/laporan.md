# Laporan Praktikum Minggu 4 (sesuaikan minggu ke berapa?)
Topik: [Polymorphism (Info Produk)]

## Identitas
- Nama  : [Rossa Aqila Zahra]
- NIM   : [240320568]
- Kelas : [3DSRA]

---

## Tujuan
- Mahasiswa mampu **menjelaskan konsep polymorphism** dalam OOP.  
- Mahasiswa mampu **membedakan method overloading dan overriding**.  
- Mahasiswa mampu **mengimplementasikan polymorphism (overriding, overloading, dynamic binding)** dalam program.  
- Mahasiswa mampu **menganalisis contoh kasus polymorphism** pada sistem nyata (Agri-POS).  
---

## Dasar Teori
olymorphism berarti “banyak bentuk” dan memungkinkan objek yang berbeda merespons panggilan method yang sama dengan cara yang berbeda.  
1. **Overloading** → mendefinisikan method dengan nama sama tetapi parameter berbeda.  
2. **Overriding** → subclass mengganti implementasi method dari superclass.  
3. **Dynamic Binding** → pemanggilan method ditentukan saat runtime, bukan compile time.  

Dalam konteks Agri-POS, misalnya:  
- Method `getInfo()` pada `Produk` dioverride oleh `Benih`, `Pupuk`, `AlatPertanian` untuk menampilkan detail spesifik.  
- Method `tambahStok()` bisa dibuat overload dengan parameter berbeda (int, double).  

---

## Langkah Praktikum
1. **Overloading**  
   - Tambahkan method `tambahStok(int jumlah)` dan `tambahStok(double jumlah)` pada class `Produk`.  

2. **Overriding**  
   - Tambahkan method `getInfo()` pada superclass `Produk`.  
   - Override method `getInfo()` pada subclass `Benih`, `Pupuk`, dan `AlatPertanian`.  

3. **Dynamic Binding**  
   - Buat array `Produk[] daftarProduk` yang berisi objek `Benih`, `Pupuk`, dan `AlatPertanian`.  
   - Loop array tersebut dan panggil `getInfo()`. Perhatikan bagaimana Java memanggil method sesuai jenis objek aktual.  

4. **Main Class**  
   - Buat `MainPolymorphism.java` untuk mendemonstrasikan overloading, overriding, dan dynamic binding.  

5. **CreditBy**  
   - Tetap panggil `CreditBy.print("<NIM>", "<Nama>")`.  

6. **Commit dan Push**  
   - Commit dengan pesan: `week4-polymorphism`.  

---

## Kode Program
### Produk.java (Overloading & getInfo default)
```java
//overloading dan getInfo default

package com.upb.agripos.model;

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

    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void tambahStok(double jumlah) {
        this.stok += (int) jumlah;
    }

    public String getInfo() {
        return "Produk: " + nama + " (Kode: " + kode + ")";
    }
}
```

### Benih.java (Overriding)
```java
//overriding

package com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    @Override
    public String getInfo() {
        return "Benih: " + super.getInfo() + ", Varietas: " + varietas;
    }
}
```

### Pupuk.java 
```java
//overriding pupuk

package com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    @Override
    public String getInfo() {
        return "Pupuk: " + super.getInfo() + ", Jenis: " + jenis;
    }
}

### AlatPertanian.java 
```java
//overriding alat pertanian

package com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String material;

    public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;
    }

    @Override
    public String getInfo() {
        return "Alat Pertanian: " + super.getInfo() + ", Material: " + material;
    }
}

### CresitBy.java
```java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nama, String nim) {
        System.out.println("\nCredit by: " + nama + " - " + nim);
    }
}

### MainPolymorphism.java
```java
//main program polymorphism

package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainPolymorphism {
    public static void main(String[] args) {
        
        System.out.println("=== Program Agri-POS ===");
        System.out.println("------------------------");
        System.out.println("Daftar Produk: ");
        
        Produk[] daftarProduk = {
            new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
            new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja")
        };

        for (Produk p : daftarProduk) {
            System.out.println(p.getInfo()); // Dynamic Binding
        }

        CreditBy.print("Rossa Aqila Zahra", "240320568");
    }
}
```

---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![MainPolymorphism](https://github.com/rossaaqilaz-hash/oop-202501-240320568/tree/1b71815e4fcb5bebc00b6bd598e6ba86b465e9ba/praktikum/week4-polymorphism/screenshots)
)
---

## Analisis
(
- Jelaskan bagaimana kode berjalan.  
1. Overloading pada class Produk dengan dua method tambahStok() yang memiliki parameter berbeda (int dan double). Hal ini menunjukkan bahwa satu nama method bisa memiliki perilaku berbeda tergantung parameter yang diberikan.

2. Overriding diterapkan di subclass (Benih, Pupuk, dan AlatPertanian) yang mengganti implementasi method getInfo() dari superclass Produk. Ini memungkinkan tiap objek menampilkan informasi sesuai jenisnya masing-masing.

3. Dynamic Binding terjadi ketika method getInfo() dipanggil dari array Produk[] daftarProduk. Walaupun referensinya bertipe Produk, Java secara dinamis menentukan method mana yang dipanggil berdasarkan objek aktualnya (Benih, Pupuk, atau AlatPertanian) saat runtime.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
- Minggu sebelumnya fokus pada pewarisan struktur antar kelas (superclass dan subclass).
- Minggu ini fokus pada perilaku yang berbeda untuk method yang sama, yang membuat program lebih fleksibel dan mudah diperluas.

- Kendala yang dihadapi dan cara mengatasinya.  
Awalnya kesulitan memahami perbedaan antara overloading dan overriding.
Solusinya adalah dengan melihat parameter method dan posisi class-nya — jika di class yang sama dengan parameter berbeda → overloading; jika di subclass dengan method yang sama → overriding.
)
---

## Kesimpulan
Tuliskan kesimpulan dari praktikum minggu ini.  
Polymorphism memungkinkan satu interface digunakan untuk berbagai bentuk objek, sehingga program menjadi lebih dinamis, efisien, dan mudah diperluas.
Dengan overloading, method bisa memiliki nama sama tetapi parameter berbeda untuk menyesuaikan kebutuhan dan dengan overriding, subclass bisa mendefinisikan perilaku khusus tanpa mengubah struktur superclass.
Konsep dynamic binding memastikan bahwa method yang sesuai dengan tipe objek aktual dipanggil pada runtime, bukan saat kompilasi.
Dalam konteks Agri-POS, polymorphism membuat sistem pengelolaan produk menjadi lebih fleksibel, karena setiap jenis produk dapat memiliki perilaku spesifik sendiri.
---

## Quiz
1. [Apa perbedaan overloading dan overriding?]  
   **Jawaban:** Overloading terjadi ketika ada dua atau lebih method dengan nama sama tetapi parameter berbeda dalam satu class.

Overriding terjadi ketika subclass mengganti implementasi method dari superclass dengan nama dan parameter yang sama.

2. [Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding?]  
   **Jawaban:** Java menentukan method yang dipanggil berdasarkan tipe objek aktual yang direferensikan, bukan berdasarkan tipe referensinya. Proses ini dilakukan saat runtime, bukan saat compile time.

3. [Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.]  
   **Jawaban:** Dalam sistem POS restoran: class MenuItem bisa dioverride oleh subclass Makanan, Minuman, dan Dessert. Ketika getInfo() dipanggil, masing-masing menampilkan informasi berbeda sesuai jenis item (misalnya ukuran porsi atau suhu minuman).
