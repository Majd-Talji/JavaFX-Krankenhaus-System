package com.majdtalji.krankenhaus.db.dao;

import com.majdtalji.krankenhaus.db.type.UsersType;
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
public class UsersDao extends Dao implements DaoList<UsersVo> {

    private static UsersDao usersDao;

    private UsersDao() {
    }

    public static UsersDao getInstance() {
        if (usersDao == null) {
            usersDao = new UsersDao();
        }
        return usersDao;
    }

    @Override
    public List<UsersVo> loadAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(UsersVo uv) throws Exception {
        //  ID USER_NAME PASSWORD USER_TYPE
        Connection con = null;
        int count = 0;
        try {
            con = getConnection();
            String sql = " INSERT INTO users (USER_NAME,PASSWORD,USER_TYPE,ID) VALUES (?,?,?,?) ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uv.getUserName());
            ps.setString(2, uv.getPassword());
            ps.setInt(3, uv.getUsersType().getId());
            ps.setInt(4, uv.getId());
            count = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        } finally {
            closeConnection(con);
        }
        return count;
    }

    @Override
    public int delete(UsersVo uv) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(UsersVo uv) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsersVo getData(UsersVo uv) throws Exception {
        // 	ID	USER_NAME	PASSWORD	USER_TYPE
        Connection con = null;
        UsersVo usersVo = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM users WHERE USER_NAME='" + uv.getUserName() + "' AND PASSWORD='" + uv.getPassword() + "'";
            PreparedStatement ps = con.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                usersVo = new UsersVo();
                usersVo.setId(rs.getInt("ID"));
                usersVo.setUserName(rs.getString("USER_NAME"));
                usersVo.setPassword(rs.getString("PASSWORD"));
                UsersType usersType = UsersType.getUsersTypeById(rs.getInt("USER_TYPE"));
                usersVo.setUsersType(usersType);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        } finally {
            closeConnection(con);
        }
        return usersVo;
    }

    @Override
    public UsersVo getDataById(int id) throws Exception {

        // 	ID	USER_NAME	PASSWORD	USER_TYPE
        Connection con = null;
        UsersVo usersVo = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM users WHERE ID=?";
            PreparedStatement ps = con.prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                usersVo = new UsersVo();
                usersVo.setId(rs.getInt("ID"));
                usersVo.setUserName(rs.getString("USER_NAME"));
                usersVo.setPassword(rs.getString("PASSWORD"));
                UsersType usersType = UsersType.getUsersTypeById(rs.getInt("USER_TYPE"));
                usersVo.setUsersType(usersType);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        } finally {
            closeConnection(con);
        }
        return usersVo;

    }

}
