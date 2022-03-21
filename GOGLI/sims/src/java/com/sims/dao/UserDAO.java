package com.sims.dao;
//import beans.CustomerBean;

import com.sims.util.Util;
import java.sql.*;
import pool.FixedVar;

/**
 *
 * @author User
 */
public class UserDAO {

    public static boolean login(String login, String password) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = FixedVar.getConnection();
            ps = con.prepareStatement(
                    "select uname, pass, role_id, isactive from user_info where uname = ? and pass = ? ");
            ps.setString(1, login);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) // found
            {
                //System.out.println(rs.getString("uname"));
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            //FixedVar.close(con);
            rs.close();
            ps.close();
        }
    }
}
