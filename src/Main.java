// Class Main sebagai class yang berisi method main untuk menjalankan program
public class Main {

    public static void main(String[] args) {

        // Membuat objek Bank dengan nama Bank Alfabes
        Bank bank = new Bank("Firman");

        bank.tambahAdmin("admin", "admin");

        // Tambahkan nasabah langsung di dalam main
        Nasabah n1 = new Nasabah("n1", "n1", "John Doe", "Jl. Contoh No. 123", "4321", 1000.0);
        Nasabah n2 = new Nasabah("n2", "n2", "Andika", "Jl. Contoh No. 123", "6321", 3000.0);
        Nasabah n3 = new Nasabah("n3", "n3", "Firman Aulia Wahid", "Jl. Contoh No. 123", "060299", 5000000.0);

        bank.tambahNasabah(n1);
        bank.tambahNasabah(n2);
        bank.tambahNasabah(n3);

        // Membuat objek Aplikasi dengan parameter objek Bank
        Aplikasi aplikasi = new Aplikasi(bank);

        // Menjalankan aplikasi dengan metode run
        aplikasi.run();
    }
}
