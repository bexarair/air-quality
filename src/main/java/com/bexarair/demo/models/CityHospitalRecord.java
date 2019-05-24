package com.bexarair.demo.models;
import javax.persistence.*;
@Entity
@Table(name = "city_hospital_records")
public class CityHospitalRecord {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private int pediAsthmaCases;

    @Column(nullable = false)
    private int pediPopulation;

    @Column(nullable = false)
    private String pediAsthmaRate;


    //creates a record
    public CityHospitalRecord(){

    }



    //reads and updates a record
    public CityHospitalRecord(String zipCode, int pediAsthmaCases, int pediPopulation, String pediAsthmaRate) {
        this.zipCode = zipCode;
        this.pediAsthmaCases = pediAsthmaCases;
        this.pediPopulation = pediPopulation;
        this.pediAsthmaRate = pediAsthmaRate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getPediAsthmaCases() {
        return pediAsthmaCases;
    }

    public void setPediAsthmaCases(int pediAsthmaCases) {
        this.pediAsthmaCases = pediAsthmaCases;
    }

    public int getPediPopulation() {
        return pediPopulation;
    }

    public void setPediPopulation(int pediPopulation) {
        this.pediPopulation = pediPopulation;
    }

    public String getPediAsthmaRate() {
        return pediAsthmaRate;
    }

    public void setPediAsthmaRate(String pediAsthmaRate) {
        this.pediAsthmaRate = pediAsthmaRate;
    }
}//end of hospitalrecord class