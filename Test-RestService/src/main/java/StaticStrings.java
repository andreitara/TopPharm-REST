/**
 * Created by Andrei on 10/3/2015.
 */
public class StaticStrings {

    public static String REST_IP_PORT = "http://localhost:81";
    //public static String REST_IP_PORT ="http://api.toppharm.md";

    public static int ADMIN_ID = 1;
    public static int USER_ID = 2;

    //User  : 6cc37c5631b50b5fe3e0d70736ac4de7b51eecfaaf0575d1e413f0104fb3b4d71a0a2b9d9fb0b99b0305eb918dd45b90
    //Admin : b2bc519c98474e1f69a00b484ddffbd50362b9dacdd6baa964db7b8f383b26238160900274ec03560f0eea3ca9e69122
    public static String ADMIN_AUTH_TOKEN = "b2bc519c98474e1f69a00b484ddffbd50362b9dacdd6baa964db7b8f383b2623264b7375a5894bf732b52f67711d3623";

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
    public static String GET_ALL_USERS_URI = REST_IP_PORT + "/toppharm/v1/user/all/{byField}/{ascending}";
    public static String ADD_DOCTOR_USER_URI =     REST_IP_PORT + "/toppharm/v1/user/{userID}/doctor/add/{doctorID}";
    public static String DELETE_DOCTOR_USER_URI =  REST_IP_PORT + "/toppharm/v1/user/{userID}/doctor/delete/{doctorID}";
    public static String GET_DOCTORS_USER_URI =    REST_IP_PORT + "/toppharm/v1/user/{userID}/doctor/all/{byField}/{ascending}";
    public static String GET_USER_TASKS_BY_DATE_URI =    REST_IP_PORT + "/toppharm/v1/user/{userID}/task/from/{fromDate}/to/{toDate}";
    public static String GET_USER_TASKS_BY_STATUS_URI =  REST_IP_PORT + "/toppharm/v1/user/{userID}/task/status/{status}";
    public static String GET_USER_TASKS_BY_TYPE_URI =    REST_IP_PORT + "/toppharm/v1/user/{userID}/task/type/{type}";

    public static String GET_ALL_INSTITUTIONS_URI = REST_IP_PORT + "/toppharm/v1/medical/institution/all/{byField}/{ascending}";
    public static String GET_INSTITUTION_URI =      REST_IP_PORT + "/toppharm/v1/medical/institution/{id}";
    public static String CREATE_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/v1/medical/institution/create";
    public static String CREATE_INSTITUTION_LIST_URI =   REST_IP_PORT + "/toppharm/v1/medical/institution/create/list";
    public static String UPDATE_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/v1/medical/institution/update";
    public static String DELETE_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/v1/medical/institution/delete/{id}";
    public static String GET_INSTITUTIONS_BY_PART_OF_NAME =  REST_IP_PORT + "/toppharm/v1/medical/institution/name/{name}/{byField}/{ascending}";
    public static String GET_INSTITUTIONS_BY_CITY_URI =      REST_IP_PORT + "/toppharm/v1/medical/institution/city/{city}/{byField}/{ascending}";
    public static String GET_INSTITUTIONS_BY_TYPE_URI =      REST_IP_PORT + "/toppharm/v1/medical/institution/type/{typeID}/{byField}/{ascending}";
    public static String GET_INSTITUTIONS_BY_CITY_AND_TYPE_URI =      REST_IP_PORT + "/toppharm/v1/medical/institution/city/{city}/type/{typeID}/{byField}/{ascending}";

    public static String GET_ALL_INSTITUTION_TYPES_URI = REST_IP_PORT + "/toppharm/v1/medical/institutiontype/all/{byField}/{ascending}";
    public static String GET_INSTITUTION_TYPE_URI =      REST_IP_PORT + "/toppharm/v1/medical/institutiontype/{id}";
    public static String CREATE_INSTITUTION_TYPE_URI =   REST_IP_PORT + "/toppharm/v1/medical/institutiontype/create";
    public static String UPDATE_INSTITUTION_TYPE_URI =   REST_IP_PORT + "/toppharm/v1/medical/institutiontype/update";
    public static String DELETE_INSTITUTION_TYPE_URI =   REST_IP_PORT + "/toppharm/v1/medical/institutiontype/delete/{id}";

    public static String GET_ALL_CPC_URI = REST_IP_PORT + "/toppharm/v1/report/cpc/{userID}/{byField}/{ascending}";
    public static String GET_ALL_STATISTICS_URI = REST_IP_PORT + "/toppharm/v1/report/statistics/{byField}/{ascending}";

    public static String GET_ALL_DOCTORS_URI = REST_IP_PORT + "/toppharm/v1/medical/doctor/all/{byField}/{ascending}";
    public static String GET_DOCTOR_URI =      REST_IP_PORT + "/toppharm/v1/medical/doctor/{id}";
    public static String CREATE_DOCTOR_URI =   REST_IP_PORT + "/toppharm/v1/medical/doctor/create";
    public static String CREATE_DOCTOR_LIST_URI =   REST_IP_PORT + "/toppharm/v1/medical/doctor/create/list";
    public static String UPDATE_DOCTOR_URI =   REST_IP_PORT + "/toppharm/v1/medical/doctor/update";
    public static String DELETE_DOCTOR_URI =   REST_IP_PORT + "/toppharm/v1/medical/doctor/delete/{id}";
    public static String GET_LAST_DATE_DOCTOR_URI =      REST_IP_PORT + "/toppharm/v1/medical/doctor/latestVisit/{doctorID}/{userID}";
    public static String GET_DOCTORS_BY_PART_OF_NAME_URI =   REST_IP_PORT + "/toppharm/v1/medical/doctor/name/{name}";
    public static String GET_DOCTORS_BY_SPECIALITY_URI =     REST_IP_PORT + "/toppharm/v1/medical/doctor/speciality/{specialityID}/{byField}/{ascending}";
    public static String GET_DOCTORS_BY_INSTITUTION_URI =    REST_IP_PORT + "/toppharm/v1/medical/doctor/institution/{institutionID}/{byField}/{ascending}";

    public static String GET_ALL_DOCTOR_INSTITUTIONS_URI = REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/institution/all";
    public static String ADD_DOCTOR_INSTITUTION_URI =      REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/institution/add";
    public static String DELETE_DOCTOR_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/institution/delete";

    public static String GET_ALL_USER_INSTITUTIONS_URI = REST_IP_PORT + "/toppharm/v1/medical/user/{userID}/institution/all/{byField}/{ascending}";
    public static String GET_ALL_USER_INSTITUTIONS_BY_TYPE_URI = REST_IP_PORT + "/toppharm/v1/medical/user/{userID}/institution/all/type/{typeID}/{byField}/{ascending}";
    public static String ADD_USER_INSTITUTION_URI =      REST_IP_PORT + "/toppharm/v1/medical/user/{userID}/institution/add";
    public static String DELETE_USER_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/v1/medical/user/{userID}/institution/delete";
    public static String GET_LAST_VISIT_INSTITUTIONS_URI = REST_IP_PORT + "/toppharm/v1/medical/institution/latestVisit/{institutionID}/{userID}";

    public static String GET_ALL_USER_DOCTORS_URI = REST_IP_PORT + "/toppharm/v1/medical/user/{userID}/doctor/all/{byField}/{ascending}";
    public static String GET_ALL_USER_DOCTORS_BY_TYPE_URI = REST_IP_PORT + "/toppharm/v1/medical/user/{userID}/doctor/all/speciality/{specialityID}/{byField}/{ascending}";


    public static String GET_ALL_SPECIALITY_URI =  REST_IP_PORT + "/toppharm/v1/medical/speciality/all";
    public static String GET_SPECIALITY_URI =      REST_IP_PORT + "/toppharm/v1/medical/speciality/{id}";
    public static String CREATE_SPECIALITY_URI =   REST_IP_PORT + "/toppharm/v1/medical/speciality/create";
    public static String UPDATE_SPECIALITY_URI =   REST_IP_PORT + "/toppharm/v1/medical/speciality/update";
    public static String DELETE_SPECIALITY_URI =   REST_IP_PORT + "/toppharm/v1/medical/speciality/delete/{id}";

    public static String GET_ALL_STATUS_URI =  REST_IP_PORT + "/toppharm/v1/user/status/all/{byField}/{ascending}";
    public static String GET_STATUS_URI =      REST_IP_PORT + "/toppharm/v1/user/status/{id}";
    public static String CREATE_STATUS_URI =   REST_IP_PORT + "/toppharm/v1/user/status/create";
    public static String UPDATE_STATUS_URI =   REST_IP_PORT + "/toppharm/v1/user/status/update";
    public static String DELETE_STATUS_URI =   REST_IP_PORT + "/toppharm/v1/user/status/delete/{id}";

    public static String UPDATE_USER_STATUS_URI =   REST_IP_PORT + "/toppharm/v1/user/update/{userID}/status/{statusID}";

    public static String GET_ALL_GENERAL_TYPE_URI =  REST_IP_PORT + "/toppharm/v1/medical/generaltype/all";
    public static String GET_GENERAL_TYPE_URI =      REST_IP_PORT + "/toppharm/v1/medical/generaltype/{id}";
    public static String CREATE_GENERAL_TYPE_URI =   REST_IP_PORT + "/toppharm/v1/medical/generaltype/create";
    public static String UPDATE_GENERAL_TYPE_URI =   REST_IP_PORT + "/toppharm/v1/medical/generaltype/update";
    public static String DELETE_GENERAL_TYPE_URI =   REST_IP_PORT + "/toppharm/v1/medical/generaltype/delete/{id}";

    public static String GET_ALL_HABITS_OF_DOCTOR_URI = REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/habit/all";
    public static String ADD_HABIT_URI =                REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/habit/add";
    public static String DELETE_HABIT_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/habit/delete/{habitID}";

    public static String GET_ALL_PERSONAL_INFOS_OF_DOCTOR_URI = REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/personalinfo/all";
    public static String ADD_PERSONAL_INFO_URI =                REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/personalinfo/add";
    public static String DELETE_PERSONAL_INFO_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/personalinfo/delete/{habitID}";

    public static String GET_ALL_OFFICES_OF_DOCTOR_URI = REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/office/all";
    public static String GET_OFFICE_URI =                REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/office/{id}";
    public static String CREATE_OFFICE_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/office/create/institution/{institutionID}";
    public static String UPDATE_OFFICE_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/office/update/institution/{institutionID}";
    public static String DELETE_OFFICE_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/v1/medical/doctor/{doctorID}/office/delete/{id}";

    public static String GET_ALL_PRODUCTS_URI = REST_IP_PORT + "/toppharm/v1/medical/product/all/{byField}/{ascending}";
    public static String GET_PRODUCT_URI =      REST_IP_PORT + "/toppharm/v1/medical/product/{id}";
    public static String CREATE_PRODUCT_URI =   REST_IP_PORT + "/toppharm/v1/medical/product/create";
    public static String UPDATE_PRODUCT_URI =   REST_IP_PORT + "/toppharm/v1/medical/product/update";
    public static String DELETE_PRODUCT_URI =   REST_IP_PORT + "/toppharm/v1/medical/product/delete/{id}";
    public static String GET_ALL_PRODUCTS_BY_PRIORITY_URI = REST_IP_PORT + "/toppharm/v1/medical/product/all/priority/{priority}/{byField}/{ascending}";


    public static String GET_ALL_PRODUCT_OBJECTIVES_URI = REST_IP_PORT + "/toppharm/v1/medical/product/{productID}/objective/all";
    public static String GET_PRODUCT_OBJECTIVE_URI =      REST_IP_PORT + "/toppharm/v1/medical/product/{productID}/objective/{id}";
    public static String CREATE_PRODUCT_OBJECTIVE_URI =   REST_IP_PORT + "/toppharm/v1/medical/product/{productID}/objective/create";
    public static String UPDATE_PRODUCT_OBJECTIVE_URI =   REST_IP_PORT + "/toppharm/v1/medical/product/{productID}/objective/update";
    public static String DELETE_PRODUCT_OBJECTIVE_URI =   REST_IP_PORT + "/toppharm/v1/medical/product/{productID}/objective/delete/{id}";


    public static String GET_ALL_TASKS_URI =  REST_IP_PORT + "/toppharm/v1/task/all/{byField}/{ascending}";
    public static String GET_TASK_URI =      REST_IP_PORT + "/toppharm/v1/task/{id}";
    public static String CREATE_TASK_URI =   REST_IP_PORT + "/toppharm/v1/task/create";
    public static String UPDATE_TASK_URI =   REST_IP_PORT + "/toppharm/v1/task/update";
    public static String EXECUTE_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/execute/{id}";

    public static String GET_ALL_TASKS_BY_CATEGORY_URI =  REST_IP_PORT + "/toppharm/v1/task/all/category/{category}/{byField}/{ascending}";
    public static String GET_ALL_TASKS_IS_CAPITAL_URI =   REST_IP_PORT + "/toppharm/v1/task/all/capital/{isCapital}/{byField}/{ascending}";
    public static String GET_ALL_TASKS_IS_SUBMITTED_URI = REST_IP_PORT + "/toppharm/v1/task/all/submitted/{isSubmitted}/{byField}/{ascending}";
    public static String GET_ALL_TASKS_BY_TYPE_URI =      REST_IP_PORT + "/toppharm/v1/task/all/type/{typeID}/{byField}/{ascending}";

    public static String ADD_TASK_COMMENT_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/comment/add";
    public static String GET_TASK_COMMENT_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/comment/all";
    public static String DELETE_TASK_COMMENT_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/comment/delete/{commentID}";
    public static String GET_TASK_HISTORY_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/history/all";

    public static String GET_ALL_TASKS_BY_USER_URI =             REST_IP_PORT + "/toppharm/v1/task/all/user/{userID}/{byField}/{ascending}";
    public static String GET_ALL_TASKS_BY_USER_BY_CATEGORY_URI = REST_IP_PORT + "/toppharm/v1/task/all/user/{userID}/category/{category}/{byField}/{ascending}";
    public static String GET_ALL_TASKS_BY_USER_BY_TYPE_URI =     REST_IP_PORT + "/toppharm/v1/task/all/user/{userID}/type/{typeID}/{byField}/{ascending}";

    public static String GET_ALL_PROMO_ITEMS_URI = REST_IP_PORT + "/toppharm/v1/medical/promoitem/all";
    public static String GET_PROMO_ITEM_URI =      REST_IP_PORT + "/toppharm/v1/medical/promoitem/{id}";
    public static String CREATE_PROMO_ITEMS_URI =   REST_IP_PORT + "/toppharm/v1/medical/promoitem/create";
    public static String UPDATE_PROMO_ITEMS_URI =   REST_IP_PORT + "/toppharm/v1/medical/promoitem/update";
    public static String DELETE_PROMO_ITEMS_URI =   REST_IP_PORT + "/toppharm/v1/medical/promoitem/delete/{id}";

    public static String GET_ALL_SAMPLES_URI = REST_IP_PORT + "/toppharm/v1/medical/sample/all";
    public static String GET_SAMPLE_URI =      REST_IP_PORT + "/toppharm/v1/medical/sample/{id}";
    public static String CREATE_SAMPLE_URI =   REST_IP_PORT + "/toppharm/v1/medical/sample/create";
    public static String UPDATE_SAMPLE_URI =   REST_IP_PORT + "/toppharm/v1/medical/sample/update";
    public static String DELETE_SAMPLE_URI =   REST_IP_PORT + "/toppharm/v1/medical/sample/delete/{id}";

    public static String ADD_INSTITUTION_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/institution/add/{institutionID}";
    public static String UPDATE_INSTITUTION_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/institution/update/{institutionID}";
    public static String DELETE_INSTITUTION_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/institution/delete";
    public static String GET_INSTITUTION_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/institution/";

    public static String ADD_DOCTOR_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/attendee/add/{doctorID}";
    public static String ADD_DOCTOR_TASK_LIST_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/attendee/add/list";
    public static String DELETE_DOCTOR_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/attendee/delete/{doctorID}";
    public static String GET_DOCTORS_TASK_URI =    REST_IP_PORT + "/toppharm/v1/task/{taskID}/attendee/all/{byField}/{ascending}";

    public static String ADD_USER_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/user/add/{userID}";
    public static String DELETE_USER_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/user/delete/{userID}";
    public static String GET_USERS_TASK_URI =    REST_IP_PORT + "/toppharm/v1/task/{taskID}/user/all";

    public static String ADD_PRODUCT_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/product/add/{productID}";
    public static String DELETE_PRODUCT_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/product/delete/{productID}";
    public static String GET_PRODUCTS_TASK_URI =    REST_IP_PORT + "/toppharm/v1/task/{taskID}/product/all/{byField}/{ascending}";

    public static String ADD_MESSAGE =              REST_IP_PORT + "/toppharm/v1/message/to/{toID}/add";
    public static String GET_REPRESENTATIVES =      REST_IP_PORT + "/toppharm/v1/message//representative/{byField}/{ascending}";
    public static String GET_HAS_UNREAD_MESSAGES =  REST_IP_PORT + "/toppharm/v1/message//representative/unreadMessages";
    public static String GET_MESSAGES_FROM =        REST_IP_PORT + "/toppharm/v1/message/from/{fromID}/{firstResult}/{maxResult}";
    public static String GET_LATEST_MESSAGES_FROM = REST_IP_PORT + "/toppharm/v1/message/from/{fromID}/latest/{maxResult}";

    public static String GET_MESSAGES_FROM_TO =   REST_IP_PORT + "/toppharm/v1/message/from/{fromID}/to/{toID}/start/{start}/end/{end}";
    public static String GET_MESSAGES_USER_USER = REST_IP_PORT + "/toppharm/v1/message/user/{user1ID}/user/{user2ID}/start/{start}/end/{end}";

    public static String ADD_MEMO_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/memo/add";
    public static String DELETE_MEMO_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/memo/delete/{memoID}";
    public static String GET_MEMOS_TASK_URI =    REST_IP_PORT + "/toppharm/v1/task/{taskID}/memo/all";

    public static String ADD_SAMPLE_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/sample/add/{sampleID}";
    public static String DELETE_SAMPLE_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/sample/delete/{sampleID}";
    public static String GET_SAMPLES_TASK_URI =    REST_IP_PORT + "/toppharm/v1/task/{taskID}/sample/all";

    public static String ADD_OBJECTIVE_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/objective/add";
    public static String DELETE_OBJECTIVE_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/objective/delete/{objectiveID}";
    public static String GET_OBJECTIVES_TASK_URI =    REST_IP_PORT + "/toppharm/v1/task/{taskID}/objective/all";

    public static String ADD_PROMO_ITEM_TASK_URI =     REST_IP_PORT + "/toppharm/v1/task/{taskID}/promoitem/add/{promoitemID}";
    public static String DELETE_PROMO_ITEM_TASK_URI =  REST_IP_PORT + "/toppharm/v1/task/{taskID}/promoitem/delete/{promoitemID}";
    public static String GET_PROMO_ITEMS_TASK_URI =    REST_IP_PORT + "/toppharm/v1/task/{taskID}/promoitem/all";
}
