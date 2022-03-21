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
public class CourseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String code;
    private String length;
    private String credit;
    private String summary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    public static ArrayList<CourseUpdate> courseList = new ArrayList();

    public ArrayList<CourseUpdate> getCourseName() {
        return courseList;
    }

    public void onEdit(RowEditEvent event) {
        id = ((CourseUpdate) event.getObject()).getId();
        title = ((CourseUpdate) event.getObject()).getTitle();
        code = ((CourseUpdate) event.getObject()).getCode();
        length = ((CourseUpdate) event.getObject()).getLength();
        credit = ((CourseUpdate) event.getObject()).getCredit();
        summary = ((CourseUpdate) event.getObject()).getSummary();
        try {
            Connection con = FixedVar.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE course set course_title = '" + title + "', course_code = '" + code + "', course_length = '" + length + "', course_credit = '" + credit + "', course_summary = '" + summary + "' where course_id = '" + id + "'");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully ", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCancel(RowEditEvent event) {
        id = ((CourseUpdate) event.getObject()).getId();
        try {
            Connection con = FixedVar.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE from course where course_id = '" + id + "'");
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
            String myQuery = "SELECT * FROM course";
            removelist();
            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                courseList.add(new CourseUpdate(rs.getInt("course_id"), rs.getString("course_title"), rs.getString("course_code"), rs.getString("course_length"), rs.getString("course_credit"), rs.getString("course_summary")));
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
        courseList.removeAll(courseList);
    }

    public synchronized void saveInfo() throws SQLException {
        Connection con = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;

        try {
            con = FixedVar.getConnection();
            HttpSession session = Util.getSession();
            String sql = "insert into course(course_title, course_code, course_length, course_credit, course_summary) values(?,?,?,?,?)";
            ps2 = con.prepareStatement(sql);
            ps2.setString(1, title);
            ps2.setString(2, code);
            ps2.setString(3, length);
            ps2.setString(4, credit);
            ps2.setString(5, summary);
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
