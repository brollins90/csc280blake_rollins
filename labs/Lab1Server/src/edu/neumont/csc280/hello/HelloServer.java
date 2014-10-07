package edu.neumont.csc280.hello;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Simple server application for the first lab in the CSC280 class
 * @author Blake Rollins
 *
 */
public class HelloServer implements Runnable {

    private static int port = 30_000;
//	private static int maxClients = 10_000;

    //	private static SocketWorker[] clients;
    private boolean running;
    private int clientCount;

    /**
     * Create the server thread
     */
    public HelloServer() {
//		clients = new SocketWorker[maxClients];
        running = false;
        clientCount = 0;
    }

    /**
     * Start the server thread
     */
    public void run() {
        System.out.println("Server starting...");

        running = true;
        try (ServerSocket ss = new ServerSocket(port)) {
            System.out.println("Server started");
            while (running) {

                Socket clientSocket = null;
                try {
                    clientSocket = ss.accept();
                    System.out.println("Connected to a client.  Current count: " + ++clientCount);
                } catch (IOException e) {
                    System.out.println("Error connecting to a new client");
                }

//				// Create a worker for the client
//				synchronized (this) {
//					clients[clientCount] = new SocketWorker(clientSocket);
//				}
//				// Start the new worker thread
//				new Thread(clients[clientCount]).start();

                // I dont want to deal with the collection right now.  and i dont really need to unless i want it clean
                new Thread(new SocketWorker(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            stopServer();
        }
    }

    public void stopServer() {
        System.out.println("Server is stopping.");
        running = false;

//		for (SocketWorker w : clients) {
//			if (w != null) {
//				w.stop();
//			}
//		}
        System.out.println("Server is stopped");
    }

    public static void main(String[] args) {
        HelloServer s = new HelloServer();
        s.run();
    }
}
