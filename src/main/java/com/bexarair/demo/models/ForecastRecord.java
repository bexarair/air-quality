package com.bexarair.demo.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class ForecastRecord {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String dateIssue;

    @Column
    private String forecastDate;

    @Column
    private String reportingArea;

    @Column
    private String stateCode;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private String parameterName;

    @Column
    private int AQI;

    @Column
    private int categoryNumber;

    @Column
    private String categoryName;

    @Column
    private boolean actionDay;

    @Column(columnDefinition = "text")
    private String discussion;

    @Column
    private String zipCode;


    public ForecastRecord(){

    }

    public ForecastRecord(String dateIssue, String forecastDate, String reportingArea, String stateCode, double latitude, double longitude, String parameterName, int AQI, int categoryNumber, String categoryName, boolean actionDay, String discussion, String zipCode) {
        this.dateIssue = dateIssue;
        this.forecastDate = forecastDate;
        this.reportingArea = reportingArea;
        this.stateCode = stateCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.parameterName = parameterName;
        this.AQI = AQI;
        this.categoryNumber = categoryNumber;
        this.categoryName = categoryName;
        this.actionDay = actionDay;
        this.discussion = discussion;
        this.zipCode = zipCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(String dateIssue) {
        this.dateIssue = dateIssue;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String getReportingArea() {
        return reportingArea;
    }

    public void setReportingArea(String reportingArea) {
        this.reportingArea = reportingArea;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public int getAQI() {
        return AQI;
    }

    public void setAQI(int AQI) {
        this.AQI = AQI;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isActionDay() {
        return actionDay;
    }

    public void setActionDay(boolean actionDay) {
        this.actionDay = actionDay;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}



