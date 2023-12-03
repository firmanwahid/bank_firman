// Class Bank sebagai class yang merepresentasikan bank yang memiliki nasabah
// Mengimplementasikan konsep agregasi

import java.util.ArrayList;

class Bank {
    // Atribut untuk bank
    private String nama;
    private ArrayList<Nasabah> nasabah; // Mengimplementasikan konsep agregasi
    private ArrayList<Akun> listadmin;

    // Konstruktor untuk class Bank
    public Bank(String nama) {
        this.nama = nama;
        this.listadmin = new ArrayList<>();
        this.nasabah = new ArrayList<>(); // Membuat objek ArrayList baru
    }

    public void tambahAdmin(String username, String password) {
        listadmin.add(new Akun(username, password));
    }

    // Getter dan setter untuk atribut-atribut bank
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public ArrayList<Nasabah> getNasabah() {
        return nasabah;
    }

    public void setNasabah(ArrayList<Nasabah> nasabah) {
        this.nasabah = nasabah;
    }

    // Method untuk menambahkan nasabah baru ke bank
    public void tambahNasabah(Nasabah nasabah) {
        this.nasabah.add(nasabah); // Menambahkan objek Nasabah ke ArrayList nasabah
    }

    // Method untuk mencari nasabah berdasarkan nomor rekening
    public Nasabah cariNasabah(String noRekening) {
        for (Nasabah n : nasabah) { // Looping untuk setiap nasabah di bank
            if (n.getNoRekening().equals(noRekening)) { // Jika nomor rekening sesuai
                return n; // Mengembalikan objek Nasabah yang dicari
            }
        }
        return null; // Mengembalikan null jika tidak ditemukan
    }

    public boolean loginAdmin(String username, String password) {
        for (Akun admin : listadmin) {
            if (admin.login(username, password)) {
                return true;
            }
        }
        return false;
    }
}