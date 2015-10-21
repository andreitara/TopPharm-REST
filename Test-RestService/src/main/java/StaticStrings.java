/**
 * Created by Andrei on 10/3/2015.
 */
public class StaticStrings {

    public static String REST_IP_PORT = "http://api.toppharm.md/:81";

    public static int ADMIN_ID = 1;
    public static int USER_ID = 2;

    public static String ADMIN_AUTH_TOKEN = "6cb5e49b-a21b-47a4-ad22-62ef8b5e44ba";
    public static String USER_AUTH_TOKEN =  "5d9cf22b-4688-4754-8a37-ca6d63f97f8f";

    public static String LOGIN_URI =  REST_IP_PORT + "/toppharm/user/login";
    public static String LOGOUT_URI = REST_IP_PORT + "/toppharm/user/logout";

    public static String GET_USER_PERMISSION_URI = REST_IP_PORT + "/toppharm/user/permission/{userID}";
    public static String GET_MY_PERMISSION_URI =   REST_IP_PORT + "/toppharm/user/permission/my";
    public static String UPDATE_PERMISSION_URI =   REST_IP_PORT + "/toppharm/user/permission/update/{userID}";

    public static String DELETE_USER_URI =   REST_IP_PORT + "/toppharm/user/delete/{id}";
    public static String UPDATE_USER_URI =   REST_IP_PORT + "/toppharm/user/update";
    public static String CREATE_USER_URI =   REST_IP_PORT + "/toppharm/user/create";
    public static String GET_USER_URI =      REST_IP_PORT + "/toppharm/user/{id}";
    public static String GET_ALL_USERS_URI = REST_IP_PORT + "/toppharm/user/all";
    public static String ADD_DOCTOR_USER_URI =     REST_IP_PORT + "/toppharm/user/{userID}/doctor/add/{doctorID}";
    public static String DELETE_DOCTOR_USER_URI =  REST_IP_PORT + "/toppharm/user/{userID}/doctor/delete/{doctorID}";
    public static String GET_DOCTORS_USER_URI =    REST_IP_PORT + "/toppharm/user/{userID}/doctor/all";
    public static String GET_USER_TASKS_BY_DATE_URI =    REST_IP_PORT + "/toppharm/user/{userID}/task/from/{fromDate}/to/{toDate}";
    public static String GET_USER_TASKS_BY_STATUS_URI =  REST_IP_PORT + "/toppharm/user/{userID}/task/status/{status}";
    public static String GET_USER_TASKS_BY_TYPE_URI =    REST_IP_PORT + "/toppharm/user/{userID}/task/type/{type}";

    public static String GET_ALL_INSTITUTIONS_URI = REST_IP_PORT + "/toppharm/medical/institution/all";
    public static String GET_INSTITUTION_URI =      REST_IP_PORT + "/toppharm/medical/institution/{id}";
    public static String CREATE_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/medical/institution/create";
    public static String UPDATE_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/medical/institution/update";
    public static String DELETE_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/medical/institution/delete/{id}";
    public static String GET_INSTITUTIONS_BY_PART_OF_NAME =  REST_IP_PORT + "/toppharm/medical/institution/name/{name}";
    public static String GET_INSTITUTIONS_BY_CITY_URI =      REST_IP_PORT + "/toppharm/medical/institution/city/{city}";
    public static String GET_INSTITUTIONS_BY_DISTRICT_URI =  REST_IP_PORT + "/toppharm/medical/institution/city/{city}/district/{district}";
    public static String GET_INSTITUTIONS_BY_STATE_URI =     REST_IP_PORT + "/toppharm/medical/institution/state/{state}";

    public static String GET_INSTITUTION_ADDRESS_URI =      REST_IP_PORT + "/toppharm/medical/institution/{institutionID}/address/";
    public static String CREATE_INSTITUTION_ADDRESS_URI =   REST_IP_PORT + "/toppharm/medical/institution/{institutionID}/address/create";
    public static String UPDATE_INSTITUTION_ADDRESS_URI =   REST_IP_PORT + "/toppharm/medical/institution/{institutionID}/address/update";

    public static String GET_ALL_DOCTORS_URI = REST_IP_PORT + "/toppharm/medical/doctor/all";
    public static String GET_DOCTOR_URI =      REST_IP_PORT + "/toppharm/medical/doctor/{id}";
    public static String CREATE_DOCTOR_URI =   REST_IP_PORT + "/toppharm/medical/doctor/create";
    public static String UPDATE_DOCTOR_URI =   REST_IP_PORT + "/toppharm/medical/doctor/update";
    public static String DELETE_DOCTOR_URI =   REST_IP_PORT + "/toppharm/medical/doctor/delete/{id}";
    public static String GET_DOCTORS_BY_PART_OF_NAME_URI =   REST_IP_PORT + "/toppharm/medical/doctor/name/{name}";
    public static String GET_DOCTORS_BY_SPECIALITY_URI =     REST_IP_PORT + "/toppharm/medical/doctor/speciality/{speciality}";
    public static String GET_DOCTORS_BY_INSTITUTION_URI =    REST_IP_PORT + "/toppharm/medical/doctor/institution/{institutionID}";

    public static String GET_ALL_OFFICES_OF_DOCTOR_URI = REST_IP_PORT + "/toppharm/medical/doctor/{doctorID}/office/all";
    public static String GET_OFFICE_URI =                REST_IP_PORT + "/toppharm/medical/doctor/{doctorID}/office/{id}";
    public static String CREATE_OFFICE_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/medical/doctor/{doctorID}/office/create/institution/{institutionID}";
    public static String UPDATE_OFFICE_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/medical/doctor/{doctorID}/office/update/institution/{institutionID}";
    public static String DELETE_OFFICE_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/medical/doctor/{doctorID}/office/delete/{id}";

    public static String GET_ALL_PRODUCTS_URI = REST_IP_PORT + "/toppharm/medical/product/all";
    public static String GET_PRODUCT_URI =      REST_IP_PORT + "/toppharm/medical/product/{id}";
    public static String CREATE_PRODUCT_URI =   REST_IP_PORT + "/toppharm/medical/product/create";
    public static String UPDATE_PRODUCT_URI =   REST_IP_PORT + "/toppharm/medical/product/update";
    public static String DELETE_PRODUCT_URI =   REST_IP_PORT + "/toppharm/medical/product/delete/{id}";
    public static String GET_PRODUCT_BY_PART_OF_CATEGORY_URI =  REST_IP_PORT + "/toppharm/medical/product/category/{category}";
    public static String GET_PRODUCT_BY_PART_OF_NAME_URI =      REST_IP_PORT + "/toppharm/medical/product/name/{name}";

    public static String GET_ALL_PRODUCT_OBJECTIVES_URI = REST_IP_PORT + "/toppharm/medical/product/{productID}/objective/all";
    public static String GET_PRODUCT_OBJECTIVE_URI =      REST_IP_PORT + "/toppharm/medical/product/{productID}/objective/{id}";
    public static String CREATE_PRODUCT_OBJECTIVE_URI =   REST_IP_PORT + "/toppharm/medical/product/{productID}/objective/create";
    public static String UPDATE_PRODUCT_OBJECTIVE_URI =   REST_IP_PORT + "/toppharm/medical/product/{productID}/objective/update";
    public static String DELETE_PRODUCT_OBJECTIVE_URI =   REST_IP_PORT + "/toppharm/medical/product/{productID}/objective/delete/{id}";

    public static String GET_ALL_TASKS_URI = REST_IP_PORT + "/toppharm/task/all";
    public static String GET_TASK_URI =      REST_IP_PORT + "/toppharm/task/{id}";
    public static String CREATE_TASK_URI =   REST_IP_PORT + "/toppharm/task/create";
    public static String UPDATE_TASK_URI =   REST_IP_PORT + "/toppharm/task/update";
    public static String DELETE_TASK_URI =   REST_IP_PORT + "/toppharm/task/delete/{id}";

    public static String ADD_INSTITUTION_TASK_URI =     REST_IP_PORT + "/toppharm/task/{taskID}/institution/add/{institutionID}";
    public static String UPDATE_INSTITUTION_TASK_URI =  REST_IP_PORT + "/toppharm/task/{taskID}/institution/update/{institutionID}";
    public static String DELETE_INSTITUTION_TASK_URI =  REST_IP_PORT + "/toppharm/task/{taskID}/institution/delete";
    public static String GET_INSTITUTION_TASK_URI =     REST_IP_PORT + "/toppharm/task/{taskID}/institution/";

    public static String ADD_DOCTOR_TASK_URI =     REST_IP_PORT + "/toppharm/task/{taskID}/doctor/add/{doctorID}";
    public static String DELETE_DOCTOR_TASK_URI =  REST_IP_PORT + "/toppharm/task/{taskID}/doctor/delete/{doctorID}";
    public static String GET_DOCTORS_TASK_URI =    REST_IP_PORT + "/toppharm/task/{taskID}/doctor/all";

    public static String ADD_USER_TASK_URI =     REST_IP_PORT + "/toppharm/task/{taskID}/user/add/{userID}";
    public static String DELETE_USER_TASK_URI =  REST_IP_PORT + "/toppharm/task/{taskID}/user/delete/{userID}";
    public static String GET_USERS_TASK_URI =    REST_IP_PORT + "/toppharm/task/{taskID}/user/all";

    public static String ADD_PRODUCT_TASK_URI =     REST_IP_PORT + "/toppharm/task/{taskID}/product/add/{productID}";
    public static String DELETE_PRODUCT_TASK_URI =  REST_IP_PORT + "/toppharm/task/{taskID}/product/delete/{productID}";
    public static String GET_PRODUCTS_TASK_URI =    REST_IP_PORT + "/toppharm/task/{taskID}/product/all";

    public static String ADD_MESSAGE =            REST_IP_PORT + "/toppharm/message/from/{fromID}/to/{toID}/add";
    public static String GET_MESSAGES_FROM_TO =   REST_IP_PORT + "/toppharm/message/from/{fromID}/to/{toID}/start/{start}/end/{end}";
    public static String GET_MESSAGES_USER_USER = REST_IP_PORT + "/toppharm/message/user/{user1ID}/user/{user2ID}/start/{start}/end/{end}";

}
