import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sifredegistir extends ayarlar {

    public void sifredegis() {
        JFrame sfrcrcv = new JFrame("Şifre Değiştirme");

        JLabel l1, l2, l3, l4;
        l1 = new JLabel("Eski Şifre:");
        l1.setBounds(x, 50, w, h);
        l2 = new JLabel("Yeni Şifre:");
        l2.setBounds(x, 95, w, h);
        l3 = new JLabel("Yeni Şifreyi");
        l3.setBounds(x, 140, w, h);
        l4 = new JLabel("Tekrar Gir:");
        l4.setBounds(x, 158, w, h);

        JTextField peskisifre = new JTextField();
        peskisifre.setBounds(x + 85, 50, w + 10, h);

        JPasswordField pyenisifre1, pyenisifre2;
        pyenisifre1 = new JPasswordField();
        pyenisifre1.setBounds(x + 85, 97, w + 10, h);
        pyenisifre2 = new JPasswordField();
        pyenisifre2.setBounds(x + 85, 148, w + 10, h);

        JButton pdegistir;
        pdegistir = new JButton("Değiştir");
        pdegistir.setBounds(x + 23, 210, w + 49, h + 15);
        pdegistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eskisif = peskisifre.getText().toString();
                yenisif1 = String.valueOf(pyenisifre1.getPassword());
                yenisif2 = String.valueOf(pyenisifre2.getPassword());

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
                        mesaj.hata(sfrcrcv, "YENİ ŞİFRELER BOŞ OLAMAZ!");
                    } else if (yenisif1.equals(yenisif2)) {
                        String sqlsorgu2 = "UPDATE personel SET per_sifre='" + yenisif1
                                + "' WHERE per_kullaniciadi='" + kullaniciadi + "'";
                        baglanti.guncelle(sqlsorgu2);

                    } else {
                        mesaj.hata(sfrcrcv, "GİRİLEN YENİ ŞİFRELER AYNI DEĞİL!");
                    }
                } else {
                    mesaj.hata(sfrcrcv, "GİRDİĞİNİZ ESKİ ŞİFRE YANLIŞTIR!");
                }

            }

        });

        sfrcrcv.add(l1);
        sfrcrcv.add(l2);
        sfrcrcv.add(l3);
        sfrcrcv.add(l4);
        sfrcrcv.add(peskisifre);
        sfrcrcv.add(pyenisifre1);
        sfrcrcv.add(pyenisifre2);
        sfrcrcv.add(pdegistir);
        sfrcrcv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        sfrcrcv.setResizable(false);
        sfrcrcv.setSize(330, 320);
        sfrcrcv.setLayout(null);
        sfrcrcv.setVisible(true);


    }


}
