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
public class TutorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String fname;
    private String mname;
    private String contact;
    private String subject;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public static ArrayList<TutorUpdate> tutorList = new ArrayList();

    public ArrayList<TutorUpdate> getTutorName() {
        return tutorList;
    }
    public void onEdit(RowEditEvent event) {
        id = ((TutorUpdate) event.getObject()).getId();
        name = ((TutorUpdate) event.getObject()).getName();
        fname = ((TutorUpdate) event.getObject()).getFname();
        mname = ((TutorUpdate) event.getObject()).getMname();
        subject = ((TutorUpdate) event.getObject()).getSubject();
        contact = ((TutorUpdate) event.getObject()).getContact();
        try {
            Connection con = FixedVar.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE tutor set name = '" + name + "', father_name = '" + fname + "', mother_name = '" + mname + "', subject = '" + subject + "', contact_no = '" + contact + "' where tutor_id = '" + id + "'");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully ", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onCancel(RowEditEvent event) {
        id = ((TutorUpdate) event.getObject()).getId();
        try {
            Connection con = FixedVar.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE from tutor where tutor_id = '" + id + "'");
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
            String myQuery = "SELECT * FROM tutor";
            removelist();
            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                tutorList.add(new TutorUpdate(rs.getInt("tutor_id"), rs.getString("name"), rs.getString("father_name"), rs.getString("mother_name"), rs.getString("subject"), rs.getString("contact_no")));
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
        tutorList.removeAll(tutorList);
    }
    public synchronized void saveInfo() throws SQLException {
        Connection con = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;

        try {
            con = FixedVar.getConnection();
            HttpSession session = Util.getSession();
            String sql = "insert into tutor(name, father_name, mother_name, subject, contact_no) values(?,?,?,?,?)";
            ps2 = con.prepareStatement(sql);
            ps2.setString(1, name);
            ps2.setString(2, fname);
            ps2.setString(3, mname);
            ps2.setString(4, subject);
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
