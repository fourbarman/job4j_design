package ru.job4j.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * EchoServer.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 29.09.2020.
 */
public class EchoServer {
    /**
     * Main method.
     * Creates localhost socket on 9000 port.
     * Accepts all incoming messages and prints them to console.
     * Exit when get message "Bye".
     *
     * @param args String args.
     * @throws IOException Exception.
     */
    public static void main(String[] args) throws IOException {
        String exit = "?msg=Bye";
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean isRunning = true;
            while (isRunning) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains(exit)) {
                            isRunning = false;
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}
