package utils;

import io.qameta.allure.Attachment;

public class ApiAllure {

    @Attachment(value = "API Request", type = "text/plain")
    public static String attachRequest(String req) { return req; }

    @Attachment(value = "API Response", type = "application/json")
    public static String attachResponse(String res) { return res; }
}
