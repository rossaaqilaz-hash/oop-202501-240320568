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