import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

public class giris extends JFrame {

    static String kullaniciadi;
    static String sifre;
    static ResultSet myRs = null;


    public static String adsoyad;


    public static String getadsoyad() {
        return adsoyad;
    }


    public giris() {
        JFrame grscrcve = new JFrame("Giriş");

        JLabel l1, l2, l3;
        l1 = new JLabel("Kullanıcı adı:");
        l1.setBounds(70, 75, 100, 25);
        l2 = new JLabel("Şifre:");
        l2.setBounds(70, 115, 100, 25);
        l3 = new JLabel("STOK TAKİP OTOMASYONUNA HOŞGELDİNİZ ");
        l3.setBounds(57, 30, 300, 20);

        JTextField tf1 = new JTextField();
        tf1.setBounds(170, 75, 120, 25);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(170, 115, 120, 25);

        JButton btn1, btn2;
        btn1 = new JButton("Yönetici Giriş");
        btn1.setBounds(40, 167, 130, 30);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kullaniciadi = tf1.getText();
                sifre = String.valueOf(pass.getPassword());

                String sqlsorgu = "select count(per_id) as giris ,per_adsoyad from personel where per_kullaniciadi='"
                        + kullaniciadi + "' and per_sifre='" + sifre + "'and per_id=" + 1;
                myRs = baglanti.yap(sqlsorgu);
                myRs = baglanti.sorgula(sqlsorgu);
                try {
                    while (myRs.next()) {
                        if (myRs.getInt("giris") == 1) {
                            adsoyad = myRs.getString("per_adsoyad");
                            menu1 men1 = new menu1();
                            grscrcve.setVisible(false);
                        } else {
                            mesaj.hata(grscrcve, "KULLANICI ADI  veya ŞİFRE HATALI!");
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });
        btn2 = new JButton("Personel Giriş");
        btn2.setBounds(210, 167, 130, 30);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kullaniciadi = tf1.getText();
                sifre = String.valueOf(pass.getPassword());
                String sqlsorgu = "select count(per_id) as giris ,per_adsoyad from personel where per_kullaniciadi='"
                        + kullaniciadi + "' and per_sifre='" + sifre + "'";
                myRs = baglanti.yap(sqlsorgu);
                myRs = baglanti.sorgula(sqlsorgu);
                try {
                    while (myRs.next()) {
                        if (myRs.getInt("giris") == 1) {
                            adsoyad = myRs.getString("per_adsoyad");
                            menu2 men2 = new menu2();
                            grscrcve.setVisible(false);
                        } else {
                            mesaj.hata(grscrcve, "KULLANICI ADI  veya ŞİFRE HATALI");
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }

        });

        grscrcve.add(l1);
        grscrcve.add(l2);
        grscrcve.add(l3);
        grscrcve.add(tf1);
        grscrcve.add(pass);
        grscrcve.add(btn1);
        grscrcve.add(btn2);
        grscrcve.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        grscrcve.setResizable(false);
        grscrcve.setSize(400, 280);
        grscrcve.setLayout(null);
        grscrcve.setVisible(true);
    }

}
