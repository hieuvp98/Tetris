/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieuv
 */
public class SocketServer {

    private ServerSocket serverSocket;
    private Socket socket;
    public  int PORT = 6868;

    public void start() {
        init();
        listen();
    }

    public void init() {
        try {
            System.out.println("Binding to port " + PORT);
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started");
        } catch (IOException e) {
            System.out.println("Cant bind to port " + PORT);
        }
    }

    public void listen() {
        Runnable runnable = () -> {
            while (!serverSocket.isClosed()) {
                try {
                    System.out.println("waiting for client...");
                    socket = serverSocket.accept();
                    System.out.println("Client connected");
                    send();
                    receiver();
                    System.out.println("Finish");
                } catch (IOException e) {
                    System.out.println("Listen fail");
                }
            }
        };
        new Thread(runnable).start();
    }

    public void receiver() {
        try {
            InputStreamReader reader = new InputStreamReader(socket.getInputStream());
            while (true) {
                int number = reader.read();
                System.out.println("\n>> " + number);
                if (number == -1) {
                    System.out.println("stop");
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("cant receier");
        }
    }

    public void send() {
        Runnable runnable = () -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                while (true) {
                    System.out.print("<< type a number: ");
                }
            } catch (IOException e) {
                System.out.println("send error");
            }
        };
        new Thread(runnable).start();
    }

}
