import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Siparis extends JFrame {
    String sqlsorgu;
    YardimciKodlar kod = new YardimciKodlar();
    public Siparis(){
        final int x = 60;
        final int w = 120;
        final int h = 25;
        JFrame sipariscrcv = new JFrame("SİPARİŞ EKRANI");


        JLabel l1, l2, l3,l4;
        l1 = new JLabel("Ürün ID: ");
        l1.setBounds(x, 90, w, h);
        l2 = new JLabel("Ürün Adı: ");
        l2.setBounds(x , 50, w, h);
        l4 = new JLabel("Mevcut Stok: ");
        l4.setBounds(x , 130, w, h);
        l3 = new JLabel("Sipariş Adedi: ");
        l3.setBounds(x, 170, w, h);


        JComboBox uadi;
        uadi = new JComboBox();
        uadi.setBounds(x + 110, 50, w + 10, h);

        JTextField uid, siparisadedi,mevcutstok;
        uid = new JTextField();
        uid.setBounds(x + 110, 90, w + 10, h);
        uid.setEditable(false);
        mevcutstok = new JTextField();
        mevcutstok.setBounds(x + 110, 130, w + 10, h);
        mevcutstok.setEditable(false);
        siparisadedi = new JTextField();
        siparisadedi.setBounds(x + 110, 170, w + 10, h);


        String stokadet = kod.getStok_adedi(uid.getText());

        uadi.removeAllItems();
        ResultSet myRs2 = null;
        String sqlsorgu2 = "select urun_adi from stok";
        myRs2 = Baglanti.sorgula(sqlsorgu2);
        try {
            while (myRs2.next()) {

                uadi.addItem(myRs2.getString("urun_adi"));

            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }



        uadi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sqlsorgu2 = "select urun_ıd from stok where urun_adi='" + uadi.getSelectedItem()+"'";
                ResultSet myRs2 = null;
                myRs2 = Baglanti.sorgula(sqlsorgu2);

                try {
                    while (myRs2.next()) {

                        uid.setText(myRs2.getString("urun_ıd"));

                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                ResultSet myRs = null;
                String sqlsorgu1 = "select stok_adedi from stok where urun_adi='" + uadi.getSelectedItem()+"'";
                myRs = Baglanti.sorgula(sqlsorgu1);
                try {
                    while (myRs.next()) {
                        mevcutstok.setText(myRs.getString("stok_adedi"));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });



        JButton siparisver = new JButton("Sipariş Ver");
        siparisver.setBounds(55, 220, w-20, h + 13);
        siparisver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sqlsorgu2;
                String stokadet = kod.getStok_adedi(uid.getText());
                Integer stokson = Integer.parseInt(stokadet)+Integer.parseInt(siparisadedi.getText());
                sqlsorgu ="Update stok SET stok_adedi='"+stokson.toString()+"' where urun_ıd="+uid.getText();
                Baglanti.guncelle(sqlsorgu);
                sqlsorgu2 = "INSERT INTO siparis (urun_id,urun_adi,siparis_adedi) values" +
                        " ("+uid.getText()+",'"+uadi.getSelectedItem()+"',"+siparisadedi.getText()+")";
                Baglanti.ekle(sqlsorgu2);
                Mesaj.bilgi(sipariscrcv, "Sipariş oluşturuldu.");
            }
        });

        JButton siparisListele = new JButton("Siparişleri Listele");
        siparisListele.setBounds(160, 220, w+20, h + 13);
        siparisListele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Siparisler sprsler = new Siparisler();
            }
        });




        sipariscrcv.add(l1);
        sipariscrcv.add(l2);
        sipariscrcv.add(l3);
        sipariscrcv.add(l4);
        sipariscrcv.add(uid);
        sipariscrcv.add(uadi);
        sipariscrcv.add(siparisadedi);
        sipariscrcv.add(mevcutstok);
        sipariscrcv.add(siparisver);
        sipariscrcv.add(siparisListele);
        sipariscrcv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        sipariscrcv.setResizable(false);
        sipariscrcv.setSize(380, 350);
        sipariscrcv.setLayout(null);
        sipariscrcv.setVisible(true);
    }
}
