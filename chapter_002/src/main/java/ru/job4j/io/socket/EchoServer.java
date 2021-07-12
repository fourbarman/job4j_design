package ru.job4j.io.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.UsageLog4j;

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
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    /**
     * Main method.
     * Creates localhost socket on 9000 port.
     * Accepts all incoming messages and prints them to console.
     * Prints message string to browser.
     * Exit when get message equals "Exit".
     *
     * @param args String args.
     */
    public static void main(String[] args) {
        String msg = "msg=";
        String exit = "Exit";
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean isRunning = true;
            while (isRunning) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String stringTail;
                    str = in.readLine();
                    while (!str.isEmpty()) {
                        System.out.println(str);
                        if (str.contains(msg)) {
                            stringTail = str.substring(str.indexOf(msg)
                                    + msg.length()).split(" ")[0];
                            if (exit.equals(stringTail)) {
                                isRunning = false;
                            }
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write(stringTail.getBytes());
                        }
                        str = in.readLine();
                    }
                }
            }
        } catch (IOException ioException) {
            LOG.error("Server start error", ioException);
        }
    }
}
