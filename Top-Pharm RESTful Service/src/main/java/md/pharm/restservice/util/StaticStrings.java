package md.pharm.restservice.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrei on 9/26/2015.
 */
public class StaticStrings {
    final public static String PATH_FOR_ALL_CONTROLLERS = ":81/";
    final public static String PORT_FOR_ALL_CONTROLLERS = "/:81/";

    final public static String HEADER_SECURITY_TOKEN = "auth-token";
    final public static String HEADER_USERNAME = "toppharm-username";
    final public static String HEADER_COUNTRY = "toppharm-country";

    final public static List<String> ACCESSIBLE_PAGES = Arrays.asList(PATH_FOR_ALL_CONTROLLERS + "toppharm/user/login");

    final public static String ERROR_PAGES = "toppharm/error";

    final public static String USER_PAGES = PATH_FOR_ALL_CONTROLLERS + "toppharm/user";
    final public static String MEDICAL_ENTITY_PAGES = PATH_FOR_ALL_CONTROLLERS + "toppharm/medical";
    final public static String TASK_PAGES = PATH_FOR_ALL_CONTROLLERS + "toppharm/task";
    final public static String MESSAGES_PAGES = PATH_FOR_ALL_CONTROLLERS + "toppharm/message";

}
