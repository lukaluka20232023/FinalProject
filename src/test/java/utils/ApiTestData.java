package utils;

import java.util.UUID;

public class ApiTestData {

    private static boolean initialized = false;

    public static String name;
    public static String email;
    public static String password;

    public static String title = "Mr";
    public static String birth_date = "10";
    public static String birth_month = "01";
    public static String birth_year = "1999";

    public static String firstname = "Test";
    public static String lastname = "User";
    public static String company = "Automation";
    public static String address1 = "Tbilisi Street 1";
    public static String address2 = "Apartment 2";
    public static String country = "Canada";
    public static String zipcode = "0101";
    public static String state = "State";
    public static String city = "City";
    public static String mobile_number = "5555555555"; // 10 digits

    public static synchronized void initOnce() {
        if (initialized) return;

        name = "ApiUser";
        email = "api_" + UUID.randomUUID().toString().substring(0, 8) + "@mail.com";
        password = "Pass12345!";

        initialized = true;
    }
}
