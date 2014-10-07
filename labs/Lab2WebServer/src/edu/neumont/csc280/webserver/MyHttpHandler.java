package edu.neumont.csc280.webserver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.neumont.servlet.HttpHandler;
import edu.neumont.servlet.HttpRequest;
import edu.neumont.servlet.HttpResponse;

public class MyHttpHandler implements HttpHandler {

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {

        if (request.getUri().equalsIgnoreCase("/lab2/item")) {
            getItem(request, response);
        } else if (request.getUri().equalsIgnoreCase("/lab2/image")) {
            getImage(request, response);
        } else {
            response.setStatusCode(MyHttpStatusCode.NotFound404.getValue());
        }
    }

    private void getItem(HttpRequest request, HttpResponse response) {
        MyHttpResponse resp = (MyHttpResponse) response;

        resp.setContentType("text/html");
        resp.setStatusCode(MyHttpStatusCode.OK.getValue());
        resp.setContent("".getBytes());
        resp.appendContent(("<html>" + "  <body>" + "    <h1>Auction Item #1234</h1>" + "    <img width=\"200\" src=\"http://localhost:8080/lab2/image\"/>" + "    <dl>" + "      <dt>Current Bid:</dt>" + "      <dd>$1.00</dd>" + "      <dt>Time Left</dt>" + "      <dd>2 Days</dd>" + "      <dt>" + "        <input/>" + "      </dt>" + "      <dd>" + "        <input type=\"submit\" value=\"Place a bid\"/>" + "      </dd>" + "    </dl>" + "  </body>" + "</html>").getBytes());
    }

    private void getImage(HttpRequest request, HttpResponse response){
        MyHttpResponse resp = (MyHttpResponse) response;

        resp.setContentType("image/png");
        resp.setStatusCode(MyHttpStatusCode.OK.getValue());

        File image = new File("img.png");
        byte[] imageBytes = new byte[(int) image.length()];
        FileInputStream fis;
        try {
            fis = new FileInputStream(image);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(imageBytes, 0, imageBytes.length);
            resp.setContent(imageBytes);
            bis.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}