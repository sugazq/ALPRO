package model;

import java.util.ArrayList;

public class Transaksi {
    private ArrayList<Obat> daftarObat;
    private int totalHarga;

    public Transaksi() {
        daftarObat = new ArrayList<>();
        totalHarga = 0;
    }

    public void tambahObat(Obat obat) {
        daftarObat.add(obat);
        totalHarga += obat.getHarga();
    }

    public void tampilkanNota() {
        System.out.println("=== Nota Transaksi ===");
        for (Obat obat : daftarObat) {
            System.out.println(obat.getNama() + "\tRp" + obat.getHarga());
        }
        System.out.println("=====================");
        System.out.println("Total\tRp" + totalHarga);
    }

    public ArrayList<Obat> getDaftarObat() {
        return daftarObat;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void resetTransaksi() {
        daftarObat.clear();
        totalHarga = 0;
    }
}
