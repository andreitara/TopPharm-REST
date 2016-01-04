package md.pharm.restservice.service.cpc;

import md.pharm.hibernate.doctor.attributes.Speciality;

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

    private Speciality speciality;

    private Integer YDTPlanedActivities;

    private Integer YDTActualActivities;
}
