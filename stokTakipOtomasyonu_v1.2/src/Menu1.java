import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu1 extends JFrame {



    public Menu1() {
        final int x = 60;
        final int w = 150;
        final int h = 48;



        JFrame menu1crcv = new JFrame("YÖNETİCİ EKRANI");



        JLabel l1 = new JLabel("Hoşgeldiniz: "+ Giris.getadsoyad());
        l1.setBounds(x+100, 30, 250, 20);


        JButton menu1perekle, menu1musekle, menu1urunekle,menu1satisyap, menu1siparisver, menu1ayarlar, menu1cikis;

        menu1perekle = new JButton("Personel Ekle");
        menu1perekle.setBounds(x, 80, w, h);
        menu1perekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personel perekle = new Personel();
            }
        });
        menu1musekle = new JButton("Müşteri Ekle");
        menu1musekle.setBounds(x + 200, 80, w, h);
        menu1musekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Musteri musekle = new Musteri();
            }
        });
        menu1urunekle = new JButton("Ürün Ekle");
        menu1urunekle.setBounds(x, 160, w, h);
        menu1urunekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UrunEkleme urunekle1 = new UrunEkleme();
            }
        });


        menu1satisyap = new JButton("Satış Yap");
        menu1satisyap.setBounds(x+200, 160, w, h);
        menu1satisyap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Satis sts = new Satis();
                sts.satis2();
            }
        });

        menu1siparisver = new JButton("Sipariş Ver");
        menu1siparisver.setBounds(x, 240, w, h);
        menu1siparisver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Siparis sprs = new Siparis();
            }
        });


        menu1ayarlar = new JButton("Ayarlar");
        menu1ayarlar.setBounds(x + 200, 240, w, h);
        menu1ayarlar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ayarlar ayar = new Ayarlar();
                ayar.ayarlarGui();
            }
        });

        menu1crcv.add(l1);

        menu1crcv.add(menu1perekle);
        menu1crcv.add(menu1musekle);
        menu1crcv.add(menu1urunekle);
        menu1crcv.add(menu1satisyap);
        menu1crcv.add(menu1siparisver);
        menu1crcv.add(menu1ayarlar);

        menu1crcv.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu1crcv.setResizable(false);
        menu1crcv.setSize(480, 400);
        menu1crcv.setLayout(null);
        menu1crcv.setVisible(true);
    }
}
