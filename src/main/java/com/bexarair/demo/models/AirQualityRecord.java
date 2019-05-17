package com.bexarair.demo.models;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "AirQuality")
public class AirQualityRecord {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Date dateObserved;

    @Column(nullable = false)
    private int hourObserved;

    @Column(nullable = false)
    private String localTimeZone;

    @Column(nullable = false)
    private String reportingArea;

    @Column(nullable = false)
    private String stateCode;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private String parameterName;

    @Column(nullable = false)
    private int AQI;

    @Column(nullable = false)
    private int categoryNumber;

    @Column(nullable = false)
    private int zipCode;

    @Column(nullable = false)
    private Date forecastDateIssue;

    @Column(nullable = false)
    private Date forecastDate;

    @Column(nullable = false)
    private String forecastParameterName;

    @Column(nullable = false)
    private int forecastAQI;

    @Column(nullable = false)
    private int forecastCategoryNumber;

    @Column(nullable = false)
    private boolean forecastActionDay;

    @Column(nullable = false, columnDefinition = "text")
    private String forecastDiscussion;


    //used to create
    public AirQualityRecord(){

    }

    //used to read and edit
    public AirQualityRecord(Date dateObserved, int hourObserved, String localTimeZone, String reportingArea, String stateCode, double latitude, double longitude, String parameterName, int AQI, int categoryNumber, int zipCode, Date forecastDateIssue, Date forecastDate, String forecastParameterName, int forecastAQI, int forecastCategoryNumber, boolean forecastActionDay, String forecastDiscussion) {
        this.dateObserved = dateObserved;
        this.hourObserved = hourObserved;
        this.localTimeZone = localTimeZone;
        this.reportingArea = reportingArea;
        this.stateCode = stateCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.parameterName = parameterName;
        this.AQI = AQI;
        this.categoryNumber = categoryNumber;
        this.zipCode = zipCode;
        this.forecastDateIssue = forecastDateIssue;
        this.forecastDate = forecastDate;
        this.forecastParameterName = forecastParameterName;
        this.forecastAQI = forecastAQI;
        this.forecastCategoryNumber = forecastCategoryNumber;
        this.forecastActionDay = forecastActionDay;
        this.forecastDiscussion = forecastDiscussion;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateObserved() {
        return dateObserved;
    }

    public void setDateObserved(Date dateObserved) {
        this.dateObserved = dateObserved;
    }

    public int getHourObserved() {
        return hourObserved;
    }

    public void setHourObserved(int hourObserved) {
        this.hourObserved = hourObserved;
    }

    public String getLocalTimeZone() {
        return localTimeZone;
    }

    public void setLocalTimeZone(String localTimeZone) {
        this.localTimeZone = localTimeZone;
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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Date getForecastDateIssue() {
        return forecastDateIssue;
    }

    public void setForecastDateIssue(Date forecastDateIssue) {
        this.forecastDateIssue = forecastDateIssue;
    }

    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String getForecastParameterName() {
        return forecastParameterName;
    }

    public void setForecastParameterName(String forecastParameterName) {
        this.forecastParameterName = forecastParameterName;
    }

    public int getForecastAQI() {
        return forecastAQI;
    }

    public void setForecastAQI(int forecastAQI) {
        this.forecastAQI = forecastAQI;
    }

    public int getForecastCategoryNumber() {
        return forecastCategoryNumber;
    }

    public void setForecastCategoryNumber(int forecastCategoryNumber) {
        this.forecastCategoryNumber = forecastCategoryNumber;
    }

    public boolean isForecastActionDay() {
        return forecastActionDay;
    }

    public void setForecastActionDay(boolean forecastActionDay) {
        this.forecastActionDay = forecastActionDay;
    }

    public String getForecastDiscussion() {
        return forecastDiscussion;
    }

    public void setForecastDiscussion(String forecastDiscussion) {
        this.forecastDiscussion = forecastDiscussion;
    }
}//end of hospitalrecord class
