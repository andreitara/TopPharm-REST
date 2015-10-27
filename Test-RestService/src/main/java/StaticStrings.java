/**
 * Created by Andrei on 10/3/2015.
 */
public class StaticStrings {

    public static String REST_IP_PORT = "http://localhost:81";//"http://api.toppharm.md/:81";

    public static int ADMIN_ID = 1;
    public static int USER_ID = 2;

    public static String ADMIN_AUTH_TOKEN = "ad94cff8-2984-4c27-85da-9feaabf1f0fc";//3257db79-a632-4ad2-a7e4-ff5404315679
    public static String USER_AUTH_TOKEN =  "13be860a-071e-4225-adb7-8b1359ec5591";

    public static String LOGIN_URI =  REST_IP_PORT + "/toppharm/v1/user/login";
    public static String LOGOUT_URI = REST_IP_PORT + "/toppharm/v1/user/logout";
    public static String CHANGE_PASSWORD_URI = REST_IP_PORT + "/toppharm/v1/user/password";

    public static String GET_USER_PERMISSION_URI = REST_IP_PORT + "/toppharm/v1/user/permission/{userID}";
    public static String GET_MY_PERMISSION_URI =   REST_IP_PORT + "/toppharm/v1/user/permission/my";
    public static String UPDATE_PERMISSION_URI =   REST_IP_PORT + "/toppharm/v1/user/permission/update/{userID}";

    public static String DELETE_USER_URI =   REST_IP_PORT + "/toppharm/v1/user/delete/{id}";
    public static String UPDATE_USER_URI =   REST_IP_PORT + "/toppharm/v1/user/update";
    public static String CREATE_USER_URI =   REST_IP_PORT + "/toppharm/v1/user/create";
    public static String GET_USER_URI =      REST_IP_PORT + "/toppharm/v1/user/{id}";
    public static String GET_ALL_USERS_URI = REST_IP_PORT + "/toppharm/v1/user/all";
    public static String ADD_DOCTOR_USER_URI =     REST_IP_PORT + "/toppharm/v1/user/{userID}/doctor/add/{doctorID}";
    public static String DELETE_DOCTOR_USER_URI =  REST_IP_PORT + "/toppharm/v1/user/{userID}/doctor/delete/{doctorID}";
    public static String GET_DOCTORS_USER_URI =    REST_IP_PORT + "/toppharm/v1/user/{userID}/doctor/all";
    public static String GET_USER_TASKS_BY_DATE_URI =    REST_IP_PORT + "/toppharm/v1/user/{userID}/task/from/{fromDate}/to/{toDate}";
    public static String GET_USER_TASKS_BY_STATUS_URI =  REST_IP_PORT + "/toppharm/v1/user/{userID}/task/status/{status}";
    public static String GET_USER_TASKS_BY_TYPE_URI =    REST_IP_PORT + "/toppharm/v1/user/{userID}/task/type/{type}";

    public static String GET_ALL_INSTITUTIONS_URI = REST_IP_PORT + "/toppharm/v1/medical/institution/all";
    public static String GET_INSTITUTION_URI =      REST_IP_PORT + "/toppharm/v1/medical/institution/{id}";
    public static String CREATE_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/v1/medical/institution/create";
    public static String UPDATE_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/v1/medical/institution/update";
    public static String DELETE_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/v1/medical/institution/delete/{id}";
    public static String GET_INSTITUTIONS_BY_PART_OF_NAME =  REST_IP_PORT + "/toppharm/v1/medical/institution/name/{name}";
    public static String GET_INSTITUTIONS_BY_CITY_URI =      REST_IP_PORT + "/toppharm/v1/medical/institution/city/{city}";
    public static String GET_INSTITUTIONS_BY_DISTRICT_URI =  REST_IP_PORT + "/toppharm/v1/medical/institution/city/{city}/district/{district}";
    public static String GET_INSTITUTIONS_BY_STATE_URI =     REST_IP_PORT + "/toppharm/v1/medical/institution/state/{state}";

    public static String GET_INSTITUTION_ADDRESS_URI =      REST_IP_PORT + "/toppharm/v1/medical/institution/{institutionID}/address/";
    public static String CREATE_INSTITUTION_ADDRESS_URI =   REST_IP_PORT + "/toppharm/v1/medical/institution/{institutionID}/address/create";
    public static String UPDATE_INSTITUTION_ADDRESS_URI =   REST_IP_PORT + "/toppharm/v1/medical/institution/{institutionID}/address/update";

    public static String GET_ALL_DOCTORS_URI = REST_IP_PORT + "/toppharm/v1/medical/doctor/all";
    public static String GET_DOCTOR_URI =      REST_IP_PORT + "/toppharm/v1/medical/doctor/{id}";
    public static String CREATE_DOCTOR_URI =   REST_IP_PORT + "/toppharm/v1/medical/doctor/create";
    public static String UPDATE_DOCTOR_URI =   REST_IP_PORT + "/toppharm/v1/medical/doctor/update";
    public static String DELETE_DOCTOR_URI =   REST_IP_PORT + "/toppharm/v1/medical/doctor/delete/{id}";
    public static String GET_DOCTORS_BY_PART_OF_NAME_URI =   REST_IP_PORT + "/toppharm/v1/medical/doctor/name/{name}";
    public static String GET_DOCTORS_BY_SPECIALITY_URI =     REST_IP_PORT + "/toppharm/v1/medical/doctor/speciality/{speciality}";
    public static String GET_DOCTORS_BY_INSTITUTION_URI =    REST_IP_PORT + "/toppharm/v1/medical/doctor/institution/{institutionID}";

    public static String GET_ALL_OFFICES_OF_DOCTOR_URI = REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/office/all";
    public static String GET_OFFICE_URI =                REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/office/{id}";
    public static String CREATE_OFFICE_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/office/create/institution/{institutionID}";
    public static String UPDATE_OFFICE_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/office/update/institution/{institutionID}";
    public static String DELETE_OFFICE_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/office/delete/{id}";

    public static String GET_ALL_PRODUCTS_URI = REST_IP_PORT + "/toppharm/v1/medical/product/all";
    public static String GET_PRODUCT_URI =      REST_IP_PORT + "/toppharm/v1/medical/product/{id}";
    public static String CREATE_PRODUCT_URI =   REST_IP_PORT + "/toppharm/v1/medical/product/create";
    public static String UPDATE_PRODUCT_URI =   REST_IP_PORT + "/toppharm/v1/medical/product/update";
    public static String DELETE_PRODUCT_URI =   REST_IP_PORT + "/toppharm/v1/medical/product/delete/{id}";
    public static String GET_PRODUCT_BY_PART_OF_CATEGORY_URI =  REST_IP_PORT + "/toppharm/v1/medical/product/category/{category}";
    public static String GET_PRODUCT_BY_PART_OF_NAME_URI =      REST_IP_PORT + "/toppharm/v1/medical/product/name/{name}";

    public static String GET_ALL_PRODUCT_OBJECTIVES_URI = REST_IP_PORT + "/toppharm/v1/medical/product/{productID}/objective/all";
    public static String GET_PRODUCT_OBJECTIVE_URI =      REST_IP_PORT + "/toppharm/v1/medical/product/{productID}/objective/{id}";
    public static String CREATE_PRODUCT_OBJECTIVE_URI =   REST_IP_PORT + "/toppharm/v1/medical/product/{productID}/objective/create";
    public static String UPDATE_PRODUCT_OBJECTIVE_URI =   REST_IP_PORT + "/toppharm/v1/medical/product/{productID}/objective/update";
    public static String DELETE_PRODUCT_OBJECTIVE_URI =   REST_IP_PORT + "/toppharm/v1/medical/product/{productID}/objective/delete/{id}";

    public static String GET_ALL_TASKS_URI = REST_IP_PORT + "/toppharm/v1/task/all";
    public static String GET_TASK_URI =      REST_IP_PORT + "/toppharm/v1/task/{id}";
    public static String CREATE_TASK_URI =   REST_IP_PORT + "/toppharm/v1/task/create";
    public static String UPDATE_TASK_URI =   REST_IP_PORT + "/toppharm/v1/task/update";
    public static String DELETE_TASK_URI =   REST_IP_PORT + "/toppharm/v1/task/delete/{id}";
    public static String CLOSE_TASK_URI =    REST_IP_PORT + "/toppharm/v1/task/close/{id}";
    public static String EXECUTE_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/execute/{id}";
    public static String ADD_TASK_COMMENT_URI =  REST_IP_PORT + "/toppharm/v1/task/{id}/comment/add";
    public static String GET_TASK_COMMENT_URI =  REST_IP_PORT + "/toppharm/v1/task/{id}/comment/all";
    public static String GET_TASK_HISTORY_URI =  REST_IP_PORT + "/toppharm/v1/task/{id}/history/all";

    public static String ADD_INSTITUTION_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/institution/add/{institutionID}";
    public static String UPDATE_INSTITUTION_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/institution/update/{institutionID}";
    public static String DELETE_INSTITUTION_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/institution/delete";
    public static String GET_INSTITUTION_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/institution/";

    public static String ADD_DOCTOR_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/doctor/add/{doctorID}";
    public static String DELETE_DOCTOR_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/doctor/delete/{doctorID}";
    public static String GET_DOCTORS_TASK_URI =    REST_IP_PORT + "/toppharm/v1/task/{taskID}/doctor/all";

    public static String ADD_USER_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/user/add/{userID}";
    public static String DELETE_USER_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/user/delete/{userID}";
    public static String GET_USERS_TASK_URI =    REST_IP_PORT + "/toppharm/v1/task/{taskID}/user/all";

    public static String ADD_PRODUCT_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/product/add/{productID}";
    public static String DELETE_PRODUCT_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/product/delete/{productID}";
    public static String GET_PRODUCTS_TASK_URI =    REST_IP_PORT + "/toppharm/v1/task/{taskID}/product/all";

    public static String ADD_MESSAGE =            REST_IP_PORT + "/toppharm/v1/message/from/{fromID}/to/{toID}/add";
    public static String GET_MESSAGES_FROM_TO =   REST_IP_PORT + "/toppharm/v1/message/from/{fromID}/to/{toID}/start/{start}/end/{end}";
    public static String GET_MESSAGES_USER_USER = REST_IP_PORT + "/toppharm/v1/message/user/{user1ID}/user/{user2ID}/start/{start}/end/{end}";

}
