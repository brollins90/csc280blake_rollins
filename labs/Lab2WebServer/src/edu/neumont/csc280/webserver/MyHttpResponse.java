package edu.neumont.csc280.webserver;

import edu.neumont.servlet.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyHttpResponse implements HttpResponse {

    private MyHttpStatusCode code;
    private String contentType;
    private OutputStream outStream;

    public MyHttpResponse(OutputStream outStream) {
        this.code = MyHttpStatusCode.ERROR500;
        this.contentType = "";
        this.outStream = outStream;
    }

    @Override
    public int getStatusCode() {
        return this.code.getValue();
    }

    @Override
    public void setStatusCode(int code) {
        this.code = MyHttpStatusCode.GetByValue(code);
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public OutputStream getOutputStream() {
        return this.outStream;
    }

    @Override
    public void flush() throws IOException {

    }
}
