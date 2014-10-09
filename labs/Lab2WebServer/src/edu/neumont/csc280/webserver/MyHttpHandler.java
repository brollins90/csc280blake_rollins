package edu.neumont.csc280.webserver;

import edu.neumont.servlet.HttpHandler;
import edu.neumont.servlet.HttpRequest;
import edu.neumont.servlet.HttpResponse;

import java.io.IOException;
import java.io.InputStream;

public class MyHttpHandler implements HttpHandler
{
    private int amount = 5;

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        System.out.println("goGet");

        if (request.getUri().equalsIgnoreCase("/lab2/item")) {
            getItem(request, response);
        } else if (request.getUri().equalsIgnoreCase("/lab2/image")) {
            getImage(request, response);
        } else {
            response.setContentType("text/html");
            response.setStatusCode(MyHttpStatusCode.NotFound404.getValue());
            try {
                response.getOutputStream().write(("<html><body>File not found</body></html>").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void doPost(HttpRequest request, HttpResponse response) {
        System.out.println("doPost");

        if (request.getUri().equalsIgnoreCase("/lab2/item")) {
            amount++;
        }
        doGet(request, response);
    }

    private void getItem(HttpRequest request, HttpResponse response) {

        response.setContentType("text/html");
        response.setStatusCode(MyHttpStatusCode.OK.getValue());
        try {
            response.getOutputStream().write(("<html><body><form method=\"post\"> <h1>Auction Item #1234</h1><img width=\"200\" src=\"http://localhost:8080/lab2/image\"/><dl><dt>Current Bid:</dt><dd>$" + amount + ".00</dd><dt>Time Left</dt><dd>2 Days</dd><dt><input name=\"amount\"/></dt><dd><input type=\"submit\" value=\"Place a bid\"/></dd></dl></form></body></html>").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getImage(HttpRequest request, HttpResponse response){

        response.setContentType("image/png");
        response.setStatusCode(MyHttpStatusCode.OK.getValue());

        byte[] imageBytes = new byte[1024];
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("img.png");
            int bytesRead = is.read(imageBytes, 0, imageBytes.length);
            response.getOutputStream().write(imageBytes);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
