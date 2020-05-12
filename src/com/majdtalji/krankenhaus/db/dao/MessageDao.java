package com.majdtalji.krankenhaus.db.dao;

import com.majdtalji.krankenhaus.db.vo.MessageVo;
import com.majdtalji.krankenhaus.db.vo.PatientInfoVo;
import com.majdtalji.krankenhaus.db.vo.UsersVo;
import com.majdtalji.krankenhaus.validation.Validation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class MessageDao extends Dao implements DaoList<MessageVo> {

    // MESSAGES  ID MESSAGE_BODY	MESSAGE_DATE	FROM_USER	TO_USER	PATIENT_ID
    private static MessageDao messageDao;

    private MessageDao() {

    }

    public static MessageDao getInstance() {
        if (messageDao == null) {
            messageDao = new MessageDao();
        }
        return messageDao;
    }

    @Override
    public List<MessageVo> loadAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(MessageVo mv) throws Exception {

        // MESSAGES  ID MESSAGE_BODY  MESSAGE_DATE  FROM_USER  TO_USER  PATIENT_ID
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            con = getConnection();
            String sql = " INSERT INTO MESSAGES (MESSAGE_BODY, MESSAGE_DATE, FROM_USER, TO_USER, PATIENT_ID) VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, mv.getMessegeBody());
            ps.setDate(2, mv.getMessageDate());
            ps.setInt(3, mv.getFromUser().getId());
            ps.setInt(4, mv.getToUser().getId());
            ps.setInt(5, mv.getPatientInfoVo().getId());

            count = ps.executeUpdate();

        } catch (Exception e) {
            Validation.specialAlertShow("Error", "Insert Message Error. \n" + e.getMessage(), Alert.AlertType.ERROR);
        } finally {
            ps.close();
            closeConnection(con);
        }
        return count;

    }

    @Override
    public int update(MessageVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(MessageVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageVo getData(MessageVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageVo getDataById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MessageVo getDataByPatientIdAndUserId(int patientId, int from) throws Exception {

        // MESSAGES  ID MESSAGE_BODY  MESSAGE_DATE  FROM_USER  TO_USER  PATIENT_ID
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MessageVo messageVo = null;
        UsersVo fromUser = null;
        UsersVo toUser = null;
        PatientInfoVo patientInfoVo = null;
        try {
            con = getConnection();
            String sql = " SELECT ID, MESSAGE_BODY, MESSAGE_DATE, FROM_USER, TO_USER, PATIENT_ID FROM messages WHERE TO_USER=? AND PATIENT_ID=? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, from);
            ps.setInt(2, patientId);
            rs = ps.executeQuery();

            while (rs.next()) {

                messageVo = new MessageVo();
                messageVo.setId(rs.getInt("ID"));
                messageVo.setMessegeBody(rs.getString("MESSAGE_BODY"));
                messageVo.setMessageDate(rs.getDate("MESSAGE_DATE"));
                fromUser = new UsersVo();
                fromUser.setId(rs.getInt("FROM_USER"));
                messageVo.setFromUser(fromUser);
                toUser = new UsersVo();
                toUser.setId(rs.getInt("TO_USER"));
                messageVo.setToUser(toUser);
                patientInfoVo = new PatientInfoVo();
                patientInfoVo.setId(rs.getInt("PATIENT_ID"));
                messageVo.setPatientInfoVo(patientInfoVo);
            }

        } catch (SQLException e) {
        } finally {
            rs.close();
            ps.close();
            closeConnection(con);
        }
        return messageVo;

    }

}
