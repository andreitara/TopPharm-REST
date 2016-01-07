package md.pharm.restservice.service.cpc;

/**
 * Created by Andrei on 1/4/2016.
 */

public class CPCRepresentative {

    private Integer id;
    private String representativeName;
    private Integer plannedActivities;
    private Integer actualActivities;
    private Integer callsToMake;
    private Double cpc;
    private Integer ydtPlanedActivities;
    private Integer ydtActualActivities;

    public CPCRepresentative(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
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

        CPCRepresentative that = (CPCRepresentative) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (representativeName != null ? !representativeName.equals(that.representativeName) : that.representativeName != null)
            return false;
        if (plannedActivities != null ? !plannedActivities.equals(that.plannedActivities) : that.plannedActivities != null)
            return false;
        if (actualActivities != null ? !actualActivities.equals(that.actualActivities) : that.actualActivities != null)
            return false;
        if (callsToMake != null ? !callsToMake.equals(that.callsToMake) : that.callsToMake != null) return false;
        if (cpc != null ? !cpc.equals(that.cpc) : that.cpc != null) return false;
        if (ydtPlanedActivities != null ? !ydtPlanedActivities.equals(that.ydtPlanedActivities) : that.ydtPlanedActivities != null)
            return false;
        return !(ydtActualActivities != null ? !ydtActualActivities.equals(that.ydtActualActivities) : that.ydtActualActivities != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (representativeName != null ? representativeName.hashCode() : 0);
        result = 31 * result + (plannedActivities != null ? plannedActivities.hashCode() : 0);
        result = 31 * result + (actualActivities != null ? actualActivities.hashCode() : 0);
        result = 31 * result + (callsToMake != null ? callsToMake.hashCode() : 0);
        result = 31 * result + (cpc != null ? cpc.hashCode() : 0);
        result = 31 * result + (ydtPlanedActivities != null ? ydtPlanedActivities.hashCode() : 0);
        result = 31 * result + (ydtActualActivities != null ? ydtActualActivities.hashCode() : 0);
        return result;
    }
}
