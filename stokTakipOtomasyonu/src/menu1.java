import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class menu1 extends JFrame {



    public menu1() {
        final int x = 60;
        final int w = 150;
        final int h = 48;



        JFrame menu1crcv = new JFrame("YÖNETİCİ EKRANI");



        JLabel l1 = new JLabel("Hoşgeldiniz: "+giris.getadsoyad());
        l1.setBounds(x+100, 30, 250, 20);


        JButton menu1perekle, menu1musekle, menu1urunekle, menu1rapor, menu1ayarlar, menu1cikis;
        menu1perekle = new JButton("Personel ekle");
        menu1perekle.setBounds(x, 80, w, h);
        menu1perekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personel perekle = new personel();
            }
        });
        menu1musekle = new JButton("Müşteri ekle");
        menu1musekle.setBounds(x + 200, 80, w, h);
        menu1musekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musteri musekle = new musteri();
            }
        });
        menu1urunekle = new JButton("Ürün ekle");
        menu1urunekle.setBounds(x, 160, w, h);
        menu1urunekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urunekleme urunekle1 = new urunekleme();
            }
        });


        menu1ayarlar = new JButton("Ayarlar");
        menu1ayarlar.setBounds(x + 200, 160, w, h);
        menu1ayarlar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ayarlar ayar = new ayarlar();
                ayar.ayarlarGui();
            }
        });

        menu1crcv.add(l1);
        menu1crcv.add(menu1perekle);
        menu1crcv.add(menu1musekle);
        menu1crcv.add(menu1urunekle);
        menu1crcv.add(menu1ayarlar);
        menu1crcv.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu1crcv.setResizable(false);
        menu1crcv.setSize(480, 300);
        menu1crcv.setLayout(null);
        menu1crcv.setVisible(true);
    }
}
