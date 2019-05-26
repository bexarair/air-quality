package com.bexarair.demo.models;
import javax.persistence.*;
@Entity
@Table(name = "CityHospitalRecords")
public class CityHospitalRecord {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private int zipCode;

    @Column(nullable = false)
    private int asthmaCases;

    @Column(nullable = false)
    private int pediAsthmaCases;

    @Column(nullable = false)
    private int pediPopulation;

    @Column(nullable = false)
    private int pediAsthmaRate;


    //creates a record
    public CityHospitalRecord(){

    }

    //reads and updates a record
    public CityHospitalRecord(int zipCode, int pediAsthmaCases, int pediPopulation, int pediAsthmaRate) {
        this.zipCode = zipCode;
        this.pediAsthmaCases = pediAsthmaCases;
        this.pediPopulation = pediPopulation;
        this.pediAsthmaRate = pediAsthmaRate;
    }

    public int getAsthmaCases() {
        return asthmaCases;
    }

    public void setAsthmaCases(int asthmaCases) {
        this.asthmaCases = asthmaCases;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
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

    public int getPediAsthmaRate() {
        return pediAsthmaRate;
    }

    public void setPediAsthmaRate(int pediAsthmaRate) {
        this.pediAsthmaRate = pediAsthmaRate;
    }
}//end of hospitalrecord class