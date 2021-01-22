import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

public class ayarlar extends JFrame {
    final int x = 55;
    final int w = 100;
    final int h = 25;

    String kullaniciadi = giris.kullaniciadi;
    String eskisifre = null;
    String eskisif, yenisif1, yenisif2;


    public void ayarlarGui() {

        JFrame ayarcrcv = new JFrame("Ayarlar");

        JLabel l1, l2, l3, l4, l5, l6;
        l1 = new JLabel("BİLGİ SIFIRLAMA");
        l1.setBounds(x + 40, 50, w + 50, h);
        l2 = new JLabel("ŞİFRE DEĞİŞTİRME");
        l2.setBounds(x + 260, 50, w + 50, h);
        l3 = new JLabel("Eski Şifre:");
        l3.setBounds(x + 220, 100, w, h);
        l4 = new JLabel("Yeni Şifre:");
        l4.setBounds(x + 220, 145, w, h);
        l5 = new JLabel("Yeni Şifreyi");
        l5.setBounds(x + 220, 185, w, h);
        l6 = new JLabel("Tekrar Gir:");
        l6.setBounds(x + 220, 203, w, h);

        JCheckBox apersifirla, amussifirla, aurunsifirla, asatissifirla;
        apersifirla = new JCheckBox("Personel Bilgilerini Sıfırla");
        apersifirla.setBounds(x + 5, 100, w + 70, h);
        apersifirla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mesaj.uyarı(ayarcrcv, "SIFIRLA BUTONUNA BASINCA PERSONEL BİLGİLERİ SIFIRLANACAK");
            }
        });

        amussifirla = new JCheckBox("Müşteri Bilgilerini Sıfırla");
        amussifirla.setBounds(x + 5, 135, w + 70, h);
        amussifirla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mesaj.uyarı(ayarcrcv, "SIFIRLA BUTONUNA BASINCA MÜŞTERİ BİLGİLERİ SIFIRLANACAK");
            }
        });

        aurunsifirla = new JCheckBox("Ürün Bilgilerini Sıfırla");
        aurunsifirla.setBounds(x + 5, 170, w + 70, h);
        aurunsifirla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mesaj.uyarı(ayarcrcv, "SIFIRLA BUTONUNA BASINCA ÜRÜN BİLGİLERİ SIFIRLANACAK");
            }
        });
        asatissifirla = new JCheckBox("Satış Bilgilerini Sıfırla");
        asatissifirla.setBounds(x + 5, 205, w + 70, h);
        asatissifirla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mesaj.uyarı(ayarcrcv, "SIFIRLA BUTONUNA BASINCA SATIŞ BİLGİLERİ SIFIRLANACAK");
            }
        });

        JTextField aeskisifre = new JTextField();
        aeskisifre.setBounds(x + 295, 100, w, h);

        JPasswordField ayenisifre1, ayenisifre2;
        ayenisifre1 = new JPasswordField();
        ayenisifre1.setBounds(x + 295, 147, w, h);
        ayenisifre2 = new JPasswordField();
        ayenisifre2.setBounds(x + 295, 192, w, h);

        JButton asifirla, adegistir;
        asifirla = new JButton("Sıfırla");
        asifirla.setBounds(x + 11, 250, w + 49, h + 15);
        asifirla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (apersifirla.isSelected()) {
                    baglanti.sil("DELETE FROM personel");
                }
                if (amussifirla.isSelected()) {
                    baglanti.sil("DELETE FROM musteri");
                }
                if (aurunsifirla.isSelected()) {
                    baglanti.sil("DELETE FROM stok");
                }
                if (asatissifirla.isSelected()) {
                    baglanti.sil("DELETE FROM satis");
                }

            }
        });

        adegistir = new JButton("Değiştir");
        adegistir.setBounds(x + 233, 250, w + 49, h + 15);
        adegistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                eskisif = aeskisifre.getText().toString();
                yenisif1 = String.valueOf(ayenisifre1.getPassword());
                yenisif2 = String.valueOf(ayenisifre2.getPassword());

                String sqlsorgu = "select per_kullaniciadi, per_sifre from stoktakip.personel where per_kullaniciadi='" + kullaniciadi + "'";
                ResultSet myRs = baglanti.yap(sqlsorgu);
                try {
                    while (myRs.next()) {
                        eskisifre = myRs.getString("per_sifre");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                if (eskisifre.equals(eskisif)) {
                    if (yenisif1.length() == 0 && yenisif2.length() == 0) {
                        mesaj.hata(ayarcrcv, "YENİ ŞİFRELER BOŞ OLAMAZ!");
                    } else if (yenisif1.equals(yenisif2)) {
                        String sqlsorgu2 = "UPDATE personel SET per_sifre='" + yenisif1
                                + "' WHERE per_kullaniciadi='" + kullaniciadi + "'";
                        baglanti.guncelle(sqlsorgu2);
                    } else {
                        mesaj.hata(ayarcrcv, "GİRİLEN YENİ ŞİFRELER AYNI DEĞİL!");
                    }
                } else {
                    mesaj.hata(ayarcrcv, "GİRDİĞİNİZ ESKİ ŞİFRE YANLIŞTIR!");
                }

            }
        });

        ayarcrcv.add(l1);
        ayarcrcv.add(l2);
        ayarcrcv.add(l3);
        ayarcrcv.add(l4);
        ayarcrcv.add(l5);
        ayarcrcv.add(l6);
        ayarcrcv.add(apersifirla);
        ayarcrcv.add(amussifirla);
        ayarcrcv.add(aurunsifirla);
        ayarcrcv.add(asatissifirla);
        ayarcrcv.add(aeskisifre);
        ayarcrcv.add(ayenisifre1);
        ayarcrcv.add(ayenisifre2);
        ayarcrcv.add(asifirla);
        ayarcrcv.add(adegistir);
        ayarcrcv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ayarcrcv.setResizable(false);
        ayarcrcv.setSize(550, 400);
        ayarcrcv.setLayout(null);
        ayarcrcv.setVisible(true);
    }

}
