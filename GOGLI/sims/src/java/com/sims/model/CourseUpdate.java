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
public class CourseUpdate implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String code;
    private String length;
    private String credit;
    private String summary;

    public CourseUpdate(int id, String title, String code, String length, String credit, String summary) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.length = length;
        this.credit = credit;
        this.summary = summary;
    }

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

     
    
}
