package model;

import controller.ApotekController;

public class PelayanApotek {
    private String nama;

    public PelayanApotek(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void prosesTransaksi(ApotekController apotekController, Obat obat) {
        System.out.println("Pelayan: " + nama);
        apotekController.tambahObat(obat);
        apotekController.tampilkanNota();
    }
}
