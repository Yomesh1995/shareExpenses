package project.common;

public class ResponseMessages {

    /* COMMON */
    public static int SUCCESS_CODE = 1000;
    public static String SUCCESS_MESSAGE = "SUCCESS";

    public static int INTERNAL_SERVER_ERROR_CODE = 1002;
    public static String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";

    public static int BEAN_VALIDATION_ERROR_CODE = 1003;
    public static String BEAN_VALIDATION_ERROR_MESSAGE = "Request Parameters Improper";


    /* USER */
    public static int USER_EXIST_CODE = 2001;
    public static String USER_EXIST_MESSAGE = "Username Already Exist";

    public static int INVALID_CREDENTIAL_CODE = 2002;
    public static String INVALID_CREDENTIAL_MESSAGE = "Invalid Credentials";
    
    public static int DUPLICATE_USERS_CODE = 2003;
    public static String DUPLICATE_USERS_MESSAGE = "Duplicate Users";
    
    public static int GROUP_NOT_CREATED_CODE = 2004;
    public static String GROUP_NOT_CREATED_MESSAGE = "Fail to Create Group";
    
    public static int USER_NOT_FOUND_CODE = 2005;
    public static String USER_NOT_FOUND_MESSAGE = "User Not Found";
    
    public static int GROUP_NOT_FOUND_CODE = 2006;
    public static String GROUP_NOT_FOUND_MESSAGE = "Group Not Found";
    
    public static int INVALID_USERS_CODE = 2007;
    public static String INVALID_USERS_MESSAGE = "Invalid Users";
    
    
    /* TRANSACTION MESSAGES */
    public static int TRANSACTION_NOT_GENERATED_CODE = 3001;
    public static String TRANSACTION_NOT_GENERATED_MESSAGE = "Failed Transaction";
}
