package controller;

import model.PelayanApotek;
import model.Obat;
import model.Transaksi;

public class ApotekController {
    private Transaksi transaksi;
    private PelayanApotek pelayan;

    public ApotekController(PelayanApotek pelayan) {
        this.pelayan = pelayan;
        transaksi = new Transaksi();
    }

    public void tambahObat(Obat obat) {
        transaksi.tambahObat(obat);
    }

    public void tampilkanNota() {
        transaksi.tampilkanNota();
    }

    public void prosesTransaksi(Obat obat) {
        pelayan.prosesTransaksi(this, obat);
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public PelayanApotek getPelayan() {
        return pelayan;
    }
}
