package com.majdtalji.krankenhaus.db.dao;

import com.majdtalji.krankenhaus.db.vo.MessageVo;
import com.majdtalji.krankenhaus.db.vo.PatientInfoVo;
import com.majdtalji.krankenhaus.db.vo.UsersVo;
import com.majdtalji.krankenhaus.validation.Validation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Dao {

    final static String DB_NAME = "jdbc:mysql://localhost/krankenhaus_system";

    final static String ENCODING = "?useUnicode=yes&characterEncoding=UTF-8";

    final static String DB_NAME_WITH_ENCODING = DB_NAME + ENCODING;

    final static String USER = "root";

    final static String PASSWORD = "";

    public Connection getConnection() {

        try {
            Connection con = DriverManager.getConnection(DB_NAME_WITH_ENCODING, USER, PASSWORD);

            if (con != null) {
                return con;
            }

        } catch (Exception e) {
            Validation.specialAlertShow("Error", "Can not Database connection.", Alert.AlertType.ERROR);
        }
        return null;

    }

    public void closeConnection(Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }

    public static int getId(String tableName) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dao database = new Dao();
        int id = 1;
        try {
            con = database.getConnection();
            String sql = "SELECT MAX(ID) + 1 FROM " + tableName;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
                if (id == 0) {
                    id = 1;
                }
            }

        } catch (Exception e) {
            Validation.specialAlertShow("Error", "Can not Database get ID.", Alert.AlertType.ERROR);
        } finally {
            try {
                rs.close();
                ps.close();
                database.closeConnection(con);
            } catch (Exception ex) {
            }
        }
        return id;

    }

}
