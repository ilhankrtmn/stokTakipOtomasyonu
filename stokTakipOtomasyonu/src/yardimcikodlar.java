import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class yardimcikodlar {

    private static String mus_bakiye;

    public String getMus_bakiye(String musadsoyad) {
        String sqlsorgu = "select mus_bakiye from musteri where" +
                " mus_adsoyad='" + musadsoyad + "'";
        ResultSet myRs = baglanti.sorgula(sqlsorgu);
        try {
            while (myRs.next()) {
                mus_bakiye = myRs.getString("mus_bakiye");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return this.mus_bakiye;
    }

    public void setMus_bakiye(String musadsoyad, String musbakiye) {
        String sqlsorgu = "UPDATE musteri SET mus_bakiye='"
                + musbakiye + "' WHERE mus_adsoyad='" + musadsoyad + "'";
        baglanti.guncelle(sqlsorgu);
    }

    static private String stok_adedi;

    public String getStok_adedi(String urunid) {
        String sqlsorgu = "SELECT stok_adedi FROM stok where urun_ıd='" + urunid + "'";
        ResultSet myRs = baglanti.sorgula(sqlsorgu);
        try {
            while (myRs.next()) {
                stok_adedi = myRs.getString("stok_adedi");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return this.stok_adedi;
    }

    public void setStok_adedi(String urunid, String netstokadedi) {
        String sqlsorgu = "UPDATE stok SET stok_adedi='" + netstokadedi + "' WHERE urun_ıd='" + urunid + "'";
        baglanti.guncelle(sqlsorgu);
    }


    public static String progbas() {
        return "PROGRAM BAŞLADI";
    }

}
