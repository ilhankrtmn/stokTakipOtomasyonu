import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class satis {
    final int x = 60;
    final int w = 120;
    final int h = 25;
    String eskitoplamtutar = null;
    String eskisatisadedi = null;
    static int toplamtutar = 0;
    static String satisfiyati = null;

    yardimcikodlar kod = new yardimcikodlar();
    public void satis2() {


        JFrame satiscrcv = new JFrame("Satış Ekranı");
        JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
        l1 = new JLabel("Ürün ID: ");
        l1.setBounds(x + 295, 50, w, h);
        l2 = new JLabel("Müşteri Ad Soyad: ");
        l2.setBounds(x, 90, w, h);
        l3 = new JLabel("Personel Ad Soyad: ");
        l3.setBounds(x, 130, w, h);
        l4 = new JLabel("Satış Tarihi: ");
        l4.setBounds(x, 170, w, h);
        l5 = new JLabel("Satış Adedi: ");
        l5.setBounds(x + 295, 90, w, h);
        l6 = new JLabel("Birim Satış Fiyatı: ");
        l6.setBounds(x + 295, 130, w, h);
        l7 = new JLabel("Toplam Tutar: ");
        l7.setBounds(x + 295, 170, w, h);
        l8 = new JLabel("Sorgu için");
        l8.setBounds(x + 560, 263, w + 20, h);
        l9 = new JLabel("satış no giriniz:");
        l9.setBounds(x + 560, 278, w + 20, h);
        l10 = new JLabel("Satış No: ");
        l10.setBounds(x, 50, w, h);

        JTextField uid, ssatistarihi, ssatisadedi, usatisfiyati, stoplamtutar, ssatisnosorgu, ssatisno;
        uid = new JTextField();
        uid.setBounds(x + 410, 50, w, h);
        ssatistarihi = new JTextField();
        ssatistarihi.setBounds(x + 128, 170, w + 10, h);
        ssatisadedi = new JTextField();
        ssatisadedi.setBounds(x + 410, 90, w, h);
        usatisfiyati = new JTextField();
        usatisfiyati.setBounds(x + 410, 130, w, h);
        usatisfiyati.setEditable(false);
        stoplamtutar = new JTextField();
        stoplamtutar.setBounds(x + 410, 170, w, h);
        stoplamtutar.setEditable(false);
        ssatisnosorgu = new JTextField();
        ssatisnosorgu.setBounds(620, 310, w, h);
        ssatisno = new JTextField();
        ssatisno.setBounds(x + 128, 50, w + 10, h);


        JComboBox madsoyad, padsoyad;
        madsoyad = new JComboBox();
        madsoyad.setBounds(x + 128, 90, w + 10, h);
        padsoyad = new JComboBox();
        padsoyad.setBounds(x + 128, 130, w + 10, h);

        uid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String no = uid.getText();
                ResultSet myRs = null;
                String sqlsorgu1 = "select urun_satis_fiyati from stok where urun_ıd=" + no;
                myRs = baglanti.sorgula(sqlsorgu1);
                try {
                    while (myRs.next()) {
                        usatisfiyati.setText(myRs.getString("urun_satis_fiyati"));
                        satisfiyati = usatisfiyati.getText();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        ssatisadedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toplamtutar = Integer.parseInt(satisfiyati) * Integer.parseInt(ssatisadedi.getText());
                stoplamtutar.setText(String.valueOf(toplamtutar));

            }
        });

        JButton ssatisyap = new JButton("Satış Yap");
        ssatisyap.setBounds(620, 103, w, h + 13);
        ssatisyap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String satis_id, urun_kodu, mus_adsoyad, per_adsoyad, satis_adet,
                        urun_satis_fiyati, toplam_tutar, satis_tarihi, ssqlsorgu;
                satis_id = ssatisno.getText();
                urun_kodu = uid.getText();
                mus_adsoyad = String.valueOf(madsoyad.getSelectedItem());
                per_adsoyad = String.valueOf(padsoyad.getSelectedItem());
                satis_adet = ssatisadedi.getText();
                urun_satis_fiyati = usatisfiyati.getText();
                toplam_tutar = stoplamtutar.getText();
                satis_tarihi = ssatistarihi.getText();

                ssqlsorgu = "INSERT INTO satis (satis_id,urun_kodu,mus_adsoyad,per_adsoyad,satis_adet," +
                        "urun_satis_fiyati,toplam_tutar,satis_tarihi) " +
                        "VALUES(" + satis_id + ",'" + urun_kodu + "'," + "'" + mus_adsoyad + "'," + "'" +
                        per_adsoyad + "'," + "'" + satis_adet + "'," + "'" + urun_satis_fiyati + "'," + "'" + toplam_tutar + "'," +
                        "'" + satis_tarihi + "')";

                String stokadet = kod.getStok_adedi(urun_kodu);
                String musbakiye = kod.getMus_bakiye(mus_adsoyad);
                if (Integer.parseInt(musbakiye) < Integer.parseInt(toplam_tutar) ||
                        Integer.parseInt(stokadet) < Integer.parseInt(satis_adet)) {
                    mesaj.uyarı(satiscrcv, "Müşteri Bakiyesi veya Stok Adedi Yetersiz !");
                } else {
                    int sonstok = Integer.parseInt(stokadet) - Integer.parseInt(satis_adet);
                    kod.setStok_adedi(urun_kodu, String.valueOf(sonstok));
                    int yenibakiye = Integer.parseInt(musbakiye) - Integer.parseInt(toplam_tutar);
                    kod.setMus_bakiye(mus_adsoyad, String.valueOf(yenibakiye));
                    baglanti.ekle(ssqlsorgu);
                }

            }
        });


        JButton ssatisdüzenle = new JButton("Satış Düzenle");
        ssatisdüzenle.setBounds(620, 157, w, h + 13);
        ssatisdüzenle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String satis_id, urun_kodu, mus_adsoyad, per_adsoyad, satis_adet,
                        urun_satis_fiyati, toplam_tutar, satis_tarihi, ssqlsorgu;
                satis_id = ssatisno.getText();
                urun_kodu = uid.getText();
                mus_adsoyad = String.valueOf(madsoyad.getSelectedItem());
                per_adsoyad = String.valueOf(padsoyad.getSelectedItem());
                satis_adet = ssatisadedi.getText();
                urun_satis_fiyati = usatisfiyati.getText();

                String sqlsorgu = "SELECT toplam_tutar FROM satis where satis_id=" + satis_id;
                ResultSet myRs = baglanti.sorgula(sqlsorgu);
                try {
                    while (myRs.next()) {
                        eskitoplamtutar = myRs.getString("toplam_tutar");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                String sqlsorgu2 = "SELECT satis_adet FROM satis where urun_kodu='" + urun_kodu + "' and satis_id='" + satis_id + "'";
                ResultSet myRs2 = baglanti.sorgula(sqlsorgu2);
                try {
                    while (myRs2.next()) {
                        eskisatisadedi = myRs2.getString("satis_adet");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                toplam_tutar = stoplamtutar.getText();
                int guncellenentutar = Integer.parseInt(toplam_tutar) - Integer.parseInt(eskitoplamtutar);
                int guncellenenstok = Integer.parseInt(satis_adet) - Integer.parseInt(eskisatisadedi);

                satis_tarihi = ssatistarihi.getText();
                ssqlsorgu = "UPDATE satis SET satis_id=" + satis_id +
                        ",urun_kodu='" + urun_kodu +
                        "',mus_adsoyad='" + mus_adsoyad +
                        "',per_adsoyad='" + per_adsoyad +
                        "',satis_adet='" + satis_adet +
                        "',urun_satis_fiyati='" + urun_satis_fiyati +
                        "',toplam_tutar=" + toplam_tutar +
                        ",satis_tarihi='" + satis_tarihi + "' WHERE satis_id=" + satis_id;


                String musbakiye = kod.getMus_bakiye(mus_adsoyad);
                int yenitutar = Integer.parseInt(musbakiye) - guncellenentutar;

                String stokadet = kod.getStok_adedi(urun_kodu);
                int yenistok = Integer.parseInt(stokadet) - guncellenenstok;

                if (Integer.parseInt(musbakiye) < Integer.parseInt(toplam_tutar) || Integer.parseInt(stokadet) < Integer.parseInt(satis_adet)) {
                    mesaj.uyarı(satiscrcv, "Müşteri Bakiyesi veya Stok Adedi Yetersiz !");
                } else {
                    kod.setStok_adedi(urun_kodu, String.valueOf(yenistok));
                    kod.setMus_bakiye(mus_adsoyad, String.valueOf(yenitutar));
                    mesaj.bilgi(satiscrcv, "SATIŞ BİLGİLERİ GÜNCELLENDİ.");
                    baglanti.guncelle(ssqlsorgu);
                }
            }
        });

        JButton ssatissil = new JButton("Satış Sil");
        ssatissil.setBounds(620, 210, w, h + 13);
        ssatissil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String satis_id, ssqlsorgu, toplamtutar, mus_adsoyad, urunkodu, satisadedi;
                satis_id = ssatisno.getText();
                toplamtutar = stoplamtutar.getText();
                urunkodu = uid.getText();
                satisadedi = ssatisadedi.getText();
                mus_adsoyad = String.valueOf(madsoyad.getSelectedItem());
                ssqlsorgu = "DELETE FROM satis WHERE satis_id=" + satis_id;
                baglanti.sil(ssqlsorgu);
                String stokadet = kod.getStok_adedi(urunkodu);
                int yenistok = Integer.parseInt(stokadet) + Integer.parseInt(satisadedi);
                kod.setStok_adedi(urunkodu, String.valueOf(yenistok));
                String musbakiye = kod.getMus_bakiye(mus_adsoyad);
                int yenitutar = Integer.parseInt(musbakiye) + Integer.parseInt(toplamtutar);
                kod.setMus_bakiye(mus_adsoyad, String.valueOf(yenitutar));
                mesaj.uyarı(satiscrcv, "ÜRÜN SİLİNDİ");
            }
        });


        DefaultTableModel smodelim = new DefaultTableModel();
        Object[] skolonlar = {"Satış No", "Ürün Kodu", "Müşteri Ad Soyad", "Personel Ad Soyad", "Satış Adeti",
                "Satış Fiyatı", "Toplam Tutar", "Satış Tarihi"};
        Object[] ssatirlar = new Object[8];
        smodelim.setColumnIdentifiers(skolonlar);


        JTable ssatistablo = new JTable();
        ssatistablo.setBounds(x, 230, 530, 240);
        JScrollPane ssp = new JScrollPane(ssatistablo);
        ssp.setBounds(x, 230, 530, 240);


        JButton slistele = new JButton("Listele");
        slistele.setBounds(620, 50, w, h + 13);
        slistele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smodelim.setRowCount(0);
                String sqlsorgu = "SELECT * FROM satis";

                ResultSet myRs = baglanti.yap(sqlsorgu);
                try {
                    while (myRs.next()) {
                        ssatirlar[0] = myRs.getString("satis_id");
                        ssatirlar[1] = myRs.getString("urun_kodu");
                        ssatirlar[2] = myRs.getString("mus_adsoyad");
                        ssatirlar[3] = myRs.getString("per_adsoyad");
                        ssatirlar[4] = myRs.getString("satis_adet");
                        ssatirlar[5] = myRs.getString("urun_satis_fiyati");
                        ssatirlar[6] = myRs.getString("toplam_tutar");
                        ssatirlar[7] = myRs.getString("satis_tarihi");
                        smodelim.addRow(ssatirlar);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                ssatistablo.setModel(smodelim);

                madsoyad.removeAllItems();
                ResultSet myRs2 = null;
                String sqlsorgu2 = "select mus_adsoyad from musteri";
                myRs2 = baglanti.sorgula(sqlsorgu2);
                try {
                    while (myRs2.next()) {

                        madsoyad.addItem(myRs2.getString("mus_adsoyad"));

                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                padsoyad.removeAllItems();
                ResultSet myRs3 = null;
                String sqlsorgu3 = "select per_adsoyad from personel";
                myRs3 = baglanti.sorgula(sqlsorgu3);
                try {
                    while (myRs3.next()) {
                        padsoyad.addItem(myRs3.getString("per_adsoyad"));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });

        ssatistablo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ssatisno.setText(String.valueOf(smodelim.getValueAt(ssatistablo.getSelectedRow(), 0)));
                uid.setText(String.valueOf(smodelim.getValueAt(ssatistablo.getSelectedRow(), 1)));
                madsoyad.setSelectedItem(String.valueOf(smodelim.getValueAt(ssatistablo.getSelectedRow(), 2)));
                padsoyad.setSelectedItem(String.valueOf(smodelim.getValueAt(ssatistablo.getSelectedRow(), 3)));
                ssatisadedi.setText(String.valueOf(smodelim.getValueAt(ssatistablo.getSelectedRow(), 4)));
                usatisfiyati.setText(String.valueOf(smodelim.getValueAt(ssatistablo.getSelectedRow(), 5)));
                stoplamtutar.setText(String.valueOf(smodelim.getValueAt(ssatistablo.getSelectedRow(), 6)));
                ssatistarihi.setText(String.valueOf(smodelim.getValueAt(ssatistablo.getSelectedRow(), 7)));
            }
        });


        JButton ssorgula = new JButton("Sorgula");
        ssorgula.setBounds(620, 350, w, h + 13);
        ssorgula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smodelim.setRowCount(0);
                String no = ssatisnosorgu.getText();
                ResultSet myRs = null;
                String sqlsorgu = "select * from satis where satis_id like'" + no + "%'";
                myRs = baglanti.sorgula(sqlsorgu);
                try {
                    while (myRs.next()) {
                        ssatirlar[0] = myRs.getString("satis_id");
                        ssatirlar[1] = myRs.getString("urun_kodu");
                        ssatirlar[2] = myRs.getString("mus_adsoyad");
                        ssatirlar[3] = myRs.getString("per_adsoyad");
                        ssatirlar[4] = myRs.getString("satis_adet");
                        ssatirlar[5] = myRs.getString("urun_satis_fiyati");
                        ssatirlar[6] = myRs.getString("toplam_tutar");
                        ssatirlar[7] = myRs.getString("satis_tarihi");
                        smodelim.addRow(ssatirlar);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                ssatistablo.setModel(smodelim);
            }
        });


        satiscrcv.add(l1);
        satiscrcv.add(l2);
        satiscrcv.add(l3);
        satiscrcv.add(l4);
        satiscrcv.add(l5);
        satiscrcv.add(l6);
        satiscrcv.add(l7);
        satiscrcv.add(l8);
        satiscrcv.add(l9);
        satiscrcv.add(l10);
        satiscrcv.add(uid);
        satiscrcv.add(ssatistarihi);
        satiscrcv.add(ssatisadedi);
        satiscrcv.add(usatisfiyati);
        satiscrcv.add(stoplamtutar);
        satiscrcv.add(ssatisnosorgu);
        satiscrcv.add(ssatisno);
        satiscrcv.add(madsoyad);
        satiscrcv.add(padsoyad);
        satiscrcv.add(ssatisyap);
        satiscrcv.add(ssatisdüzenle);
        satiscrcv.add(ssatissil);
        satiscrcv.add(slistele);
        satiscrcv.add(ssorgula);
        satiscrcv.add(ssp);
        satiscrcv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        satiscrcv.setResizable(false);
        satiscrcv.setSize(800, 600);
        satiscrcv.setLayout(null);
        satiscrcv.setVisible(true);
    }
}
