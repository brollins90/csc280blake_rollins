package edu.neumont.csc280.webserver;

import edu.neumont.servlet.HttpRequest;
import edu.neumont.servlet.HttpResponse;

import java.io.*;
import java.net.Socket;
import java.security.InvalidParameterException;

public class ServerWorker implements Runnable {

    private Socket socket;
    private BufferedReader socketIn;
    private OutputStream socketOut;
    private boolean running;

    private static MyHttpHandler handler = new MyHttpHandler();

    public ServerWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        running = true;

        try {
            // Open the streams
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = socket.getOutputStream();

            while (running) {
                if (socketIn.ready()) {

                    String firstLine = socketIn.readLine();
                    String[] requestParts = firstLine.split(" ");

                    if (requestParts.length != 3) {
                        throw new InvalidParameterException("HttpRequest does not contain 3 parts.");
                    }

                    HttpRequest request = new MyHttpRequest(requestParts[1]);
                    HttpResponse response = new MyHttpResponse(new ByteArrayOutputStream());

                    if (requestParts[0].equalsIgnoreCase("GET")) {
                        handler.doGet(request, response);
                    }

                    if (requestParts[0].equalsIgnoreCase("POST")) {
                        handler.doPost(request, response);
                    }

//                    // 2 should be "HTTP/1.1"
//                    if (!requestParts[2].equalsIgnoreCase("HTTP/1.1")) {
//                        throw new InvalidParameterException(requestParts[0] + " is an unsupported protocol version.");
//                    }


                    writeResponse(socketOut, response);
                }
            }

        } catch (Exception e) {
            System.out.println("Socket closed");
            e.printStackTrace();
        } finally {
            try {
                socketIn.close();
                socketOut.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("ERROR: " + e.getLocalizedMessage());
            }
        }
    }

    private void writeResponse(OutputStream socketOut, HttpResponse response) {

        try {


            System.out.println("Write response");
            String line = "HTTP/1.1 " + response.getStatusCode() + " " + MyHttpStatusCode.GetByValue(response.getStatusCode()).getString() + "\n";
            socketOut.write(line.getBytes());

            byte[] b = ((ByteArrayOutputStream) response.getOutputStream()).toByteArray();

            if (response.getStatusCode() == MyHttpStatusCode.ERROR500.getValue()) {
                b = "<html><body>Internal server error.</body></html>".getBytes();
            }
            line = "Content-Length: " + b.length + "\n";
            socketOut.write(line.getBytes());

//            line = "Connection: close\n";
//            socketOut.write(line.getBytes());

            line = "\n";
            socketOut.write(line.getBytes());

            socketOut.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
