/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.admin.master.sugestedcourse.dao;

/**
 *
 * @author user
 */
public class SuggestedCoursePOJO {

    String courseId;
    String country, university, level, course,location;

    /**
     *
     * @return
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     *
     * @param courseId
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     */
    public String getUniversity() {
        return university;
    }

    /**
     *
     * @param university
     */
    public void setUniversity(String university) {
        this.university = university;
    }

    /**
     *
     * @return
     */
    public String getLevel() {
        return level;
    }

    /**
     *
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     *
     * @return
     */
    public String getCourse() {
        return course;
    }

    /**
     *
     * @param course
     */
    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "suggestedCoursePOJO{" + "courseId=" + courseId + ", country=" + country + ", university=" + university + ", level=" + level + ", course=" + course + '}';
    }

}
