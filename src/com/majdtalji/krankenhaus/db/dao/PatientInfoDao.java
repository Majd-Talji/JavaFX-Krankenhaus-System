package com.majdtalji.krankenhaus.db.dao;

import com.majdtalji.krankenhaus.db.vo.PatientInfoVo;
import com.majdtalji.krankenhaus.db.vo.UsersVo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class PatientInfoDao extends Dao implements DaoList<PatientInfoVo> {

    //  ID	FIRST_NAME	FATHER_NAME	MOBILE	EMAIL	USER_ID
    private static PatientInfoDao patientInfoDao;

    private PatientInfoDao() {

    }

    public static PatientInfoDao getInstance() {
        if (patientInfoDao == null) {
            patientInfoDao = new PatientInfoDao();
        }
        return patientInfoDao;
    }

    @Override
    public List<PatientInfoVo> loadAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(PatientInfoVo piv) throws Exception {
        // ID	FIRST_NAME	FATHER_NAME	MOBILE	EMAIL	USER_ID
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            con = getConnection();
            String userSql = " INSERT INTO patient_info (ID,FIRST_NAME,FATHER_NAME,MOBILE,EMAIL,USER_ID) VALUES (?,?,?,?,?,?) ";
            ps = con.prepareStatement(userSql);
            ps.setInt(1, piv.getId());
            ps.setString(2, piv.getFirstName());
            ps.setString(3, piv.getFatheName());
            ps.setString(4, piv.getMobile());
            ps.setString(5, piv.getEmail());
            ps.setInt(6, piv.getUsersVo().getId());
            count = ps.executeUpdate();

        } catch (SQLException e) {
        } finally {
            ps.close();
            closeConnection(con);
        }
        return count;

    }

    @Override
    public int update(PatientInfoVo piv) throws Exception {

        // // ID	FIRST_NAME	FATHER_NAME	MOBILE	EMAIL	USER_ID
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            con = getConnection();
            String sql = " UPDATE patient_info SET FIRST_NAME=?,FATHER_NAME=?,MOBILE=?,EMAIL=?,USER_ID=? WHERE ID=? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, piv.getFirstName());
            ps.setString(2, piv.getFatheName());
            ps.setString(3, piv.getMobile());
            ps.setString(4, piv.getEmail());
            ps.setInt(5, piv.getUsersVo().getId());
            ps.setInt(6, piv.getId());
            count = ps.executeUpdate();

        } catch (SQLException e) {
            con.rollback();
        } finally {
            ps.close();
            closeConnection(con);
        }
        return count;

    }

    @Override
    public int delete(PatientInfoVo piv) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            con = getConnection();
            String sql = " DELETE FROM patient_info WHERE ID=? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, piv.getId());
            count = ps.executeUpdate();

        } catch (SQLException e) {
        } finally {
            ps.close();
            closeConnection(con);
        }
        return count;

    }

    @Override
    public PatientInfoVo getData(PatientInfoVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PatientInfoVo getDataById(int id) throws Exception {

        // // ID	FIRST_NAME	FATHER_NAME	MOBILE	EMAIL	USER_ID
        Connection con = null;
        PatientInfoVo patientInfoVo = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM patient_info WHERE ID=?";
            PreparedStatement ps = con.prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                patientInfoVo = new PatientInfoVo();
                patientInfoVo.setId(rs.getInt("ID"));
                patientInfoVo.setFirstName(rs.getString("FIRST_NAME"));
                patientInfoVo.setFatheName(rs.getString("FATHER_NAME"));
                patientInfoVo.setMobile(rs.getString("MOBILE"));
                patientInfoVo.setEmail(rs.getString("EMAIL"));

                UsersVo usersVo = new UsersVo();
                usersVo.setId(rs.getInt("USER_ID"));
                patientInfoVo.setUsersVo(usersVo);

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        } finally {
            closeConnection(con);
        }
        return patientInfoVo;

    }
}
