package edu.neumont.csc280.webserver;

import edu.neumont.servlet.HttpRequest;

import java.io.InputStream;

public class MyHttpRequest implements HttpRequest {

    private String uri;
    private InputStream is;

    public MyHttpRequest(InputStream is) {
        this.is = is;
    }

    public MyHttpRequest(String uri) {
        this.uri = uri;
    }

    @Override
    public String getUri() {
        return this.uri;
    }

}
