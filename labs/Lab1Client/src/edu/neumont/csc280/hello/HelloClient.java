package edu.neumont.csc280.hello;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class HelloClient{
    public static void main(String[] args) {
        try{
            String prompt = "What host? (Leave blank for localhost)";
            String host = JOptionPane.showInputDialog(prompt);
            if (host == null || host.isEmpty()) host = "localhost";
            prompt = "What is your name?";
            String name;
            do{
                name = JOptionPane.showInputDialog(prompt);
            }while (name == null || name.isEmpty());

            Socket server = new Socket(host, 30000);
            OutputStream serverOut = server.getOutputStream();
            InputStream serverIn = server.getInputStream();

            PrintWriter serverWrite = new PrintWriter(serverOut, true);
            serverWrite.println(name);
            server.shutdownOutput();

            Scanner serverScan = new Scanner(serverIn);
            serverScan.useDelimiter("$");
            String resp = serverScan.next();

            JOptionPane.showMessageDialog(null, resp, "The server says:", JOptionPane.INFORMATION_MESSAGE);
            server.shutdownInput();
            server.close();
        } catch (IOException ioex){
            ioex.printStackTrace();
        }
    }
}
