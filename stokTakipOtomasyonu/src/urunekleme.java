import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class urunekleme extends JFrame {
    final int x = 60;
    final int w = 100;
    final int h = 25;


    static private String stok_adedi;


    public urunekleme() {
        JFrame uruncrcv = new JFrame("Ürün Ekleme");
        JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
        l1 = new JLabel("Ürün ID: ");
        l1.setBounds(x, 50, w, h);
        l2 = new JLabel("Ürün Adı: ");
        l2.setBounds(x + 275, 50, w, h);
        l3 = new JLabel("Ürün Kalitesi: ");
        l3.setBounds(x, 90, w, h);
        l4 = new JLabel("Ürün Boyutu: ");
        l4.setBounds(x, 130, w, h);
        l5 = new JLabel("Ürün Türü: ");
        l5.setBounds(x, 170, w, h);
        l6 = new JLabel("Ürün Alış Fiyatı: ");
        l6.setBounds(x + 275, 90, w, h);
        l7 = new JLabel("Ürün Satış Fiyatı: ");
        l7.setBounds(x + 275, 130, w, h);
        l8 = new JLabel("Stok adedi: ");
        l8.setBounds(x + 275, 170, w, h);
        l9 = new JLabel("Sorgu için ürün");
        l9.setBounds(x + 560, 263, w + 20, h);
        l10 = new JLabel("adını giriniz:");
        l10.setBounds(x + 560, 278, w + 20, h);

        JTextField uid, uadi, ualisfiyati, usatisfiyati, ustokadedi, uadsorgu;
        uid = new JTextField();
        uid.setBounds(x + 110, 50, w + 10, h);
        uadi = new JTextField();
        uadi.setBounds(x + 395, 50, w + 10, h);
        ualisfiyati = new JTextField();
        ualisfiyati.setBounds(x + 395, 90, w + 10, h);
        usatisfiyati = new JTextField();
        usatisfiyati.setBounds(x + 395, 130, w + 10, h);
        ustokadedi = new JTextField();
        ustokadedi.setBounds(x + 395, 170, w + 10, h);
        uadsorgu = new JTextField();
        uadsorgu.setBounds(620, 310, w + 20, h);

        String[] kalite = {"1.Kalite", "2.Kalite", "Eko"};
        String[] boyut = {"183x366", "210x280", "170x210", "122x280"};
        String[] tur = {"Suntalam", "Mdflam", "Ham Sunta", "Arkalık"};
        JComboBox ukalite, uboyutu, uturu;
        ukalite = new JComboBox(kalite);
        ukalite.setBounds(x + 110, 90, w + 10, h);
        uboyutu = new JComboBox(boyut);
        uboyutu.setBounds(x + 110, 130, w + 10, h);
        uturu = new JComboBox(tur);
        uturu.setBounds(x + 110, 170, w + 10, h);


        JButton ukaydet, uguncelle, ukayitsil, ulistele;
        ukaydet = new JButton("Kaydet");
        ukaydet.setBounds(620, 103, w + 20, h + 13);
        ukaydet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String urun_ıd, urun_adi, urun_kalitesi, urun_turu, urun_boyutu,
                        urun_alis_fiyati, urun_satis_fiyati, urun_sqlsorgu;
                urun_ıd = uid.getText();
                urun_adi = uadi.getText();
                urun_kalitesi = String.valueOf(ukalite.getSelectedItem());
                urun_turu = String.valueOf(uturu.getSelectedItem());
                urun_boyutu = String.valueOf(uboyutu.getSelectedItem());
                urun_alis_fiyati = ualisfiyati.getText();
                urun_satis_fiyati = usatisfiyati.getText();
                stok_adedi = ustokadedi.getText();

                urun_sqlsorgu = "INSERT INTO stok (urun_ıd,urun_adi,urun_kalitesi,urun_turu,urun_boyutu," +
                        "urun_alis_fiyati,urun_satis_fiyati,stok_adedi) " +
                        "VALUES(" + urun_ıd + ",'" + urun_adi + "'," + "'" + urun_kalitesi + "'," + "'" +
                        urun_turu + "'," + "'" + urun_boyutu + "'," + "'" + urun_alis_fiyati + "'," + "'" + urun_satis_fiyati + "'," +
                        "'" + stok_adedi + "')";

                baglanti.ekle(urun_sqlsorgu);
            }
        });

        uguncelle = new JButton("Güncelle");
        uguncelle.setBounds(620, 156, w + 20, h + 13);
        uguncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String urun_ıd, urun_adi, urun_kalitesi, urun_turu, urun_boyutu,
                        urun_alis_fiyati, urun_satis_fiyati, urun_sqlsorgu;
                urun_ıd = uid.getText();
                urun_adi = uadi.getText();
                urun_kalitesi = String.valueOf(ukalite.getSelectedItem());
                urun_turu = String.valueOf(uturu.getSelectedItem());
                urun_boyutu = String.valueOf(uboyutu.getSelectedItem());
                urun_alis_fiyati = ualisfiyati.getText();
                urun_satis_fiyati = usatisfiyati.getText();
                stok_adedi = ustokadedi.getText();
                urun_sqlsorgu = "UPDATE stok SET urun_ıd=" + urun_ıd +
                        ",urun_adi='" + urun_adi +
                        "',urun_kalitesi='" + urun_kalitesi +
                        "',urun_turu='" + urun_turu +
                        "',urun_boyutu='" + urun_boyutu +
                        "',urun_alis_fiyati='" + urun_alis_fiyati +
                        "',urun_satis_fiyati=" + urun_satis_fiyati +
                        ",stok_adedi='" + stok_adedi + "' WHERE urun_ıd=" + urun_ıd;
                //System.out.println(per_sqlsorgu);
                baglanti.guncelle(urun_sqlsorgu);
                mesaj.bilgi(uruncrcv, "ÜRÜN BİLGİLERİ GÜNCELLENDİ.");
            }
        });

        ukayitsil = new JButton("Kayıt Sil");
        ukayitsil.setBounds(620, 210, w + 20, h + 13);
        ukayitsil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String urun_ıd, per_sqlsorgu;
                urun_ıd = uid.getText();
                // DELETE FROM ogrenci WHERE
                per_sqlsorgu = "DELETE FROM stok WHERE urun_ıd=" + urun_ıd;
                baglanti.sil(per_sqlsorgu);
                mesaj.uyarı(uruncrcv, "ÜRÜN SİLİNDİ");
            }
        });


        DefaultTableModel umodelim = new DefaultTableModel();
        Object[] ukolonlar = {"Ürün ID", "Ürün Adı", "Ürün Kalitesi", "Ürün Türü", "Ürün Boyutu",
                "Ürün Alış Fiyatı", "Ürün Satış Fiyatı", "Stok adedi",};
        Object[] usatirlar = new Object[8];
        umodelim.setColumnIdentifiers(ukolonlar);


        JTable uliste = new JTable();
        uliste.setBounds(x, 250, 510, 220);
        JScrollPane usp = new JScrollPane(uliste);
        usp.setBounds(x, 250, 510, 220);
        ulistele = new JButton("Listele");
        ulistele.setBounds(620, 50, w + 20, h + 13);
        ulistele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                umodelim.setRowCount(0);
                String sqlsorgu = "SELECT * FROM stok";

                ResultSet myRs = baglanti.yap(sqlsorgu);
                try {
                    while (myRs.next()) {
                        usatirlar[0] = myRs.getString("urun_ıd");
                        usatirlar[1] = myRs.getString("urun_adi");
                        usatirlar[2] = myRs.getString("urun_kalitesi");
                        usatirlar[3] = myRs.getString("urun_turu");
                        usatirlar[4] = myRs.getString("urun_boyutu");
                        usatirlar[5] = myRs.getString("urun_alis_fiyati");
                        usatirlar[6] = myRs.getString("urun_satis_fiyati");
                        usatirlar[7] = myRs.getString("stok_adedi");
                        umodelim.addRow(usatirlar);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                uliste.setModel(umodelim);
            }
        });


        uliste.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                uid.setText(String.valueOf(umodelim.getValueAt(uliste.getSelectedRow(), 0)));
                uadi.setText(String.valueOf(umodelim.getValueAt(uliste.getSelectedRow(), 1)));
                ukalite.setSelectedItem(String.valueOf(umodelim.getValueAt(uliste.getSelectedRow(), 2)));
                uturu.setSelectedItem(String.valueOf(umodelim.getValueAt(uliste.getSelectedRow(), 3)));
                uboyutu.setSelectedItem(String.valueOf(umodelim.getValueAt(uliste.getSelectedRow(), 4)));
                ualisfiyati.setText(String.valueOf(umodelim.getValueAt(uliste.getSelectedRow(), 5)));
                usatisfiyati.setText(String.valueOf(umodelim.getValueAt(uliste.getSelectedRow(), 6)));
                ustokadedi.setText(String.valueOf(umodelim.getValueAt(uliste.getSelectedRow(), 7)));

            }
        });

        JButton usorgula = new JButton("Sorgula");
        usorgula.setBounds(620, 350, w + 20, h + 13);
        usorgula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                umodelim.setRowCount(0);
                String ad = uadsorgu.getText();
                ResultSet myRs = null;
                String sqlsorgu = "select * from stok where urun_adi like'" + ad + "%'";
                myRs = baglanti.sorgula(sqlsorgu);
                try {
                    while (myRs.next()) {
                        usatirlar[0] = myRs.getString("urun_ıd");
                        usatirlar[1] = myRs.getString("urun_adi");
                        usatirlar[2] = myRs.getString("urun_kalitesi");
                        usatirlar[3] = myRs.getString("urun_turu");
                        usatirlar[4] = myRs.getString("urun_boyutu");
                        usatirlar[5] = myRs.getString("urun_alis_fiyati");
                        usatirlar[6] = myRs.getString("urun_satis_fiyati");
                        usatirlar[7] = myRs.getString("stok_adedi");
                        umodelim.addRow(usatirlar);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                uliste.setModel(umodelim);
            }
        });

        uruncrcv.add(l1);
        uruncrcv.add(l2);
        uruncrcv.add(l3);
        uruncrcv.add(l4);
        uruncrcv.add(l5);
        uruncrcv.add(l6);
        uruncrcv.add(l7);
        uruncrcv.add(l8);
        uruncrcv.add(l9);
        uruncrcv.add(l10);
        uruncrcv.add(ukalite);
        uruncrcv.add(uboyutu);
        uruncrcv.add(uturu);
        uruncrcv.add(uid);
        uruncrcv.add(uadi);
        uruncrcv.add(ualisfiyati);
        uruncrcv.add(usatisfiyati);
        uruncrcv.add(ustokadedi);
        uruncrcv.add(uadsorgu);
        uruncrcv.add(ukaydet);
        uruncrcv.add(uguncelle);
        uruncrcv.add(ukayitsil);
        uruncrcv.add(ulistele);
        uruncrcv.add(usorgula);
        uruncrcv.add(usp);
        uruncrcv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        uruncrcv.setResizable(false);
        uruncrcv.setSize(800, 575);
        uruncrcv.setLayout(null);
        uruncrcv.setVisible(true);
    }

}
