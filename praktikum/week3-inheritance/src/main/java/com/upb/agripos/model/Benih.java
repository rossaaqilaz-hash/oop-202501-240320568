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