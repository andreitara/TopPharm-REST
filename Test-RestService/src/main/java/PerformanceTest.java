import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Calendar;

/**
 * Created by Andrei on 10/14/2015.
 */
public class PerformanceTest {

    public static void getTest() throws JsonProcessingException, InterruptedException {

        Thread[] threads = new Thread[20];
        for (int k = 0; k < threads.length; k++) {
            threads[k] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 30; i++) {
                        System.out.println("Thread " + i);
                        try {
                            MessageControllerTest.getAllRepresentatives("id", true);
                            MessageControllerTest.getHasUnreadMessages();
                            MessageControllerTest.getMessageBiDirectionalFROM(1002, 0, 2);
                            MessageControllerTest.getLatestMessageBiDirectionalFROM(1002, 1);
                            MessageControllerTest.getAllRepresentatives("id", true);
                            MessageControllerTest.getHasUnreadMessages();
                            MessageControllerTest.getMessageBiDirectionalFROM(1002,0,2);
                            MessageControllerTest.getLatestMessageBiDirectionalFROM(1002,1);
                            MessageControllerTest.getAllRepresentatives("id", true);
                            MessageControllerTest.getHasUnreadMessages();
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
