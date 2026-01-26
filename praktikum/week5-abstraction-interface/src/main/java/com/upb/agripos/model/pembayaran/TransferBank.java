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