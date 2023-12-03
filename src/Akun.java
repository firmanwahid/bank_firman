import java.util.ArrayList;
class Akun {
    // Atribut bersama untuk admin dan nasabah
    protected String username;
    protected String password;

    // Konstruktor untuk class Akun
    public Akun(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Method untuk mengecek apakah username dan password sesuai
    public boolean login(String noRekening, String password) {
        return this.username.equals(noRekening) && this.password.equals(password);
    }
}

// Class Nasabah sebagai subclass dari class Akun
// Mengimplementasikan konsep inheritance
class Nasabah extends Akun {
    // Atribut khusus untuk nasabah
    private String nama;
    private String alamat;
    private String noRekening;
    private double saldo;
    private ArrayList<Transaksi> riwayat; // Mengimplementasikan konsep agregasi

    // Konstruktor untuk class Nasabah
    public Nasabah(String username, String password, String nama, String alamat, String noRekening, double saldo) {
        super(username, password); // Memanggil konstruktor superclass
        this.nama = nama;
        this.alamat = alamat;
        this.noRekening = noRekening;
        this.saldo = saldo;
        this.riwayat = new ArrayList<Transaksi>(); // Membuat objek ArrayList baru
    }

    // Getter dan setter untuk atribut-atribut nasabah
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Transaksi> getRiwayat() {
        return riwayat;
    }

    public void setRiwayat(ArrayList<Transaksi> riwayat) {
        this.riwayat = riwayat;
    }

    // Method untuk menampilkan detail transaksi terakhir
    public void lihatDetailTransaksi() {
        if (riwayat.isEmpty()) { // Jika riwayat kosong
            System.out.println("Anda belum melakukan transaksi apapun.");
        } else { // Jika riwayat ada
            Transaksi t = riwayat.get(riwayat.size() - 1); // Mengambil transaksi terakhir
            System.out.println("Detail transaksi terakhir Anda:");
            System.out.println(t); // Memanggil method toString() dari class Transaksi
        }
    }

    // Method untuk menampilkan riwayat transaksi
    public void lihatRiwayatTransaksi() {
        if (riwayat.isEmpty()) { // Jika riwayat kosong
            System.out.println("Anda belum melakukan transaksi apapun.");
        } else { // Jika riwayat ada
            System.out.println("Riwayat transaksi Anda:");
            for (Transaksi t : riwayat) {
                System.out.println(t); // Memanggil method toString() dari class Transaksi
            }
        }
    }

    // Method untuk menambahkan transaksi baru ke riwayat
    public void tambahTransaksi(Transaksi transaksi) {
        riwayat.add(transaksi); // Menambahkan objek Transaksi ke ArrayList riwayat
    }

    // Method untuk melakukan simpan/setor uang
    public void simpanUang(double jumlah) {
        saldo += jumlah; // Menambahkan saldo dengan jumlah uang yang disimpan
        Transaksi t = new Transaksi("Simpan", jumlah, saldo); // Membuat objek Transaksi baru
        tambahTransaksi(t); // Menambahkan transaksi baru ke riwayat
        System.out.println("Anda berhasil menyimpan uang sebesar Rp " + jumlah);
    }

    // Method untuk melakukan tarik uang
    public void tarikUang(double jumlah) {
        if (jumlah > saldo) { // Jika jumlah uang yang ditarik melebihi saldo
            System.out.println("Saldo Anda tidak cukup untuk melakukan penarikan.");
        } else { // Jika jumlah uang yang ditarik tidak melebihi saldo
            saldo -= jumlah; // Mengurangi saldo dengan jumlah uang yang ditarik
            Transaksi t = new Transaksi("Tarik", jumlah, saldo); // Membuat objek Transaksi baru
            tambahTransaksi(t); // Menambahkan transaksi baru ke riwayat
            System.out.println("Anda berhasil menarik uang sebesar Rp " + jumlah);
        }
    }

    // Method untuk melakukan transfer uang ke nasabah lain
    public void transferUang(Nasabah penerima, double jumlah) {
        if (jumlah > saldo) { // Jika jumlah uang yang ditransfer melebihi saldo
            System.out.println("Saldo Anda tidak cukup untuk melakukan transfer.");
        } else { // Jika jumlah uang yang ditransfer tidak melebihi saldo
            saldo -= jumlah; // Mengurangi saldo dengan jumlah uang yang ditransfer
            penerima.saldo += jumlah; // Menambahkan saldo penerima dengan jumlah uang yang ditransfer
            Transaksi t1 = new Transaksi("Transfer", jumlah, saldo, penerima); // Membuat objek Transaksi baru untuk
                                                                               // pengirim
            Transaksi t2 = new Transaksi("Terima", jumlah, penerima.saldo, this); // Membuat objek Transaksi baru untuk
                                                                                  // penerima
            tambahTransaksi(t1); // Menambahkan transaksi baru ke riwayat pengirim
            penerima.tambahTransaksi(t2); // Menambahkan transaksi baru ke riwayat penerima
            System.out.println("Anda berhasil mentransfer uang sebesar Rp " + jumlah + " ke " + penerima.nama);
        }
    }

    // Method untuk menampilkan informasi nasabah
    public String toString() {
        System.out.println(
                    "==========================================================================================================" +
                    "\n| No Rekening\t  | Nama\t\t | Alamat\t\t\t  | Saldo\t    | Username   |" +
                    "\n==========================================================================================================");
        return 
        String.format("| %-15s | %-20s | %-30s | %-15s | %-10s |",
                noRekening, nama, alamat, "Rp " + saldo, username);
    }

    public boolean login(String noRekening, String password) {
        return this.noRekening.equals(noRekening) && this.password.equals(password);
    }

}

// Class Admin sebagai subclass dari class Akun
// Mengimplementasikan konsep inheritance
class Admin extends Akun {
    // Konstruktor untuk class Admin
    public Admin(String username, String password) {
        super(username, password); // Memanggil konstruktor superclass
    }

    // Method untuk menampilkan seluruh info nasabah
    public void lihatInfoNasabah(Bank bank) {
        System.out.println("Daftar nasabah bank " + bank.getNama() + ":");
        for (Nasabah n : bank.getNasabah()) {
            System.out.println(n); // Memanggil method toString() dari class Nasabah
        }
    }

    // Method untuk menambahkan nasabah baru ke bank
    public void tambahNasabah(Bank bank, Nasabah nasabah) {
        bank.tambahNasabah(nasabah); // Memanggil method tambahNasabah() dari class Bank
        System.out.println("Nasabah baru berhasil ditambahkan.");
    }
    
}