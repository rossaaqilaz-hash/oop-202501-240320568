# Laporan Praktikum Minggu 5
Topik: [Abstraction (Abstract Class & Interface)"]

## Identitas
- Nama  : [Rossa Aqila Zahra]
- NIM   : [240320568]
- Kelas : [3DSRA]

---

## Tujuan
- Mahasiswa mampu **menjelaskan perbedaan abstract class dan interface**.
- Mahasiswa mampu **mendesain abstract class dengan method abstrak** sesuai kebutuhan kasus.
- Mahasiswa mampu **membuat interface dan mengimplementasikannya pada class**.
- Mahasiswa mampu **menerapkan multiple inheritance melalui interface** pada rancangan kelas.
- Mahasiswa mampu **mendokumentasikan kode** (komentar kelas/method, README singkat pada folder minggu).

---

## Dasar Teori
**Abstraksi** adalah proses menyederhanakan kompleksitas dengan menampilkan elemen penting dan menyembunyikan detail implementasi.
- **Abstract class**: tidak dapat diinstansiasi, dapat memiliki method abstrak (tanpa badan) dan non-abstrak. Dapat menyimpan state (field).
- **Interface**: kumpulan kontrak (method tanpa implementasi konkret). Sejak Java 8 mendukung default method. Mendukung **multiple inheritance** (class dapat mengimplementasikan banyak interface).
- Gunakan **abstract class** bila ada _shared state_ dan perilaku dasar; gunakan **interface** untuk mendefinisikan kemampuan/kontrak lintas hierarki.

Dalam konteks Agri-POS, **Pembayaran** dapat dimodelkan sebagai abstract class dengan method abstrak `prosesPembayaran()` dan `biaya()`. Implementasi konkritnya: `Cash` dan `EWallet`. Kemudian, interface seperti `Validatable` (mis. verifikasi OTP) dan `Receiptable` (mencetak bukti) dapat diimplementasikan oleh jenis pembayaran yang relevan.

---

## Langkah Praktikum
1. **Abstract Class – Pembayaran**
   - Buat `Pembayaran` (abstract) dengan field `invoiceNo`, `total` dan method:
     - `double biaya()` (abstrak) → biaya tambahan (fee).
     - `boolean prosesPembayaran()` (abstrak) → mengembalikan status berhasil/gagal.
     - `double totalBayar()` (konkrit) → `return total + biaya();`.

2. **Subclass Konkret**
   - `Cash` → biaya = 0, proses = selalu berhasil jika `tunai >= totalBayar()`.
   - `EWallet` → biaya = 1.5% dari `total`; proses = membutuhkan validasi.

3. **Interface**
   - `Validatable` → `boolean validasi();` (contoh: OTP).
   - `Receiptable` → `String cetakStruk();`

4. **Multiple Inheritance via Interface**
   - `EWallet` mengimplementasikan **dua interface**: `Validatable`, `Receiptable`.
   - `Cash` setidaknya mengimplementasikan `Receiptable`.

5. **Main Class**
    - Buat `MainAbstraction.java` untuk mendemonstrasikan pemakaian `Pembayaran` (polimorfik).
    - Tampilkan hasil proses dan struk. Di akhir, panggil `CreditBy.print("[NIM]", "[Nama]")`.

6. **Commit dan Push**
   - Commit dengan pesan: `week5-abstraction-interface`.

---

## Kode Program

### Receitable.Java
``` Java
package com.upb.agripos.model.kontrak;

public interface Receiptable {
    String cetakStruk();
}
```

### Validatable.Java
``` Java
package com.upb.agripos.model.kontrak;

public interface Validatable {
    boolean validasi();
}
```

### Cash.Java
``` Java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Receiptable;

public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

    @Override
    public double biaya() {
        return 0.0;
    }

    @Override
    public boolean prosesPembayaran() {
        return tunai >= totalBayar(); // sederhana: cukup uang tunai
    }

    @Override
    public String cetakStruk() {
        return String.format("INVOICE %s | TOTAL: %.2f | BAYAR CASH: %.2f | KEMBALI: %.2f",
                invoiceNo, totalBayar(), tunai, Math.max(0, tunai - totalBayar()));
    }
}
```

### EWallet.Java
``` Java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp; // sederhana untuk simulasi

    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return total * 0.015; // 1.5% fee
    }

    @Override
    public boolean validasi() {
        return otp != null && otp.length() == 6; // contoh validasi sederhana
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi(); // jika validasi lolos, anggap berhasil
    }

    @Override
    public String cetakStruk() {
        return String.format("INVOICE %s | TOTAL+FEE: %.2f | E-WALLET: %s | STATUS: %s",
                invoiceNo, totalBayar(), akun, prosesPembayaran() ? "BERHASIL" : "GAGAL");
    }
}
```

### Pembayaran.Java
``` Java
package com.upb.agripos.model.pembayaran;

public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    public abstract double biaya();               // fee/biaya tambahan
    public abstract boolean prosesPembayaran();   // proses spesifik tiap metode

    public double totalBayar() {
        return total + biaya();
    }

    public String getInvoiceNo() { return invoiceNo; }
    public double getTotal() { return total; }
}
```

### TransferBank.Java
``` Java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class TransferBank extends Pembayaran implements Validatable, Receiptable {
    private String namaBank;
    private String kodeTransfer; //untuk validasi

    public TransferBank (String invoiceNo, double total, String namaBank, String kodeTransfer) {
        super(invoiceNo, total);
        this.namaBank = namaBank;
        this.kodeTransfer = kodeTransfer;
    }

    @Override
    public double biaya() {
        return 3500.0; //biaya tetap 3.500
    }
    
    @Override
    public boolean validasi() {
        return kodeTransfer != null && kodeTransfer.length() == 8; //contoh validasi sederhana
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi(); //jika validasi berhasil
    }

    @Override
    public String cetakStruk() {
        return "  STRUCK PEMBAYARAN TRANSFER  \n" +
                "------------------------------\n" +
                String.format(
                    "INVOICE    : %s %n" + 
                    "TOTAL+FEE  : Rp %.2f %n" +
                    "BANK       : %s %n" + 
                    "STATUS     : %s %n",
                        invoiceNo, totalBayar(), namaBank, prosesPembayaran() ? "BERHASIL" : "GAGAL");
    }
}
```

### Credit.Java
``` Java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nama, String nim) {
        System.out.println("\nCredit by: " + nama + " - " + nim);
    }
}
```

### MainAbstraction.Java
``` Java
package com.upb.agripos;

import com.upb.agripos.model.pembayaran.*;
import com.upb.agripos.model.kontrak.*;
import com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        Pembayaran cash = new Cash("INV-001", 100000, 120000);
        Pembayaran ew = new EWallet("INV-002", 150000, "user@ewallet", "123456");

        System.out.println(((Receiptable) cash).cetakStruk());
        System.out.println(((Receiptable) ew).cetakStruk());

    CreditBy.print("[240320568]", "[Rossa Aqila Zahra]");
    }
}
```
---

## Hasil Eksekusi
![MainAbstraction](https://github.com/rossaaqilaz-hash/oop-202501-240320568/tree/3efcf4253433d67685749125eddc32ce4664afde/praktikum/week5-abstraction-interface/screenshots)
)
---

## Analisis

- Jelaskan bagaimana kode berjalan.
  
Program dimulai dari MainAbstraction.java, yang membuat dua objek turunan dari abstract class Pembayaran: yaitu Cash dan EWallet. Karena Pembayaran adalah abstract class, objeknya tidak dapat dibuat langsung. Kelas Cash dan EWallet wajib mengimplementasikan method abstrak biaya() dan prosesPembayaran().
Objek cash dan ew kemudian di-cast ke interface Receiptable agar bisa memanggil method cetakStruk().
Pada Cash, metode prosesPembayaran() memeriksa apakah uang tunai mencukupi, dan bila cukup akan menampilkan struk pembayaran dengan kembalian.
Pada EWallet, metode prosesPembayaran() bergantung pada hasil validasi OTP dari interface Validatable. Jika OTP 6 digit, maka dianggap valid dan transaksi berhasil.
Terakhir, CreditBy.print() menampilkan identitas pembuat program sebagai bentuk dokumentasi.  

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.
  
Minggu sebelumnya, fokus pada polymorphism — penggunaan method overriding pada kelas turunan untuk memberikan perilaku berbeda.
Minggu ini, konsep abstraction memperluas hal tersebut dengan menyembunyikan detail implementasi pada abstract class dan mendefinisikan kontrak perilaku melalui interface.
Abstraction menekankan pada “apa yang harus dilakukan”, bukan “bagaimana melakukannya”.
Dengan interface, program dapat menggunakan multiple inheritance — misalnya EWallet mengimplementasikan dua interface (Validatable dan Receiptable), yang tidak bisa dilakukan hanya dengan class biasa.

- Kendala yang dihadapi dan cara mengatasinya.
  
Kendala 1 Awalnya, error muncul karena file interface (Validatable dan Receiptable) berada di package yang berbeda dan belum di-import dengan benar Solusinya Menambahkan import com.upb.agripos.model.kontrak.*; di bagian atas file yang memerlukan.
Kendala 2 Salah ketik pada nama file atau class (Receitable vs Receiptable) Solusinya Menyamakan nama file dan nama class agar sesuai.
Kendala 3 validasi OTP: Program awal tidak memeriksa panjang OTP dengan benar Solusinya Menambahkan kondisi otp.length() == 6 agar lebih realistis.

---

## Kesimpulan
Abstraction memudahkan pembuatan struktur kode yang lebih terorganisir dengan memisahkan konsep dan implementasi. Abstract class digunakan untuk menyimpan perilaku dasar dan shared state (seperti invoiceNo, total), sementara interface digunakan untuk mendefinisikan kontrak perilaku yang dapat diterapkan di berbagai kelas.
Dengan menggabungkan abstract class dan interface, program menjadi lebih fleksibel dan mudah dikembangkan (misalnya menambah jenis pembayaran baru tanpa mengubah kode utama).
Konsep ini juga mendukung prinsip OOP seperti encapsulation, inheritance, dan polymorphism dengan cara yang lebih modular.

---

## Quiz
1. [Jelaskan perbedaan konsep dan penggunaan abstract class dan interface.]  
   **Jawaban:**
   Abstract class digunakan untuk membuat kerangka dasar suatu class yang memiliki state (variabel) dan perilaku umum yang bisa diwariskan. Abstract class bisa memiliki method abstrak (tanpa isi) dan method konkret (dengan isi).
Contohnya Pembayaran yang punya field invoiceNo dan total, serta method totalBayar() yang sudah memiliki implementasi.
Interface digunakan untuk mendefinisikan kontrak perilaku tanpa menyimpan data atau implementasi (kecuali default method sejak Java 8). Interface berfungsi untuk mendefinisikan kemampuan tambahan yang dapat dimiliki oleh berbagai class yang berbeda hierarki contoh Validatable dan Receiptable, yang mendefinisikan perilaku umum seperti validasi() dan cetakStruk().

2. [Mengapa multiple inheritance lebih aman dilakukan dengan interface pada Java?]  
   **Jawaban:**
   Karena interface tidak membawa state atau implementasi konkret, sehingga tidak menimbulkan konflik pewarisan seperti pada multiple inheritance antar class (misalnya dua class induk memiliki field atau method yang sama).
Dengan interface, Java hanya mewarisi kontrak perilaku, bukan data atau logika aktual, sehingga aman untuk digabungkan dalam satu class. Misalnya, EWallet bisa mengimplementasikan Validatable dan Receiptable tanpa risiko bentrok antar kode.

3. [Pada contoh Agri-POS, bagian mana yang paling tepat menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya.]  
   **Jawaban:** 
- Abstract class: Pembayaran
  Karena Pembayaran memiliki atribut umum (invoiceNo, total) dan sebagian perilaku dasar (totalBayar()), tetapi cara menghitung biaya() dan prosesPembayaran() berbeda untuk setiap jenis pembayaran.
- Interface: Validatable dan Receiptable
  Karena keduanya hanya mendefinisikan perilaku tambahan (kemampuan) tanpa menyimpan data. Validatable untuk verifikasi transaksi (seperti OTP atau kode transfer), dan Receiptable untuk mencetak struk transaksi.
Dengan kombinasi ini, program menjadi fleksibel, terstruktur, dan mudah diperluas (misalnya menambah metode pembayaran baru seperti TransferBank tanpa mengubah kode yang sudah ada).
