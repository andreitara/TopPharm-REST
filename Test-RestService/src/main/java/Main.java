import com.fasterxml.jackson.core.JsonProcessingException;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.institution.attributes.InstitutionType;
import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.user.permission.Permission;
import md.pharm.restservice.service.doctor.DoctorController;
import md.pharm.restservice.service.task.DoctorTaskController;
import net.sourceforge.jtds.jdbc.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import sun.util.calendar.LocalGregorianCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Andrei on 9/22/2015.
 */
public class Main {

    public static void main(String[] args) throws JsonProcessingException, InterruptedException {
        //main2();
        //LoginControllerTest.loginAdmin();
        //LoginControllerTest.logoutAdmin();
        //LoginControllerTest.changePasswordAdmin();

        //PermissionControllerTest.getAdminUserPermission(3);
        //PermissionControllerTest.getAdminMyPermission();
        //PermissionControllerTest.updatePermissionsByAdmin(3);

        //UserControllerTest.createUserByAdmin();
        //UserControllerTest.updateUserByAdmin(1002);
        //UserControllerTest.deleteUserByAdmin(2);
        //UserControllerTest.getUserByAdmin(1);
        //UserControllerTest.getAllUsersByAdmin("name", true);
        //DoctorUserControllerTest.addDoctorToUser(1,1);
        //DoctorUserControllerTest.deleteDoctorFromUser(4,1);
        //DoctorUserControllerTest.getAllDoctorsFromUser(1, "name", true);

        //InstitutionsControllerTest.createInstitutionByAdmin();
        //InstitutionsControllerTest.getInstitutionsByAdmin(1);
        //InstitutionsControllerTest.getAllInstitutionsByAdmin("longName", false);
        //InstitutionsControllerTest.updateInstitutionByAdmin(4);
        //InstitutionsControllerTest.deleteInstitutionByAdmin(1);
        //InstitutionsControllerTest.getAllInstitutionsByCity("rezina", "longName", true);
        //InstitutionsControllerTest.getAllInstitutionsByPartName("name", "longName", false);
        //InstitutionsControllerTest.getAllInstitutionsByType(2, "longName", true);
        //InstitutionsControllerTest.getAllInstitutionsByCityAndType("chisinau", 1, "longName", true);

        //InstitutionTypeControllerTest.createDoctorByAdmin();
        //InstitutionTypeControllerTest.getAllDoctorsByAdmin();
        //InstitutionTypeControllerTest.getDoctorByAdmin(3);
        //InstitutionTypeControllerTest.updateDoctorByAdmin(3);
        //InstitutionTypeControllerTest.deleteDoctorByAdmin(3);

        //InstitutionAddressControllerTest.getInstitutionAddressByAdmin(4);
        //InstitutionAddressControllerTest.createInstitutionAddressByAdmin(9);
        //InstitutionAddressControllerTest.updateInstitutionAddressByAdmin(5);

        //DoctorControllerTest.getAllCPCByAdmin(1, "name", true);
        //DoctorControllerTest.getAllStatisticsByAdmin("representativeName", true);

        //DoctorControllerTest.createDoctorByAdmin();
        //DoctorControllerTest.updateDoctorByAdmin(1);
        //DoctorControllerTest.getAllDoctorsByAdmin("name", true);
        //DoctorControllerTest.getDoctorByAdmin(25);
        //DoctorControllerTest.deleteDoctorByAdmin(25);
        //DoctorControllerTest.getLastDateDoctorByAdmin(2,1);
        //DoctorControllerTest.getAllDoctorsBySpecialityByAdmin(2);
        //DoctorControllerTest.getAllDoctorsByGeneralTypeByAdmin(1);

        //DoctorControllerTest.addInstitutionsToDoctor(3, 4);
        //DoctorControllerTest.getAllInstitutionsToDoctor(3);
        //DoctorControllerTest.deleteInstitutionsListToDoctor(16);


        //SpecialityController.createDoctorByAdmin();
        //SpecialityController.getAllDoctorsByAdmin();
        //SpecialityController.getDoctorByAdmin(6);
        //SpecialityController.updateDoctorByAdmin(3);
        //SpecialityController.deleteDoctorByAdmin(3);

        //GeneralTypeControllerTest.createDoctorByAdmin();
        //GeneralTypeControllerTest.getAllDoctorsByAdmin();
        //GeneralTypeControllerTest.getDoctorByAdmin(2);
        //GeneralTypeControllerTest.updateDoctorByAdmin(4);
        //GeneralTypeControllerTest.deleteDoctorByAdmin(5);

        //HabbitDoctorControllerTest.addHabitToTask(2);
        //HabbitDoctorControllerTest.getHabitsFromTask(2);
        //HabbitDoctorControllerTest.deleteHabitFromTask(2,2);

        //PersonalInfoDoctorControllerTest.addInfoToDoctor(2);
        //PersonalInfoDoctorControllerTest.getInfosFromTask(2);
        //PersonalInfoDoctorControllerTest.deleteInfoFromTask(2,1);

        //ProductControllerTest.getAllProductsByAdmin("name", false);
        //ProductControllerTest.getAllProductsByPriority("2", "name", true);
        //ProductControllerTest.createProductByAdmin(15);
        //ProductControllerTest.updateDoctorByAdmin(1005);
        //ProductControllerTest.deleteProductByAdmin(1006);
        //ProductControllerTest.getProductByAdmin(1);

        //ProductObjectiveControllerTest.createProductByAdmin(2);
        //ProductObjectiveControllerTest.getAllProductObjectivesByAdmin(2);
        //ProductObjectiveControllerTest.getProductByAdmin(2,2);
        //ProductObjectiveControllerTest.updateProductObjectiveByAdmin(2,2);
        //ProductObjectiveControllerTest.deleteProductObjectiveByAdmin(2,1);

        //TaskControllerTest.createTaskByAdmin();
        //TaskControllerTest.updateTaskByAdmin(13);
        //TaskControllerTest.getAllTasksByAdmin("name", true);
        //TaskControllerTest.getTaskByAdmin(21);
        //TaskControllerTest.deleteTaskByAdmin(1);

        //TaskControllerTest.getAllTasksByCategoryByAdmin("category 2", "speciality.name", true);
        //TaskControllerTest.getAllTasksByTypeByAdmin(2, "name", true);
        //TaskControllerTest.getAllTasksIsCapitalByAdmin(false, "name", false);

        //TaskControllerTest.getAllTasksByUserByAdmin(1, "name", true);
        //TaskControllerTest.getAllTasksByUserByCategoryByAdmin(1, "category 2", "name", true);
        //TaskControllerTest.getAllTasksByUserByTypeByAdmin(1, 1, "name", true);

        //TaskControllerTest.addTaskCommentByAdmin(2);
        //TaskControllerTest.getTaskCommentByAdmin(2);
        //TaskControllerTest.deleteTaskCommentByAdmin(2,2);
        //TaskControllerTest.getTaskHistoryByAdmin(1);

        //TaskAttributesControllerTest.addMemoToTask(1);
        //TaskAttributesControllerTest.getMemosFromTask(1);
        //TaskAttributesControllerTest.deleteMemoFromTask(1,3);

        //TaskAttributesControllerTest.addObjectiveToTask(1);
        //TaskAttributesControllerTest.getObjectivesFromTask(1);
        //TaskAttributesControllerTest.deleteObjectiveFromTask(1,2);

        //TaskAttributesControllerTest.addSampleToTask(1,1);
        //TaskAttributesControllerTest.getSamplesFromTask(1);
        //TaskAttributesControllerTest.deleteSampleFromTask(1,1);

        //TaskAttributesControllerTest.addPromoItemToTask(1,1);
        //TaskAttributesControllerTest.getPromoItemsFromTask(1);
        //TaskAttributesControllerTest.deletePromoItemFromTask(1,1);

        //PromoItemControllerTest.createDoctorByAdmin();
        //PromoItemControllerTest.getAllDoctorsByAdmin();
        //PromoItemControllerTest.getDoctorByAdmin(3);
        //PromoItemControllerTest.updateDoctorByAdmin(3);
        //PromoItemControllerTest.deleteDoctorByAdmin(3);

        //SampleControllerTest.createDoctorByAdmin();
        //SampleControllerTest.getAllDoctorsByAdmin();
        //SampleControllerTest.getDoctorByAdmin(3);
        //SampleControllerTest.updateDoctorByAdmin(3);
        //SampleControllerTest.deleteDoctorByAdmin(3);


        //DoctorTaskControllerTest.addDoctorToTask(2,2);
        //DoctorTaskControllerTest.deleteDoctorToTask(2,3);
        //DoctorTaskControllerTest.getAllDoctorsTask(2,"name", true);

        //ProductTaskControllerTest.addProductToTask(1009,1005);
        //ProductTaskControllerTest.getAllProductsTask(1009, "priority", true);
        //ProductTaskControllerTest.deleteProductTask(1009,1005);

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

        //MessageControllerTest.addMessageFromUser(1);
        //MessageControllerTest.addMessageFromAdmin(1002);
        //MessageControllerTest.getAllRepresentatives("id", true);
        //MessageControllerTest.getHasUnreadMessages();
        //MessageControllerTest.getMessageBiDirectionalFROM(1002,0,2);
        //MessageControllerTest.getLatestMessageBiDirectionalFROM(1002,1);

        //MessageControllerTest.getMessageUniDirectional();
        //MessageControllerTest.getMessageBiDirectional();

        //PerformanceTest.getTest();
    }

    public static void main2(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1451250483000l);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("Year "+mYear);
        System.out.println("Month "+mMonth);
        System.out.println("Day "+mDay);
    }
}
