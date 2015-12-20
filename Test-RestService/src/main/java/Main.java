import com.fasterxml.jackson.core.JsonProcessingException;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.task.Task;
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
        //LoginControllerTest.changePasswordAdmin();

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
        //InstitutionsControllerTest.getAllInstitutionsByAdmin();
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

        //SpecialityController.createDoctorByAdmin();
        //SpecialityController.getAllDoctorsByAdmin();
        //SpecialityController.getDoctorByAdmin(2);
        //SpecialityController.updateDoctorByAdmin(3);
        //SpecialityController.deleteDoctorByAdmin(4);

        //GeneralTypeControllerTest.createDoctorByAdmin();
        //GeneralTypeControllerTest.getAllDoctorsByAdmin();
        //GeneralTypeControllerTest.getDoctorByAdmin(2);
        //GeneralTypeControllerTest.updateDoctorByAdmin(4);
        //GeneralTypeControllerTest.deleteDoctorByAdmin(5);


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
        //TaskControllerTest.deleteTaskByAdmin(1);
        //TaskControllerTest.closeTaskByAdmin(1);
        //TaskControllerTest.executeTaskByAdmin(1);
        //TaskControllerTest.addTaskCommentByAdmin(1);
        //TaskControllerTest.getTaskCommentByAdmin(1);
        //TaskControllerTest.getTaskHistoryByAdmin(1);

        //TaskAttributesControllerTest.addMemoToTask(1);
        //TaskAttributesControllerTest.getMemosFromTask(1);
        //TaskAttributesControllerTest.deleteMemoFromTask(1,3);

        //TaskAttributesControllerTest.addObjectiveToTask(1);
        //TaskAttributesControllerTest.getObjectivesFromTask(1);
        //TaskAttributesControllerTest.deleteObjectiveFromTask(1,2);

        //TaskAttributesControllerTest.addSampleToTask(1,1);
        //TaskAttributesControllerTest.getSamplesFromTask(1);
        //TaskAttributesControllerTest.deleteSampleFromTask(1,2);

        //TaskAttributesControllerTest.addPromoItemToTask(1);
        //TaskAttributesControllerTest.getPromoItemsFromTask(1);
        //TaskAttributesControllerTest.deletePromoItemFromTask(1,2);

        //PromoItemControllerTest.createDoctorByAdmin();
        //PromoItemControllerTest.getAllDoctorsByAdmin();
        //PromoItemControllerTest.getDoctorByAdmin(3);
        //PromoItemControllerTest.updateDoctorByAdmin(2);
        //PromoItemControllerTest.deleteDoctorByAdmin(4);

        //SampleControllerTest.createDoctorByAdmin();
        //SampleControllerTest.getAllDoctorsByAdmin();
        //SampleControllerTest.getDoctorByAdmin(3);
        //SampleControllerTest.updateDoctorByAdmin(2);
        //SampleControllerTest.deleteDoctorByAdmin(4);


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
