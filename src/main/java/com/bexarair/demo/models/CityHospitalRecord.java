package com.bexarair.demo.models;
import javax.persistence.*;
@Entity
@Table(name = "city_hospital_records")
public class CityHospitalRecord {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private String zipcode;

    @Column(nullable = false)
    private int pedi_asthma_cases;

    @Column(nullable = false)
    private int pedi_population;

    @Column(nullable = false)
    private String pedi_asthma_rate;


    //creates a record
    public CityHospitalRecord(){

    }


    public String getZipcode() {
        return zipcode;
    }

    public void setZip_code(String zip_code) {
        this.zipcode = zip_code;
    }

    public int getPedi_asthma_cases() {
        return pedi_asthma_cases;
    }

    public void setPedi_asthma_cases(int pedi_asthma_cases) {
        this.pedi_asthma_cases = pedi_asthma_cases;
    }

    public int getPedi_population() {
        return pedi_population;
    }

    public void setPedi_population(int pedi_population) {
        this.pedi_population = pedi_population;
    }

    public String getPedi_asthma_rate() {
        return pedi_asthma_rate;
    }

    public void setPedi_asthma_rate(String pedi_asthma_rate) {
        this.pedi_asthma_rate = pedi_asthma_rate;
    }

    //reads and updates a record
    public CityHospitalRecord(String year,String zipcode, int pedi_asthma_cases, int pedi_population, String pedi_asthma_rate) {
        this.year = year;
        this.zipcode = zipcode;
        this.pedi_asthma_cases = pedi_asthma_cases;
        this.pedi_population = pedi_population;
        this.pedi_asthma_rate = pedi_asthma_rate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}//end of hospitalrecord class