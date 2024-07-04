package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.ApotekController;
import model.Obat;
import model.PelayanApotek;

public class ApotekGUI extends JFrame {
    private ApotekController apotekController;
    private JComboBox<String> pelayanComboBox;
    private JTextArea notaArea;
    private DefaultListModel<Obat> listModel;
    private JList<Obat> obatList;
    private JLabel totalLabel;

    public ApotekGUI(PelayanApotek[] pelayanArray) {
        // Setting up the JFrame
        setTitle("Aplikasi Apotek");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel atas untuk memilih pelayan dan daftar obat
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel pelayanPanel = new JPanel(new FlowLayout());

        // Combo box untuk memilih pelayan
        pelayanComboBox = new JComboBox<>();
        for (PelayanApotek pelayan : pelayanArray) {
            pelayanComboBox.addItem(pelayan.getNama());
        }
        pelayanComboBox.addActionListener(e -> {
            int index = pelayanComboBox.getSelectedIndex();
            apotekController = new ApotekController(pelayanArray[index]);
        });
        pelayanPanel.add(new JLabel("Pilih Pelayan:"));
        pelayanPanel.add(pelayanComboBox);
        topPanel.add(pelayanPanel, BorderLayout.NORTH);

        // Daftar obat yang tersedia
        String[] obatNames = {
                "Paracetamol (Rp5000)",
                "Amoxicillin (Rp10000)",
                "Vitamin C (Rp8000)",
                "Perispirant (Rp75000)",
                "Alparazolam (Rp118000)",
                "Cetirizine (Rp19000)",
                "Gliserin (Rp7000)",
                "Ibuprofen (Rp28000)",
                "Mikonazol (Rp39000)",
                "OBH (Rp37000)",
                "COMBI (Rp20000)",
                "Retinol Vitamin A (Rp29000)"
        };

        int[] obatPrices = {
                5000, 10000, 8000, 75000, 118000, 19000, 7000, 28000, 39000, 37000, 20000, 29000
        };

        listModel = new DefaultListModel<>();
        for (int i = 0; i < obatNames.length; i++) {
            listModel.addElement(new Obat(obatNames[i].split(" ")[0], obatPrices[i]));
        }

        obatList = new JList<>(listModel);
        topPanel.add(new JScrollPane(obatList), BorderLayout.CENTER);

        // Tombol untuk menambah obat ke dalam transaksi
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton tambahButton = new JButton("Tambah Obat");
        tambahButton.addActionListener(e -> {
            Obat selectedObat = obatList.getSelectedValue();
            if (selectedObat != null) {
                apotekController.tambahObat(selectedObat);
                updateNota();
            }
        });

        buttonPanel.add(tambahButton);

        // Tombol untuk menyelesaikan pesanan
        JButton selesaiButton = new JButton("Selesai Pesanan");
        selesaiButton.addActionListener(e -> {
            cetakNota();
            JOptionPane.showMessageDialog(this, "Nota telah dicetak. Terima kasih!");
            apotekController.getTransaksi().resetTransaksi();
            updateNota();
        });

        buttonPanel.add(selesaiButton);

        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Area teks untuk menampilkan nota transaksi
        notaArea = new JTextArea();
        notaArea.setEditable(false);
        add(new JScrollPane(notaArea), BorderLayout.CENTER);

        // Label untuk menampilkan total harga
        totalLabel = new JLabel("Total: Rp0");
        add(totalLabel, BorderLayout.SOUTH);

        pelayanComboBox.setSelectedIndex(0);
        setVisible(true);
    }

    private void updateNota() {
        notaArea.setText("=== Nota Transaksi ===\n");
        for (Obat obat : apotekController.getTransaksi().getDaftarObat()) {
            notaArea.append(obat.getNama() + "\tRp" + obat.getHarga() + "\n");
        }
        notaArea.append("=====================\n");
        notaArea.append("Total\tRp" + apotekController.getTransaksi().getTotalHarga() + "\n");

        totalLabel.setText("Total: Rp" + apotekController.getTransaksi().getTotalHarga());
    }

    private void cetakNota() {
        System.out.println("=== Nota Transaksi ===");
        for (Obat obat : apotekController.getTransaksi().getDaftarObat()) {
            System.out.println(obat.getNama() + "\tRp" + obat.getHarga());
        }
        System.out.println("=====================");
        System.out.println("Total\tRp" + apotekController.getTransaksi().getTotalHarga());
    }
}
