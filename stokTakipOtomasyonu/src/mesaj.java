import java.util.*;
import javax.swing.*;

public abstract class mesaj extends yardimcikodlar{
    static void hata(JFrame cerceveadi, String mesaj) {
        JOptionPane.showMessageDialog(cerceveadi, mesaj, "HATA !", JOptionPane.ERROR_MESSAGE);
    }

    static void soru(JFrame cerceveadi, String mesaj) {
        JOptionPane.showMessageDialog(cerceveadi, mesaj, "", JOptionPane.QUESTION_MESSAGE);
    }

    static public void uyarı(JFrame cerceveadi, String mesaj){
        JOptionPane.showMessageDialog(cerceveadi, mesaj, "UYARI !", JOptionPane.WARNING_MESSAGE);
    }

    static void bilgi(JFrame cerceveadi, String mesaj) {
        JOptionPane.showMessageDialog(cerceveadi, mesaj, "", JOptionPane.INFORMATION_MESSAGE);
    }
}
