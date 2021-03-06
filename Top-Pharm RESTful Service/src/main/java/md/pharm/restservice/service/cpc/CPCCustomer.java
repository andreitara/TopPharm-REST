package md.pharm.restservice.service.cpc;

import md.pharm.hibernate.doctor.attributes.Speciality;

import javax.persistence.*;

/**
 * Created by Andrei on 1/4/2016.
 */

public class CPCCustomer {

    private Integer id;
    private String name;
    private Integer plannedActivities;
    private Integer actualActivities;
    private Integer callsToMake;
    private Double cpc;
    private String targetClass;
    private String targetSubClass;
    private String speciality;
    private Integer ydtPlanedActivities;
    private Integer ydtActualActivities;

    public CPCCustomer(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlannedActivities() {
        return plannedActivities;
    }

    public void setPlannedActivities(Integer plannedActivities) {
        this.plannedActivities = plannedActivities;
    }

    public Integer getActualActivities() {
        return actualActivities;
    }

    public void setActualActivities(Integer actualActivities) {
        this.actualActivities = actualActivities;
    }

    public Integer getCallsToMake() {
        return callsToMake;
    }

    public void setCallsToMake(Integer callsToMake) {
        this.callsToMake = callsToMake;
    }

    public Double getCpc() {
        return cpc;
    }

    public void setCpc(Double cpc) {
        this.cpc = cpc;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getTargetSubClass() {
        return targetSubClass;
    }

    public void setTargetSubClass(String targetSubClass) {
        this.targetSubClass = targetSubClass;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getYdtPlanedActivities() {
        return ydtPlanedActivities;
    }

    public void setYdtPlanedActivities(Integer ydtPlanedActivities) {
        this.ydtPlanedActivities = ydtPlanedActivities;
    }

    public Integer getYdtActualActivities() {
        return ydtActualActivities;
    }

    public void setYdtActualActivities(Integer ydtActualActivities) {
        this.ydtActualActivities = ydtActualActivities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CPCCustomer that = (CPCCustomer) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (plannedActivities != null ? !plannedActivities.equals(that.plannedActivities) : that.plannedActivities != null)
            return false;
        if (actualActivities != null ? !actualActivities.equals(that.actualActivities) : that.actualActivities != null)
            return false;
        if (callsToMake != null ? !callsToMake.equals(that.callsToMake) : that.callsToMake != null) return false;
        if (cpc != null ? !cpc.equals(that.cpc) : that.cpc != null) return false;
        if (targetClass != null ? !targetClass.equals(that.targetClass) : that.targetClass != null) return false;
        if (targetSubClass != null ? !targetSubClass.equals(that.targetSubClass) : that.targetSubClass != null)
            return false;
        if (speciality != null ? !speciality.equals(that.speciality) : that.speciality != null) return false;
        if (ydtPlanedActivities != null ? !ydtPlanedActivities.equals(that.ydtPlanedActivities) : that.ydtPlanedActivities != null)
            return false;
        return !(ydtActualActivities != null ? !ydtActualActivities.equals(that.ydtActualActivities) : that.ydtActualActivities != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (plannedActivities != null ? plannedActivities.hashCode() : 0);
        result = 31 * result + (actualActivities != null ? actualActivities.hashCode() : 0);
        result = 31 * result + (callsToMake != null ? callsToMake.hashCode() : 0);
        result = 31 * result + (cpc != null ? cpc.hashCode() : 0);
        result = 31 * result + (targetClass != null ? targetClass.hashCode() : 0);
        result = 31 * result + (targetSubClass != null ? targetSubClass.hashCode() : 0);
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        result = 31 * result + (ydtPlanedActivities != null ? ydtPlanedActivities.hashCode() : 0);
        result = 31 * result + (ydtActualActivities != null ? ydtActualActivities.hashCode() : 0);
        return result;
    }
}
