import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class musteri extends JFrame {
    final int x = 60;
    final int w = 100;
    final int h = 25;




    private static String mus_bakiye;
    public musteri() {
        JFrame muscrcv = new JFrame("Müşteri Bilgileri");
        JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
        l1 = new JLabel("Müşteri ID:");
        l1.setBounds(x, 50, w, h);
        l2 = new JLabel("Ad Soyad:");
        l2.setBounds(x, 90, w, h);
        l3 = new JLabel("Cinsiyet:");
        l3.setBounds(x, 130, w, h);
        l4 = new JLabel("Telefon No:");
        l4.setBounds(x, 170, w, h);
        l5 = new JLabel("Adres:");
        l5.setBounds(x + 230, 50, w, h);
        l6 = new JLabel("Şehir:");
        l6.setBounds(x + 230, 130, w, h);
        l7 = new JLabel("Bakiye:");
        l7.setBounds(x + 230, 170, w, h);
        l8 = new JLabel("Sorgu için");
        l8.setBounds(x + 530, 263, w + 20, h);
        l9 = new JLabel("ad soyad giriniz:");
        l9.setBounds(x + 530, 278, w + 20, h);

        JTextField mid, madsoyad, mbakiye, mtelno, msehir, madsoyadsorgu;
        mid = new JTextField();
        mid.setBounds(x + 80, 50, w + 10, h);
        madsoyad = new JTextField();
        madsoyad.setBounds(x + 80, 90, w + 10, h);
        mtelno = new JTextField();
        mtelno.setBounds(x + 80, 170, w + 10, h);
        msehir = new JTextField();
        msehir.setBounds(x + 290, 130, w + 10, h);
        mbakiye = new JTextField();
        mbakiye.setBounds(x + 290, 170, w + 10, h);
        madsoyadsorgu = new JTextField();
        madsoyadsorgu.setBounds(590, 310, w + 20, h);

        String[] cinsiyet = {"Erkek", "Kadın", "Diğer"};
        JComboBox mcinsiyet = new JComboBox(cinsiyet);
        mcinsiyet.setBounds(x + 80, 130, w + 10, h);

        JTextArea madres = new JTextArea();
        madres.setBounds(x + 290, 50, w + 80, h + 35);


        JButton mkaydet, mguncelle, mkayitsil, mlistele;
        mkaydet = new JButton("Kaydet");
        mkaydet.setBounds(590, 103, w + 20, h + 13);
        mkaydet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mus_id, mus_adsoyad, mus_cinsiyet2, mus_telno, mus_adres, mus_sehir, mus_sqlsorgu;
                mus_id = mid.getText();
                mus_adsoyad = madsoyad.getText();
                mus_cinsiyet2 = String.valueOf(mcinsiyet.getSelectedItem());
                mus_telno = mtelno.getText();
                mus_adres = madres.getText();
                mus_sehir = msehir.getText();
                mus_bakiye = mbakiye.getText();
                mus_sqlsorgu = "INSERT INTO musteri (mus_id,mus_adsoyad,mus_cinsiyet,mus_telno,mus_adres,mus_sehir,mus_bakiye) " +
                        "VALUES(" + mus_id + ",'" + mus_adsoyad + "'," + "'" + mus_cinsiyet2 + "'," + "'" +
                        mus_telno + "'," + "'" + mus_adres + "'," + "'" + mus_sehir + "'," + "'" + mus_bakiye + "')";
                baglanti.ekle(mus_sqlsorgu);
            }
        });

        mguncelle = new JButton("Güncelle");
        mguncelle.setBounds(590, 157, w + 20, h + 13);
        mguncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mus_id, mus_adsoyad, mus_cinsiyet2, mus_telno, mus_adres, mus_sehir, mus_sqlsorgu;
                mus_id = mid.getText();
                mus_adsoyad = madsoyad.getText();
                mus_cinsiyet2 = String.valueOf(mcinsiyet.getSelectedItem());
                mus_telno = mtelno.getText();
                mus_adres = madres.getText();
                mus_sehir = msehir.getText();
                mus_bakiye = mbakiye.getText();
                mus_sqlsorgu = "UPDATE musteri SET mus_id=" + mus_id +
                        ",mus_adsoyad='" + mus_adsoyad +
                        "',mus_cinsiyet='" + mus_cinsiyet2 +
                        "',mus_telno='" + mus_telno +
                        "',mus_adres='" + mus_adres +
                        "',mus_sehir='" + mus_sehir +
                        "',mus_bakiye=" + mus_bakiye + " WHERE mus_id=" + mus_id;
                baglanti.guncelle(mus_sqlsorgu);
                mesaj.bilgi(muscrcv, "MÜŞTERİ BİLGİLERİ GÜNCELLENDİ.");
            }
        });

        mkayitsil = new JButton("Kayıt Sil");
        mkayitsil.setBounds(590, 210, w + 20, h + 13);
        mkayitsil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mus_id, mus_sqlsorgu;
                mus_id = mid.getText();
                // DELETE FROM ogrenci WHERE
                mus_sqlsorgu = "DELETE FROM musteri WHERE mus_id=" + mus_id;
                baglanti.sil(mus_sqlsorgu);
                mesaj.uyarı(muscrcv, "MÜŞTERİ SİLİNDİ");
            }
        });


        DefaultTableModel mmodelim = new DefaultTableModel();
        Object[] mkolonlar = {"Müşteri ID", "Adı Soyadı", "Cinsiyet", "Telefon No", "Adres",
                "Şehir", "Bakiye"};
        Object[] msatirlar = new Object[7];
        mmodelim.setColumnIdentifiers(mkolonlar);


        JTable mliste = new JTable();
        mliste.setBounds(x, 230, 500, 240);
        JScrollPane msp = new JScrollPane(mliste);
        msp.setBounds(x, 230, 500, 240);


        mlistele = new JButton("Listele");
        mlistele.setBounds(590, 50, w + 20, h + 13);
        mlistele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mmodelim.setRowCount(0);
                String sqlsorgu = "SELECT * FROM musteri";

                ResultSet myRs = baglanti.yap(sqlsorgu);
                try {
                    while (myRs.next()) {
                        msatirlar[0] = myRs.getString("mus_id");
                        msatirlar[1] = myRs.getString("mus_adsoyad");
                        msatirlar[2] = myRs.getString("mus_cinsiyet");
                        msatirlar[3] = myRs.getString("mus_telno");
                        msatirlar[4] = myRs.getString("mus_adres");
                        msatirlar[5] = myRs.getString("mus_sehir");
                        msatirlar[6] = myRs.getString("mus_bakiye");
                        mmodelim.addRow(msatirlar);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                mliste.setModel(mmodelim);
            }
        });

        mliste.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mid.setText(String.valueOf(mmodelim.getValueAt(mliste.getSelectedRow(), 0)));
                madsoyad.setText(String.valueOf(mmodelim.getValueAt(mliste.getSelectedRow(), 1)));
                mcinsiyet.setSelectedItem(String.valueOf(mmodelim.getValueAt(mliste.getSelectedRow(), 2)));
                mtelno.setText(String.valueOf(mmodelim.getValueAt(mliste.getSelectedRow(), 3)));
                madres.setText(String.valueOf(mmodelim.getValueAt(mliste.getSelectedRow(), 4)));
                msehir.setText(String.valueOf(mmodelim.getValueAt(mliste.getSelectedRow(), 5)));
                mbakiye.setText(String.valueOf(mmodelim.getValueAt(mliste.getSelectedRow(), 6)));
            }
        });


        JButton msorgula = new JButton("Sorgula");
        msorgula.setBounds(590, 350, w + 20, h + 13);
        msorgula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mmodelim.setRowCount(0);
                String ad = madsoyadsorgu.getText();
                ResultSet myRs = null;
                String sqlsorgu = "select * from musteri where mus_adsoyad like'" + ad + "%'";
                myRs = baglanti.sorgula(sqlsorgu);
                try {
                    while (myRs.next()) {
                        msatirlar[0] = myRs.getString("mus_id");
                        msatirlar[1] = myRs.getString("mus_adsoyad");
                        msatirlar[2] = myRs.getString("mus_cinsiyet");
                        msatirlar[3] = myRs.getString("mus_telno");
                        msatirlar[4] = myRs.getString("mus_adres");
                        msatirlar[5] = myRs.getString("mus_sehir");
                        msatirlar[6] = myRs.getString("mus_bakiye");
                        mmodelim.addRow(msatirlar);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                mliste.setModel(mmodelim);
            }
        });


        muscrcv.add(l1);
        muscrcv.add(l2);
        muscrcv.add(l3);
        muscrcv.add(l4);
        muscrcv.add(l5);
        muscrcv.add(l6);
        muscrcv.add(l7);
        muscrcv.add(l8);
        muscrcv.add(l9);
        muscrcv.add(mid);
        muscrcv.add(madsoyad);
        muscrcv.add(mtelno);
        muscrcv.add(msehir);
        muscrcv.add(mbakiye);
        muscrcv.add(mcinsiyet);
        muscrcv.add(madsoyadsorgu);
        muscrcv.add(madres);
        muscrcv.add(msp);
        muscrcv.add(mkaydet);
        muscrcv.add(mguncelle);
        muscrcv.add(mkayitsil);
        muscrcv.add(mlistele);
        muscrcv.add(msorgula);
        muscrcv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        muscrcv.setResizable(false);
        muscrcv.setSize(780, 550);
        muscrcv.setLayout(null);
        muscrcv.setVisible(true);
    }
}
