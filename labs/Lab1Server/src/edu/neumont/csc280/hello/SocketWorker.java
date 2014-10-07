package edu.neumont.csc280.hello;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The worker thread for each client that connects to the server
 * @author Blake Rollins
 *
 */
public class SocketWorker implements Runnable {

    // Hold the socket for the client connection
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Create a new client worker thread for the server
     * @param clientSocket	The socket for the client
     */
    public SocketWorker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Start the worker
     */
    @Override
    public void run() {
        try {
            // Open the streams
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read a string
            String receivedString = in.readLine();

            // Prepend the string with Hello and send it back
            String responseString = "Hello " + receivedString;
            out.println(responseString);
            out.flush();

            stop();
        } catch (Exception e) {
            //System.out.println("Error");
        }
    }

    public void stop() {

        try{
            // close the streams
            out.close();
            in.close();

            // close the socket
            clientSocket.close();
        } catch (Exception e) {

        }

    }
}
