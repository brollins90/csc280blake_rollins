package edu.neumont.csc280.webserver;

public enum MyHttpStatusCode {
    OK(200, "OK"), NotFound404(404, "File Not Found"), ERROR500(500, "Internal Server Error");

    private final int value;
    private final String string;

    MyHttpStatusCode(int value, String string) {
        this.value = value;
        this.string = string;
    }

    public int getValue() {
        return value;
    }

    public String getString() {
        return string;
    }

    public static MyHttpStatusCode GetByValue(int value) {
        for (MyHttpStatusCode c : values()) {
            if (c.value == value) {
                return c;
            }
        }
        return MyHttpStatusCode.ERROR500;
    }
}
