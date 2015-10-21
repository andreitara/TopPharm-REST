import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Calendar;

/**
 * Created by Andrei on 10/14/2015.
 */
public class PerformanceTest {

    public static void getTest() throws JsonProcessingException, InterruptedException {

        Thread[] threads = new Thread[10];
        for (int k = 0; k < threads.length; k++) {
            threads[k] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        System.out.println("Thread " + i);
                        try {
                            TaskControllerTest.getTaskByAdmin(1);
                            InstitutionsControllerTest.getInstitutionsByAdmin(4);
                            InstitutionAddressControllerTest.getInstitutionAddressByAdmin(4);
                            ProductControllerTest.getProductByAdmin(2);
                            ProductObjectiveControllerTest.getAllProductObjectivesByAdmin(2);
                            DoctorControllerTest.getAllDoctorsByAdmin();
                            PermissionControllerTest.getAdminMyPermission();
                            UserControllerTest.getUserByAdmin(3);
                            DoctorTaskControllerTest.getAllDoctorsTask(2);
                            UserTaskControllerTest.getAllUsersTask(1);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        for (int k=0; k<threads.length ; k++){
            threads[k].start();
        }

        Calendar start = Calendar.getInstance();
        for (int k=0; k<threads.length; k++){
            threads[k].join();
        }
        Calendar end = Calendar.getInstance();
        Long time = end.getTimeInMillis() - start.getTimeInMillis();
        System.out.println(time);
    }
}
