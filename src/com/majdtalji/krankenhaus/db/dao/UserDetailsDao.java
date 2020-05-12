package com.majdtalji.krankenhaus.db.dao;

import com.majdtalji.krankenhaus.db.type.UsersType;
import com.majdtalji.krankenhaus.db.vo.UserDetailsVo;
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
public class UserDetailsDao extends com.majdtalji.krankenhaus.db.dao.Dao implements com.majdtalji.krankenhaus.db.dao.DaoList<UserDetailsVo> {

    private static UserDetailsDao userDetailsDao;

    private UserDetailsDao() {

    }

    public static UserDetailsDao getInstance() {
        if (userDetailsDao == null) {
            userDetailsDao = new UserDetailsDao();
        }
        return userDetailsDao;
    }

    @Override
    public List<UserDetailsVo> loadAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(UserDetailsVo udv) throws Exception {
        // ID	USER_ID	FIRST_NAME	FATHER_NAME	MOBILE	IMAGE	SPECIALIZATION
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            String userSql = " INSERT INTO users (USER_NAME,PASSWORD,USER_TYPE,ID) VALUES (?,?,?,?) ";
            ps = con.prepareStatement(userSql);
            ps.setString(1, udv.getUsersVo().getUserName());
            ps.setString(2, udv.getUsersVo().getPassword());
            ps.setInt(3, udv.getUsersVo().getUsersType().getId());
            ps.setInt(4, udv.getUsersVo().getId());
            ps.executeUpdate();

            String usersDetailsSql = " INSERT INTO users_details (USER_ID,FIRST_NAME,FATHER_NAME,MOBILE,IMAGE,SPECIALIZATION) VALUES (?,?,?,?,?,?) ";
            ps = con.prepareStatement(usersDetailsSql);
            ps.setInt(1, udv.getUsersVo().getId());
            ps.setString(2, udv.getFirstName());
            ps.setString(3, udv.getFatheName());
            ps.setString(4, udv.getMobile());
            ps.setBytes(5, udv.getImage());
            ps.setString(6, udv.getSpecialization());
            ps.executeUpdate();
            con.commit();
            count = 1;

        } catch (Exception e) {
            con.rollback();
        } finally {
            ps.close();
            closeConnection(con);
        }
        return count;
    }

    @Override
    public int update(UserDetailsVo udv) throws Exception {

        // ID	USER_ID	FIRST_NAME	FATHER_NAME	MOBILE IMAGE  SPECIALIZATION
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            String userSql = " UPDATE users SET USER_NAME=?,PASSWORD=?,USER_TYPE=? WHERE ID=? ";
            ps = con.prepareStatement(userSql);
            ps.setString(1, udv.getUsersVo().getUserName());
            ps.setString(2, udv.getUsersVo().getPassword());
            ps.setInt(3, udv.getUsersVo().getUsersType().getId());
            ps.setInt(4, udv.getUsersVo().getId());
            ps.executeUpdate();

            String usersDetailsSql = " UPDATE users_details SET FIRST_NAME=?,FATHER_NAME=?,MOBILE=?,IMAGE=?, SPECIALIZATION=? WHERE USER_ID=? ";
            ps = con.prepareStatement(usersDetailsSql);
            ps.setString(1, udv.getFirstName());
            ps.setString(2, udv.getFatheName());
            ps.setString(3, udv.getMobile());
            ps.setBytes(4, udv.getImage());
            ps.setString(5, udv.getSpecialization());
            ps.setInt(6, udv.getUsersVo().getId());
            ps.executeUpdate();
            con.commit();
            count = 1;

        } catch (SQLException e) {
            con.rollback();
        } finally {
            ps.close();
            closeConnection(con);
        }
        return count;

    }

    @Override
    public int delete(UserDetailsVo udv) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            String usersDetailsSql = " DELETE FROM users_details WHERE USER_ID=? ";
            ps = con.prepareStatement(usersDetailsSql);
            ps.setInt(1, udv.getUsersVo().getId());
            ps.executeUpdate();

            String userSql = " DELETE FROM users WHERE ID=? ";
            ps = con.prepareStatement(userSql);
            ps.setInt(1, udv.getUsersVo().getId());
            ps.executeUpdate();
            con.commit();
            count = 1;

        } catch (SQLException e) {
            con.rollback();
        } finally {
            ps.close();
            closeConnection(con);
        }
        return count;

    }

    @Override
    public UserDetailsVo getData(UserDetailsVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // ID	USER_ID	FIRST_NAME	FATHER_NAME	MOBILE

    @Override
    public UserDetailsVo getDataById(int id) throws Exception {

        // ID	USER_ID	FIRST_NAME	FATHER_NAME	MOBILE  IMAGE
        // ID   USER_NAME   PASSWORD    USER_TYPE
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDetailsVo userDetailsVo = null;
        UsersVo usersVo = null;
        try {
            con = getConnection();
            String userSql = " SELECT USERS.ID,USERS.USER_NAME,USERS.PASSWORD,USERS.USER_TYPE,  USERS_DETAILS.FIRST_NAME,USERS_DETAILS.FATHER_NAME,USERS_DETAILS.MOBILE,USERS_DETAILS.IMAGE, USERS_DETAILS.SPECIALIZATION FROM USERS INNER JOIN USERS_DETAILS ON USERS_DETAILS.USER_ID = USERS.ID WHERE USERS.ID=? ";
            ps = con.prepareStatement(userSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                usersVo = new UsersVo();
                usersVo.setId(rs.getInt("ID"));
                usersVo.setUserName(rs.getString("USER_NAME"));
                usersVo.setPassword(rs.getString("PASSWORD"));
                UsersType usersType = UsersType.getUsersTypeById(rs.getInt("USER_TYPE"));
                usersVo.setUsersType(usersType);

                userDetailsVo = new UserDetailsVo();
                userDetailsVo.setFirstName(rs.getString("FIRST_NAME"));
                userDetailsVo.setFatheName(rs.getString("FATHER_NAME"));
                userDetailsVo.setMobile(rs.getString("MOBILE"));
                userDetailsVo.setImage(rs.getBytes("IMAGE"));
                userDetailsVo.setSpecialization(rs.getString("SPECIALIZATION"));
                userDetailsVo.setUsersVo(usersVo);

            }

        } catch (SQLException e) {
        } finally {
            rs.close();
            ps.close();
            closeConnection(con);
        }
        return userDetailsVo;

    }

}
