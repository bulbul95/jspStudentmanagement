/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sims.model;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class StaffUpdate implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String fname;
    private String mname;
    private String contact;
    private String position;

    public StaffUpdate(int id, String name, String fname, String mname, String position, String contact) {
        this.id = id;
        this.name = name;
        this.fname = fname;
        this.mname = mname;
        this.contact = contact;
        this.position = position;
    }

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
    
}
