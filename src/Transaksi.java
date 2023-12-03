// Class Transaksi sebagai class yang merepresentasikan transaksi yang dilakukan
// oleh nasabah
// Mengimplementasikan konsep komposisi

import java.util.ArrayList;

class Transaksi {
    // Atribut untuk transaksi
    private String jenis; // Jenis transaksi: Simpan, Tarik, Transfer, atau Terima
    private double jumlah; // Jumlah uang yang terlibat dalam transaksi
    private double saldo; // Saldo setelah transaksi
    private Nasabah lawan; // Lawan transaksi, jika ada

    // Konstruktor untuk transaksi Simpan atau Tarik
    public Transaksi(String jenis, double jumlah, double saldo) {
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.saldo = saldo;
        this.lawan = null; //
    }

    // Konstruktor untuk transaksi Transfer atau Terima
    public Transaksi(String jenis, double jumlah, double saldo, Nasabah lawan) {
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.saldo = saldo;
        this.lawan = lawan; // Mengimplementasikan konsep asosiasi
    }

    // Getter dan setter untuk atribut-atribut transaksi
    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Nasabah getLawan() {
        return lawan;
    }

    public void setLawan(Nasabah lawan) {
        this.lawan = lawan;
    }

    // Method untuk menampilkan informasi transaksi
    public String toString() {
        String info = String.format("| %-15s | %-15s | %-15s |", jenis, "Rp " + jumlah, "Rp " + saldo);
        if (lawan != null) {
            info += String.format(" %-20s |", lawan.getNama());
        } else {
            info += String.format(" %-20s |", " ");
        }
        return info;
    }
}
