import java.sql.*;

public class baglanti {
    static Connection myConn;
    static Statement myStat;
    static ResultSet myRs = null;

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/stoktakip?serverTimezone=UTC";
        String user = "root";
        String pass = "LHNkrtmn42";
        try {
            myConn = DriverManager.getConnection(url, user, pass);
            myStat = myConn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static ResultSet yap(String sqlkod) {
        try {
            String url = "jdbc:mysql://localhost:3306/stoktakip?serverTimezone=UTC";
            String user = "root";
            String pass = "LHNkrtmn42";
            myConn = DriverManager.getConnection(url, user, pass);
            myStat = myConn.createStatement();
            myRs = myStat.executeQuery(sqlkod);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myRs;
    }

    static void ekle(String sqlkod) {
        try {
            myStat.executeUpdate(sqlkod);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void guncelle(String sqlkod) {
        try {
            myStat.executeUpdate(sqlkod);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void sil(String sqlkod) {
        try {
            myStat.executeUpdate(sqlkod);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static ResultSet sorgula(String sqlkod) {
        ResultSet myRs = null;
        try {
            myRs = myStat.executeQuery(sqlkod);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myRs;
    }
}


