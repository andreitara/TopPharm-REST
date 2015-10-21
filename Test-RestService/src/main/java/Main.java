import com.fasterxml.jackson.core.JsonProcessingException;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.user.permission.Permission;
import md.pharm.restservice.service.task.DoctorTaskController;

import java.util.Calendar;

/**
 * Created by Andrei on 9/22/2015.
 */
public class Main {

    public static void main(String[] args) throws JsonProcessingException, InterruptedException {
        //LoginControllerTest.loginAdmin();
        //LoginControllerTest.logoutAdmin();
        //LoginControllerTest.loginUser();
        //LoginControllerTest.logoutUser();

        //PermissionControllerTest.getAdminUserPermission(3);
        //PermissionControllerTest.getAdminMyPermission();
        //PermissionControllerTest.updatePermissionsByAdmin(3);

        //UserControllerTest.createUserByAdmin();
        //UserControllerTest.updateUserByAdmin(2);
        //UserControllerTest.deleteUserByAdmin(2);
        //UserControllerTest.getUserByAdmin(4);
        //UserControllerTest.getAllUsersByAdmin();
        //UserControllerTest.createUserByUser();
        //UserControllerTest.deleteUserByUser();
        //DoctorUserControllerTest.addDoctorToUser(4,2);
        //DoctorUserControllerTest.deleteDoctorFromUser(4,1);
        //DoctorUserControllerTest.getAllDoctorsFromUser(4);

        //InstitutionsControllerTest.createInstitutionByAdmin();
        //InstitutionsControllerTest.getInstitutionsByAdmin(4);
        InstitutionsControllerTest.getAllInstitutionsByAdmin();
        //InstitutionsControllerTest.updateInstitutionByAdmin(4);
        //InstitutionsControllerTest.deleteInstitutionByAdmin(3);
        //InstitutionsControllerTest.getAllInstitutionsByCity("city1");
        //InstitutionsControllerTest.getAllInstitutionsByState("state");
        //InstitutionsControllerTest.getAllInstitutionsByCityDistrict("city1","district");
        //InstitutionsControllerTest.getAllInstitutionsByPartName("Long Name 3");

        //InstitutionAddressControllerTest.getInstitutionAddressByAdmin(4);
        //InstitutionAddressControllerTest.createInstitutionAddressByAdmin(9);
        //InstitutionAddressControllerTest.updateInstitutionAddressByAdmin(5);

        //DoctorControllerTest.createDoctorByAdmin();
        //DoctorControllerTest.updateDoctorByAdmin(1);
        //DoctorControllerTest.getAllDoctorsByAdmin();
        //DoctorControllerTest.getDoctorByAdmin(1);
        //DoctorControllerTest.deleteDoctorByAdmin(2);

        //ProductControllerTest.getAllProductsByAdmin();
        //ProductControllerTest.createProductByAdmin();
        //ProductControllerTest.getProductByAdmin(2);
        //ProductControllerTest.updateDoctorByAdmin(1);
        //ProductControllerTest.deleteProductByAdmin(1);

        //ProductObjectiveControllerTest.createProductByAdmin(2);
        //ProductObjectiveControllerTest.getAllProductObjectivesByAdmin(2);
        //ProductObjectiveControllerTest.getProductByAdmin(2,2);
        //ProductObjectiveControllerTest.updateProductObjectiveByAdmin(2,2);
        //ProductObjectiveControllerTest.deleteProductObjectiveByAdmin(2,1);

        //DoctorOfficeControllerTest.createOfficeByAdmin(4,4);
        //DoctorOfficeControllerTest.getAllDoctorOfficesByAdmin(4);
        //DoctorOfficeControllerTest.getDoctorOfficesByAdmin(4,3);
        //DoctorOfficeControllerTest.updateDoctorOfficesByAdmin(4,4);
        //DoctorOfficeControllerTest.deleteDoctorOfficeByAdmin(4,1);

        //TaskControllerTest.createTaskByAdmin();
        //TaskControllerTest.updateTaskByAdmin(1);
        //TaskControllerTest.getAllTasksByAdmin();
        //TaskControllerTest.getTaskByAdmin(1);
        //TaskControllerTest.deleteTaskByAdmin(2);

        //DoctorTaskControllerTest.addDoctorToTask(1,3);
        //DoctorTaskControllerTest.deleteDoctorToTask(2,3);
        //DoctorTaskControllerTest.getAllDoctorsTask(2);

        //ProductTaskControllerTest.addProductToTask(1,2);
        //ProductTaskControllerTest.getAllProductsTask(1);
        //ProductTaskControllerTest.deleteProductTask(1,2);

        //UserTaskControllerTest.addUserToTask(6,2);
        //UserTaskControllerTest.deleteUserToTask(1,3);
        //UserTaskControllerTest.getAllUsersTask(2);
        //UserTaskControllerTest.getUserTasksByStartAndEndDate(2);
        //UserTaskControllerTest.getUserTasksByStatus(2, "new");
        //UserTaskControllerTest.getUserTasksByType(2, "simple");

        //InstitutionTaskControllerTest.addInstitutionToTask(1,4);
        //InstitutionTaskControllerTest.deleteInstitutionFromTask(1);
        //InstitutionTaskControllerTest.getInstitutionTask(1);
        //InstitutionTaskControllerTest.updateInstitutionToTask(1,5);

        //MessageControllerTest.addMessage();
        //MessageControllerTest.getMessageUniDirectional();
        //MessageControllerTest.getMessageBiDirectional();

        //PerformanceTest.getTest();
    }
}
