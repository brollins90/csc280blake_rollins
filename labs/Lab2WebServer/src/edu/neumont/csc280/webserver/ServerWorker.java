package edu.neumont.csc280.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.InvalidParameterException;

import edu.neumont.servlet.HttpResponse;

public class ServerWorker implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private HttpResponse response;

    private static MyHttpHandler handler = new MyHttpHandler();

    public ServerWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            // Open the streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            response = new MyHttpResponse(socket.getOutputStream());

            String firstLine = in.readLine();
            String[] requestParts = firstLine.split(" ");
            if (requestParts.length != 3) {
                throw new InvalidParameterException("HttpRequest does not contain 3 parts.");
            }

            // 0 should be "GET"
            if (!requestParts[0].equalsIgnoreCase("GET")) {
                throw new InvalidParameterException(requestParts[0] + " is an unsupported method.");
            }
            // 2 should be "HTTP/1.1"
            if (!requestParts[2].equalsIgnoreCase("HTTP/1.1")) {
                throw new InvalidParameterException(requestParts[0] + " is an unsupported protocol version.");
            }

            MyHttpRequest request = new MyHttpRequest(requestParts[1]);
            handler.doGet(request, response);

            response.flush();

        } catch (Exception e) {
            response.setStatusCode(MyHttpStatusCode.ERROR500.getValue());
            try {
                response.flush();
            } catch (IOException e1) {
                System.out.println("ERROR: " + e.getLocalizedMessage());
//				e1.printStackTrace();
            }

        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("ERROR: " + e.getLocalizedMessage());
//				e.printStackTrace();
            }
        }

    }

}
