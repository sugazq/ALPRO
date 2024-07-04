package controller;

import javax.swing.SwingUtilities;
import model.PelayanApotek;
import view.ApotekGUI;

public class ApotekApp {
    public static void main(String[] args) {
        PelayanApotek[] pelayanArray = {
                new PelayanApotek("RIDHO"),
                new PelayanApotek("AZIS PANJI"),
                new PelayanApotek("MUTHIA"),
                new PelayanApotek("BREY"),
                new PelayanApotek("ERIS")
        };

        SwingUtilities.invokeLater(() -> new ApotekGUI(pelayanArray));
    }
}
