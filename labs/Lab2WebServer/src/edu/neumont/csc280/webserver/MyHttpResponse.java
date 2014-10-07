package edu.neumont.csc280.webserver;

import java.io.IOException;
import java.io.OutputStream;

import edu.neumont.servlet.HttpResponse;

public class MyHttpResponse implements HttpResponse {

    private MyHttpStatusCode code;
    private String contentType;
    private byte[] content;
    // private HashMap<String,String> headers;
    private OutputStream outStream;

    public MyHttpResponse(OutputStream out) {
        this.code = MyHttpStatusCode.OK;
        this.contentType = "none";
        this.content = new byte[0];
        // this.headers = new HashMap<String,String>();
        this.outStream = out;
    }

    // public void addHeader(String header, String value) {
    //
    // }

    public void appendContent(byte[] newContent) {
        byte[] temp = new byte[content.length + newContent.length];
        System.arraycopy(content, 0, temp, 0, content.length);
        System.arraycopy(newContent, 0, temp, content.length, newContent.length);
        content = temp;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] contentBytes) {
        content = contentBytes;
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
        switch (this.code) {
            case OK:
                flushToClient();
                break;
            case NotFound404:
                this.setContent("404 error".getBytes());
                flushToClient();
                break;
            case ERROR500:
                this.setContent("500 error".getBytes());
                flushToClient();
                break;
        }
    }

    private void flushToClient() throws IOException {
        String line = "HTTP/1.1 " + this.getStatusCode() + " " + MyHttpStatusCode.GetByValue(this.getStatusCode()).getString() + "\n";
        this.outStream.write(line.getBytes());
        //this.outStream.flush();

        line = "Content-Length: " + content.length + "\n";
        this.outStream.write(line.getBytes());
        //this.outStream.flush();

        line = "Connection: close\n";
        this.outStream.write(line.getBytes());
        //this.outStream.flush();

        line = "\n";
        this.outStream.write(line.getBytes());
        //this.outStream.flush();

        this.outStream.write(content);
        this.outStream.flush();
    }

}
