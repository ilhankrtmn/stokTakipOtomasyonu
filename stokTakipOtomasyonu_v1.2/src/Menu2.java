import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu2 extends JFrame {
    final int x = 60;
    final int w = 150;
    final int h = 48;



    public Menu2() {
        JFrame menu2crcv = new JFrame("PERSONEL EKRANI");

        JLabel l1 = new JLabel("Hoşgeldiniz: " + Giris.getadsoyad());
        l1.setBounds(x + 100, 30, 250, 20);

        JButton menu2musekle, menu2urunekle, menu2satis, menu2sifreguncelle;

        menu2musekle = new JButton("Müşteri ekle");
        menu2musekle.setBounds(x, 80, w, h);
        menu2musekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Musteri musekle2 = new Musteri();
            }
        });
        menu2urunekle = new JButton("Ürün ekle");
        menu2urunekle.setBounds(x + 200, 80, w, h);
        menu2urunekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UrunEkleme urnekle2 = new UrunEkleme();
            }
        });
        menu2satis = new JButton("Satış Yap");
        menu2satis.setBounds(x, 160, w, h);
        menu2satis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Satis sts = new Satis();
                sts.satis2();
            }
        });
        menu2sifreguncelle = new JButton("Şifre Değiştir");
        menu2sifreguncelle.setBounds(x + 200, 160, w, h);
        menu2sifreguncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SifreDegistir pncere = new SifreDegistir();
                pncere.sifredegis();
            }
        });




        menu2crcv.add(l1);
        menu2crcv.add(menu2musekle);
        menu2crcv.add(menu2urunekle);
        menu2crcv.add(menu2satis);
        menu2crcv.add(menu2sifreguncelle);

        menu2crcv.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu2crcv.setResizable(false);
        menu2crcv.setSize(480, 300);
        menu2crcv.setLayout(null);
        menu2crcv.setVisible(true);
    }

}
