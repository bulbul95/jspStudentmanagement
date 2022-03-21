/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;


import com.sims.util.Util;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;
import pool.FixedVar;

/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class StaffBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String fname;
    private String mname;
    private String contact;
    private String position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public static ArrayList<StaffUpdate> staffList = new ArrayList();

    public ArrayList<StaffUpdate> getStaffName() {
        return staffList;
    }
    public void onEdit(RowEditEvent event) {
        id = ((StaffUpdate) event.getObject()).getId();
        name = ((StaffUpdate) event.getObject()).getName();
        fname = ((StaffUpdate) event.getObject()).getFname();
        mname = ((StaffUpdate) event.getObject()).getMname();
        position = ((StaffUpdate) event.getObject()).getPosition();
        contact = ((StaffUpdate) event.getObject()).getContact();
        try {
            Connection con = FixedVar.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE staff set name = '" + name + "', father_name = '" + fname + "', mother_name = '" + mname + "', position = '" + position + "', contact_no = '" + contact + "' where staff_id = '" + id + "'");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully ", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onCancel(RowEditEvent event) {
        id = ((StaffUpdate) event.getObject()).getId();
        try {
            Connection con = FixedVar.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE from staff where staff_id = '" + id + "'");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted Successfully ", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostConstruct
    public void init() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = FixedVar.getConnection();
            st = con.createStatement();
            String myQuery = "SELECT * FROM staff";
            removelist();
            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                staffList.add(new StaffUpdate(rs.getInt("staff_id"), rs.getString("name"), rs.getString("father_name"), rs.getString("mother_name"), rs.getString("position"), rs.getString("contact_no")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removelist() {
        staffList.removeAll(staffList);
    }
    public synchronized void saveInfo() throws SQLException {
        Connection con = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;

        try {
            con = FixedVar.getConnection();
            HttpSession session = Util.getSession();
            String sql = "insert into staff(name, father_name, mother_name, position, contact_no) values(?,?,?,?,?)";
            ps2 = con.prepareStatement(sql);
            ps2.setString(1, name);
            ps2.setString(2, fname);
            ps2.setString(3, mname);
            ps2.setString(4, position);
            ps2.setString(5, contact);
            int x = ps2.executeUpdate();
            if (x == 1) {
                FacesContext contextm = FacesContext.getCurrentInstance();
                contextm.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry Successful", "Have A Nice Day"));
                init();
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        "a",
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Could not saved data!",
                                "Please Try Again!"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            con.close();
        }

    }
    
}
