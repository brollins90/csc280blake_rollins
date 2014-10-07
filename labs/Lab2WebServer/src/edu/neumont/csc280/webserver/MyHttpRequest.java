package edu.neumont.csc280.webserver;

import edu.neumont.servlet.HttpRequest;

public class MyHttpRequest implements HttpRequest {

    private String uri;

    public MyHttpRequest(String uri) {
        // System.out.println("created request");
        this.uri = uri;
    }

    @Override
    public String getUri() {
        return this.uri;
    }

}
