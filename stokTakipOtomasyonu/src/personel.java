import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class personel extends JFrame {
    final int x = 60;
    final int w = 100;
    final int h = 25;

    public personel() {
        JFrame percrcv = new JFrame("Personel Bilgileri");

        JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
        l1 = new JLabel("Personel ID:");
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
        l7 = new JLabel("Maaş:");
        l7.setBounds(x + 230, 170, w, h);
        l8 = new JLabel("Kullanıcı Adı:");
        l8.setBounds(x, 210, w, h);
        l9 = new JLabel("Şifre:");
        l9.setBounds(x + 230, 210, w, h);
        l10 = new JLabel("Sorgu için");
        l10.setBounds(x + 540, 263, w+20, h);
        l11 = new JLabel("ad soyad giriniz:");
        l11.setBounds(x + 540, 278, w+20, h);


        JTextField pid, padsoyad, pmaas, ptelno, psehir, pkullaniciadi,padsoyadsorgu;
        pid = new JTextField();
        pid.setBounds(x + 80, 50, w + 10, h);
        padsoyad = new JTextField();
        padsoyad.setBounds(x + 80, 90, w + 10, h);
        pmaas = new JTextField();
        pmaas.setBounds(x + 290, 170, w + 10, h);
        ptelno = new JTextField();
        ptelno.setBounds(x + 80, 170, w + 10, h);
        psehir = new JTextField();
        psehir.setBounds(x + 290, 130, w + 10, h);
        pkullaniciadi = new JTextField();
        pkullaniciadi.setBounds(x + 80, 210, w + 10, h);
        padsoyadsorgu = new JTextField();
        padsoyadsorgu.setBounds(600, 310, w + 20, h);

        JPasswordField psifre = new JPasswordField();
        psifre.setBounds(x + 290, 210, w + 10, h);

        String[] cinsiyet = {"Erkek", "Kadın", "Diğer"};
        JComboBox pcinsiyet = new JComboBox(cinsiyet);
        pcinsiyet.setBounds(x + 80, 130, w + 10, h);

        JTextArea padres = new JTextArea();
        padres.setBounds(x + 290, 50, w + 80, h + 35);


        JButton pkaydet, pguncelle, pkayitsil, plistele;
        pkaydet = new JButton("Kaydet");
        pkaydet.setBounds(600, 103, w + 20, h + 13);
        pkaydet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String per_id, per_adsoyad, per_cinsiyet2, per_telno, per_adres, per_sehir, per_maas, per_kullaniciadi, per_sifre, per_sqlsorgu;
                per_id = pid.getText();
                per_adsoyad = padsoyad.getText();
                per_cinsiyet2 = String.valueOf(pcinsiyet.getSelectedItem());
                per_telno = ptelno.getText();
                per_adres = padres.getText();
                per_sehir = psehir.getText();
                per_maas = pmaas.getText();
                per_kullaniciadi = pkullaniciadi.getText();
                per_sifre = String.valueOf(psifre.getPassword());
                per_sqlsorgu = "INSERT INTO personel (per_id,per_adsoyad,per_cinsiyet,per_telno,per_adres,per_sehir,per_maas,per_kullaniciadi,per_sifre) " +
                        "VALUES(" + per_id + ",'" + per_adsoyad + "'," + "'" + per_cinsiyet2 + "'," + "'" +
                        per_telno + "'," + "'" + per_adres + "'," + "'" + per_sehir + "'," + "'" + per_maas + "'," +
                        "'" + per_kullaniciadi + "'," + "'" + per_sifre + "')";

                baglanti.ekle(per_sqlsorgu);
            }
        });


        pguncelle = new JButton("Güncelle");
        pguncelle.setBounds(600, 156, w + 20, h + 13);
        pguncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String per_id, per_adsoyad, per_cinsiyet2, per_telno, per_adres, per_sehir, per_maas, per_kullaniciadi, per_sifre, per_sqlsorgu;
                per_id = pid.getText();
                per_adsoyad = padsoyad.getText();
                per_cinsiyet2 = String.valueOf(pcinsiyet.getSelectedItem());
                per_telno = ptelno.getText();
                per_adres = padres.getText();
                per_sehir = psehir.getText();
                per_maas = pmaas.getText();
                per_kullaniciadi = pkullaniciadi.getText();
                per_sifre = String.valueOf(psifre.getPassword());
                per_sqlsorgu = "UPDATE personel SET per_id=" + per_id +
                        ",per_adsoyad='" + per_adsoyad +
                        "',per_cinsiyet='" + per_cinsiyet2 +
                        "',per_telno='" + per_telno +
                        "',per_adres='" + per_adres +
                        "',per_sehir='" + per_sehir +
                        "',per_maas=" + per_maas +
                        ",per_kullaniciadi='" + per_kullaniciadi +
                        "',per_sifre='" + per_sifre + "' WHERE per_id=" + per_id;

                baglanti.guncelle(per_sqlsorgu);
                mesaj.bilgi(percrcv,"PERSONEL BİLGİLERİ GÜNCELLENDİ.");
            }
        });

        pkayitsil = new JButton("Kayıt Sil");
        pkayitsil.setBounds(600, 210, w + 20, h + 13);
        pkayitsil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String per_id, per_sqlsorgu;
                per_id = pid.getText();
                // DELETE FROM ogrenci WHERE
                per_sqlsorgu = "DELETE FROM personel WHERE per_id=" + per_id;
                baglanti.sil(per_sqlsorgu);
                mesaj.uyarı(percrcv,"PERSONEL SİLİNDİ");
            }
        });


        DefaultTableModel pmodelim = new DefaultTableModel();
        Object[] pkolonlar = {"Personel ID", "Adı Soyadı", "Cinsiyet", "Telefon No", "Adres",
                "Şehir", "Bakiye", "Kullanıcı Adı", "Şifre"};
        Object[] psatirlar = new Object[9];
        pmodelim.setColumnIdentifiers(pkolonlar);


        JTable pliste = new JTable();
        pliste.setBounds(x, 270, 500, 240);
        JScrollPane psp = new JScrollPane(pliste);
        psp.setBounds(x, 270, 500, 240);

        plistele = new JButton("Listele");
        plistele.setBounds(600, 50, w + 20, h + 13);
        plistele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pmodelim.setRowCount(0);
                String sqlsorgu = "SELECT * FROM personel";

                ResultSet myRs = baglanti.yap(sqlsorgu);
                try {
                    while (myRs.next()) {
                        psatirlar[0] = myRs.getString("per_id");
                        psatirlar[1] = myRs.getString("per_adsoyad");
                        psatirlar[2] = myRs.getString("per_cinsiyet");
                        psatirlar[3] = myRs.getString("per_telno");
                        psatirlar[4] = myRs.getString("per_adres");
                        psatirlar[5] = myRs.getString("per_sehir");
                        psatirlar[6] = myRs.getString("per_maas");
                        psatirlar[7] = myRs.getString("per_kullaniciadi");
                        psatirlar[8] = myRs.getString("per_sifre");
                        pmodelim.addRow(psatirlar);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                pliste.setModel(pmodelim);
            }
        });

        pliste.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pid.setText(String.valueOf(pmodelim.getValueAt(pliste.getSelectedRow(), 0)));
                padsoyad.setText(String.valueOf(pmodelim.getValueAt(pliste.getSelectedRow(), 1)));
                pcinsiyet.setSelectedItem(String.valueOf(pmodelim.getValueAt(pliste.getSelectedRow(), 2)));
                ptelno.setText(String.valueOf(pmodelim.getValueAt(pliste.getSelectedRow(), 3)));
                padres.setText(String.valueOf(pmodelim.getValueAt(pliste.getSelectedRow(), 4)));
                psehir.setText(String.valueOf(pmodelim.getValueAt(pliste.getSelectedRow(), 5)));
                pmaas.setText(String.valueOf(pmodelim.getValueAt(pliste.getSelectedRow(), 6)));
                pkullaniciadi.setText(String.valueOf(pmodelim.getValueAt(pliste.getSelectedRow(), 7)));
                psifre.setText(String.valueOf(pmodelim.getValueAt(pliste.getSelectedRow(), 8)));
            }
        });


        JButton psorgula = new JButton("Sorgula");
        psorgula.setBounds(600, 350, w + 20, h + 13);
        psorgula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pmodelim.setRowCount(0);
                String ad = padsoyadsorgu.getText();
                ResultSet myRs = null;
                String sqlsorgu = "select * from personel where per_adsoyad like'" + ad + "%'";
                myRs = baglanti.sorgula(sqlsorgu);
                try {
                    while (myRs.next()) {
                        psatirlar[0] = myRs.getString("per_id");
                        psatirlar[1] = myRs.getString("per_adsoyad");
                        psatirlar[2] = myRs.getString("per_cinsiyet");
                        psatirlar[3] = myRs.getString("per_telno");
                        psatirlar[4] = myRs.getString("per_adres");
                        psatirlar[5] = myRs.getString("per_sehir");
                        psatirlar[6] = myRs.getString("per_maas");
                        psatirlar[7] = myRs.getString("per_kullaniciadi");
                        psatirlar[8] = myRs.getString("per_sifre");
                        pmodelim.addRow(psatirlar);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                pliste.setModel(pmodelim);
            }
        });

        percrcv.add(l1);
        percrcv.add(l2);
        percrcv.add(l3);
        percrcv.add(l4);
        percrcv.add(l5);
        percrcv.add(l6);
        percrcv.add(l7);
        percrcv.add(l8);
        percrcv.add(l9);
        percrcv.add(l10);
        percrcv.add(l11);
        percrcv.add(pid);
        percrcv.add(padsoyad);
        percrcv.add(ptelno);
        percrcv.add(pmaas);
        percrcv.add(psehir);
        percrcv.add(padsoyadsorgu);
        percrcv.add(pcinsiyet);
        percrcv.add(pkullaniciadi);
        percrcv.add(psifre);
        percrcv.add(padres);
        percrcv.add(psp);
        percrcv.add(pkaydet);
        percrcv.add(pguncelle);
        percrcv.add(pkayitsil);
        percrcv.add(plistele);
        percrcv.add(psorgula);
        percrcv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        percrcv.setResizable(false);
        percrcv.setSize(790, 600);
        percrcv.setLayout(null);
        percrcv.setVisible(true);
    }

}
