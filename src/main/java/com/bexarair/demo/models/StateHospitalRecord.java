package com.bexarair.demo.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="StateHospitalRecords")
public class StateHospitalRecord {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private int typeOfAdmission;

    @Column
    private int sourceOfAdmission;

    @Column
    private int dischargeQuarter;

    @Column
    private String providerName;

    @Column
    private String patZipCode;

    @Column
    private int county;

    @Column
    private int publicHeathRegion;

    @Column
    private String sex;

    @Column
    private int race;

    @Column
    private int ethnicity;

    @Column
    private int lengthOfStay;

    @Column
    private int patAge;

    @Column
    private int admittingDiagnosis;

    @Column
    private int princDiag;

    @Column
    private int otherDiag;

    @Column
    private int poaPrincDiag;

    @Column
    private int riskMortality;

    @Column
    private int illnessSeverity;


    public StateHospitalRecord(){

    }

    public StateHospitalRecord(int typeOfAdmission, int sourceOfAdmission, int dischargeQuarter, String providerName, String patZipCode, int county, int publicHeathRegion, String sex, int race, int ethnicity, int lengthOfStay, int patAge, int admittingDiagnosis, int princDiag, int otherDiag, int poaPrincDiag, int riskMortality, int illnessSeverity) {
        this.typeOfAdmission = typeOfAdmission;
        this.sourceOfAdmission = sourceOfAdmission;
        this.dischargeQuarter = dischargeQuarter;
        this.providerName = providerName;
        this.patZipCode = patZipCode;
        this.county = county;
        this.publicHeathRegion = publicHeathRegion;
        this.sex = sex;
        this.race = race;
        this.ethnicity = ethnicity;
        this.lengthOfStay = lengthOfStay;
        this.patAge = patAge;
        this.admittingDiagnosis = admittingDiagnosis;
        this.princDiag = princDiag;
        this.otherDiag = otherDiag;
        this.poaPrincDiag = poaPrincDiag;
        this.riskMortality = riskMortality;
        this.illnessSeverity = illnessSeverity;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTypeOfAdmission() {
        return typeOfAdmission;
    }

    public void setTypeOfAdmission(int typeOfAdmission) {
        this.typeOfAdmission = typeOfAdmission;
    }

    public int getSourceOfAdmission() {
        return sourceOfAdmission;
    }

    public void setSourceOfAdmission(int sourceOfAdmission) {
        this.sourceOfAdmission = sourceOfAdmission;
    }

    public int getDischargeQuarter() {
        return dischargeQuarter;
    }

    public void setDischargeQuarter(int dischargeQuarter) {
        this.dischargeQuarter = dischargeQuarter;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getPatZipCode() {
        return patZipCode;
    }

    public void setPatZipCode(String patZipCode) {
        this.patZipCode = patZipCode;
    }

    public int getCounty() {
        return county;
    }

    public void setCounty(int county) {
        this.county = county;
    }

    public int getPublicHeathRegion() {
        return publicHeathRegion;
    }

    public void setPublicHeathRegion(int publicHeathRegion) {
        this.publicHeathRegion = publicHeathRegion;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public int getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(int ethnicity) {
        this.ethnicity = ethnicity;
    }

    public int getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(int lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }

    public int getPatAge() {
        return patAge;
    }

    public void setPatAge(int patAge) {
        this.patAge = patAge;
    }

    public int getAdmittingDiagnosis() {
        return admittingDiagnosis;
    }

    public void setAdmittingDiagnosis(int admittingDiagnosis) {
        this.admittingDiagnosis = admittingDiagnosis;
    }

    public int getPrincDiag() {
        return princDiag;
    }

    public void setPrincDiag(int princDiag) {
        this.princDiag = princDiag;
    }

    public int getOtherDiag() {
        return otherDiag;
    }

    public void setOtherDiag(int otherDiag) {
        this.otherDiag = otherDiag;
    }

    public int getPoaPrincDiag() {
        return poaPrincDiag;
    }

    public void setPoaPrincDiag(int poaPrincDiag) {
        this.poaPrincDiag = poaPrincDiag;
    }

    public int getRiskMortality() {
        return riskMortality;
    }

    public void setRiskMortality(int riskMortality) {
        this.riskMortality = riskMortality;
    }

    public int getIllnessSeverity() {
        return illnessSeverity;
    }

    public void setIllnessSeverity(int illnessSeverity) {
        this.illnessSeverity = illnessSeverity;
    }
}
