// Class Aplikasi sebagai class yang merepresentasikan aplikasi bank minimalis
// Mengimplementasikan konsep polimorfisme

import java.util.Scanner;

class Aplikasi {
    // Atribut untuk aplikasi
    public Bank bank; // Mengimplementasikan konsep asosiasi
    public Akun akun; // Mengimplementasikan konsep asosiasi
    public Scanner input; // Mengimplementasikan konsep komposisi

    // Konstruktor untuk class Aplikasi
    public Aplikasi(Bank bank) {
        this.bank = bank;
        this.akun = null;
        this.input = new Scanner(System.in); // Membuat objek Scanner baru
    }

    // Method untuk menampilkan menu utama
    public void tampilMenuUtama() {
        System.out.println("Selamat datang di aplikasi bank " + bank.getNama());
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("1. Login sebagai admin");
        System.out.println("2. Login sebagai nasabah");
        System.out.println("3. Keluar");
        System.out.print("Masukkan pilihan Anda: ");
    }

    // Method untuk menampilkan menu admin
    public void tampilMenuAdmin() {
        System.out.println("Anda login sebagai admin");
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("1. Lihat info nasabah");
        System.out.println("2. Tambah nasabah");
        System.out.println("3. Logout");
        System.out.print("Masukkan pilihan Anda: ");
    }

    // Method untuk menampilkan menu nasabah
    public void tampilMenuNasabah() {
        System.out.println("Anda login sebagai nasabah");
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("1. Lihat detail transaksi");
        System.out.println("2. Lihat riwayat transaksi");
        System.out.println("3. Simpan uang");
        System.out.println("4. Tarik uang");
        System.out.println("5. Transfer uang");
        System.out.println("6. Logout");
        System.out.print("Masukkan pilihan Anda: ");
    }

    // Method untuk memproses input dari user
    public void prosesInput(int pilihan) {
        switch (pilihan) {
            case 1: // Jika pilihan 1
                if (akun == null) { // Jika belum login
                    loginAdmin(); // Memanggil method loginAdmin()
                } else if (akun instanceof Admin) { // Jika sudah login sebagai admin
                    lihatInfoNasabah(); // Memanggil method lihatInfoNasabah()
                } else if (akun instanceof Nasabah) { // Jika sudah login sebagai nasabah
                    lihatDetailTransaksi(); // Memanggil method lihatDetailTransaksi()
                }
                break;
            case 2: // Jika pilihan 2
                if (akun == null) { // Jika belum login
                    loginNasabah(); // Memanggil method loginNasabah()
                } else if (akun instanceof Admin) { // Jika sudah login sebagai admin
                    tambahNasabah(); // Memanggil method tambahNasabah()
                } else if (akun instanceof Nasabah) { // Jika sudah login sebagai nasabah
                    lihatRiwayatTransaksi(); // Memanggil method lihatRiwayatTransaksi()
                }
                break;
            case 3: // Jika pilihan 3
                if (akun == null) { // Jika belum login
                    keluar(); // Memanggil method keluar()
                } else if (akun instanceof Admin) { // Jika sudah login sebagai admin
                    logout(); // Memanggil method logout()
                } else if (akun instanceof Nasabah) { // Jika sudah login sebagai nasabah
                    simpanUang(); // Memanggil method simpanUang()
                }
                break;
            case 4: // Jika pilihan 4
                if (akun instanceof Nasabah) { // Jika sudah login sebagai nasabah
                    tarikUang(); // Memanggil method tarikUang()
                } else { // Jika belum login atau login sebagai admin
                    System.out.println("Pilihan tidak valid.");
                }
                break;
            case 5: // Jika pilihan 5
                if (akun instanceof Nasabah) { // Jika sudah login sebagai nasabah
                    transferUang(); // Memanggil method transferUang()
                } else { // Jika belum login atau login sebagai admin
                    System.out.println("Pilihan tidak valid.");
                }
                break;
            case 6: // Jika pilihan 6
                if (akun instanceof Nasabah) { // Jika sudah login sebagai nasabah
                    logout(); // Memanggil method logout()
                } else { // Jika belum login atau login sebagai admin
                    System.out.println("Pilihan tidak valid.");
                }
                break;
            default: // Jika pilihan lainnya
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }

    // Method untuk login sebagai admin
    public void loginAdmin() {
        System.out.print("Masukkan username admin: ");
        String username = input.next(); // Membaca input username
        System.out.print("Masukkan password admin: ");
        String password = input.next(); // Membaca input password
        // Mengecek apakah input username dan password sesuai dengan admin yang sudah
        // dibuat
        if (bank.loginAdmin(username, password)) {
            System.out.println("Login berhasil sebagai admin.");
            akun = new Admin(username, password); // Mengeset akun dengan objek Admin yang sesuai
        } else {
            System.out.println("Login gagal. Username atau password admin salah.");
        }
    }

    // Method untuk login sebagai nasabah
    public void loginNasabah() {
        System.out.print("Masukkan nomor rekening: ");
        String noRekening = input.next(); // Membaca input nomor rekening
        System.out.print("Masukkan password: ");
        String password = input.next(); // Membaca input password
        Nasabah n = bank.cariNasabah(noRekening); // Mencari nasabah berdasarkan nomor rekening
        if (n != null && n.login(noRekening, password)) { // Jika nasabah ditemukan dan username dan password sesuai
            akun = n; // Mengeset akun dengan objek Nasabah yang ditemukan
            System.out.println("Login berhasil.");
        } else { // Jika nasabah tidak ditemukan atau username atau password tidak sesuai
            System.out.println("Login gagal. Nomor rekening atau password salah.");
        }
    }

    // Method untuk logout dari akun
    public void logout() {
        akun = null; // Mengeset akun dengan null
        System.out.println("Logout berhasil.");
    }

    // Method untuk keluar dari aplikasi
    public void keluar() {
        System.out.println("Terima kasih telah menggunakan aplikasi bank minimalis " + bank.getNama());
        System.exit(0); // Menghentikan program
    }

    // Method untuk menampilkan info nasabah (hanya bisa dilakukan oleh admin)
    public void lihatInfoNasabah() {
        if (akun instanceof Admin) { // Jika akun adalah admin
            Admin a = (Admin) akun; // Melakukan type casting dari Akun ke Admin
            a.lihatInfoNasabah(bank); // Memanggil method lihatInfoNasabah() dari class Admin
        } else { // Jika akun bukan admin
            System.out.println("Anda tidak memiliki akses untuk melihat info nasabah.");
        }
    }

    // Method untuk menambahkan nasabah baru (hanya bisa dilakukan oleh admin)
    // Method untuk menambahkan nasabah baru ke bank
    public void tambahNasabah() {
        if (akun instanceof Admin) { // Jika akun adalah admin
            Admin a = (Admin) akun; // Melakukan type casting dari Akun ke Admin
            System.out.print("Masukkan nama nasabah: ");
            String nama = input.next(); // Membaca input nama

            // Menambahkan input.nextLine() untuk mengonsumsi karakter newline
            input.nextLine();

            System.out.print("Masukkan alamat nasabah: ");
            String alamat = input.nextLine(); // Membaca input alamat

            System.out.print("Masukkan nomor rekening nasabah: ");
            String noRekening = input.next(); // Membaca input nomor rekening

            System.out.print("Masukkan saldo awal nasabah: ");
            double saldo = input.nextDouble(); // Membaca input saldo

            System.out.print("Masukkan password nasabah: ");
            String password = input.next(); // Membaca input password

            Nasabah n = new Nasabah(noRekening, password, nama, alamat, noRekening, saldo); // Membuat objek Nasabah
                                                                                            // baru
            a.tambahNasabah(bank, n); // Memanggil method tambahNasabah() dari class Admin
        } else { // Jika akun bukan admin
            System.out.println("Anda tidak memiliki akses untuk menambahkan nasabah.");
        }
    }

    // Method untuk menampilkan detail transaksi terakhir (hanya bisa dilakukan oleh
    // nasabah
    public void lihatDetailTransaksi() {
        if (akun instanceof Nasabah) { // Jika akun adalah nasabah
            Nasabah n = (Nasabah) akun; // Melakukan type casting dari Akun ke Nasabah
            n.lihatDetailTransaksi(); // Memanggil method lihatDetailTransaksi() dari class Nasabah
        } else { // Jika akun bukan nasabah
            System.out.println("Anda tidak memiliki akses untuk melihat detail transaksi.");
        }
    }

    // Method untuk menampilkan riwayat transaksi (hanya bisa dilakukan oleh
    // nasabah
    public void lihatRiwayatTransaksi() {
        if (akun instanceof Nasabah) { // Jika akun adalah nasabah
            Nasabah n = (Nasabah) akun; // Melakukan type casting dari Akun ke Nasabah
            n.lihatRiwayatTransaksi(); // Memanggil method lihatRiwayatTransaksi() dari class Nasabah
        } else { // Jika akun bukan nasabah
            System.out.println("Anda tidak memiliki akses untuk melihat riwayat transaksi.");
        }
    }

    // Method untuk melakukan simpan uang (hanya bisa dilakukan oleh nasabah)
    public void simpanUang() {
        if (akun instanceof Nasabah) { // Jika akun adalah nasabah
            Nasabah n = (Nasabah) akun; // Melakukan type casting dari Akun ke Nasabah
            System.out.print("Masukkan jumlah uang yang ingin disimpan: ");
            double jumlah = input.nextDouble(); // Membaca input jumlah uang
            n.simpanUang(jumlah); // Memanggil method simpanUang() dari class Nasabah
        } else { // Jika akun bukan nasabah
            System.out.println("Anda tidak memiliki akses untuk menyimpan uang.");
        }
    }

    // Method untuk melakukan tarik uang (hanya bisa dilakukan oleh nasabah)
    public void tarikUang() {
        if (akun instanceof Nasabah) { // Jika akun adalah nasabah
            Nasabah n = (Nasabah) akun; // Melakukan type casting dari Akun ke Nasabah
            System.out.print("Masukkan jumlah uang yang ingin ditarik: ");
            double jumlah = input.nextDouble(); // Membaca input jumlah uang
            n.tarikUang(jumlah); // Memanggil method tarikUang() dari class Nasabah
        } else { // Jika akun bukan nasabah
            System.out.println("Anda tidak memiliki akses untuk menarik uang.");
        }
    }

    // Method untuk melakukan transfer uang (hanya bisa dilakukan oleh nasabah)
    public void transferUang() {
        if (akun instanceof Nasabah) { // Jika akun adalah nasabah
            Nasabah n = (Nasabah) akun; // Melakukan type casting dari Akun ke Nasabah
            System.out.print("Masukkan nomor rekening penerima: ");
            String noRekening = input.next(); // Membaca input nomor rekening penerima
            Nasabah p = bank.cariNasabah(noRekening); // Mencari nasabah penerima berdasarkan nomor rekening
            if (p != null) { // Jika nasabah penerima ditemukan
                System.out.print("Masukkan jumlah uang yang ingin ditransfer: ");
                double jumlah = input.nextDouble(); // Membaca input jumlah uang
                n.transferUang(p, jumlah); // Memanggil method transferUang() dari class Nasabah
            } else { // Jika nasabah penerima tidak ditemukan
                System.out.println("Nomor rekening penerima tidak valid.");
            }
        } else { // Jika akun bukan nasabah
            System.out.println("Anda tidak memiliki akses untuk mentransfer uang.");
        }
    }

    // Method untuk menjalankan aplikasi
    public void run() {
        int pilihan;
        do {
            if (akun == null) { // Jika belum login
                tampilMenuUtama(); // Menampilkan menu utama
            } else if (akun instanceof Admin) { // Jika sudah login sebagai admin
                tampilMenuAdmin(); // Menampilkan menu admin
            } else if (akun instanceof Nasabah) { // Jika sudah login sebagai nasabah
                tampilMenuNasabah(); // Menampilkan menu nasabah
            }
            pilihan = input.nextInt(); // Membaca input pilihan
            prosesInput(pilihan); // Memproses input pilihan
        } while (pilihan != 3 || akun != null); // Looping selama pilihan bukan 3 atau akun tidak null
    }
}