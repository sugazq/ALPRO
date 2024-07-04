package model;

public class Obat {
    private String nama;
    private int harga;

    public Obat(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return nama + " (Rp" + harga + ")";
    }
}
