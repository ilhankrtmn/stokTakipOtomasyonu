import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Siparisler extends JFrame {
    final int x = 60;
    final int w = 100;
    final int h = 25;
    public Siparisler(){
        JFrame sipariscrcv = new JFrame("SİPARİŞ LİSTESİ");

        JLabel l1 = new JLabel("GELECEK SİPARİŞLER LİSTESİ");
        l1.setBounds(x, 41, 250, 20);

        DefaultTableModel modelim = new DefaultTableModel();
        Object[] ukolonlar = {"Sipariş ID", "Ürün ID", "Ürün Adı", "Sipariş Adedi"};
        Object[] usatirlar = new Object[4];
        modelim.setColumnIdentifiers(ukolonlar);


        JTable uliste = new JTable();
        uliste.setBounds(x, 80, 300, 220);
        JScrollPane usp = new JScrollPane(uliste);
        usp.setBounds(x, 80, 300, 220);
        JButton ulistele = new JButton("Listele");
        ulistele.setBounds(260, 40, w , h);
        ulistele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelim.setRowCount(0);
                String sqlsorgu = "SELECT * FROM siparis";

                ResultSet myRs = Baglanti.yap(sqlsorgu);
                try {
                    while (myRs.next()) {
                        usatirlar[0] = myRs.getString("siparis_id");
                        usatirlar[1] = myRs.getString("urun_id");
                        usatirlar[2] = myRs.getString("urun_adi");
                        usatirlar[3] = myRs.getString("siparis_adedi");
                        modelim.addRow(usatirlar);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                uliste.setModel(modelim);
            }
        });

        sipariscrcv.add(l1);
        sipariscrcv.add(ulistele);
        sipariscrcv.add(usp);
        sipariscrcv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        sipariscrcv.setResizable(false);
        sipariscrcv.setSize(450, 390);
        sipariscrcv.setLayout(null);
        sipariscrcv.setVisible(true);
    }
}
