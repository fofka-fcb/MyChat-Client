package org.example.ServicesOfClient;

import lombok.SneakyThrows;

import java.io.*;
import java.net.Socket;

public class ClientImpl implements Client {
    private final static int PORT = 4444;
    private final static String HOST = "localhost";

    @SneakyThrows
    @Override
    public void start() {
        Socket socket = new Socket(HOST, PORT);

        if (socket.isConnected()) {
            System.out.println("connection to server is done");

            BufferedReader readerFromClient = new BufferedReader(new InputStreamReader(System.in));

            PrintWriter writeToServer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            System.out.println("print your nickname: ");
            String nicknameOfClient = readerFromClient.readLine();
            writeToServer.write("!auto!" + nicknameOfClient + "\n");
            writeToServer.flush();

            new Thread(new SocketRunnable(socket)).start();

            System.out.println("print your message: ");
            String exit = "!Exit";
            while (true) {
                String messageToServer = readerFromClient.readLine();
                if (messageToServer.equals(exit)){
                    socket.close();
                    break;
                }
                writeToServer.write(messageToServer + "\n");
                writeToServer.flush();
            }
        }
    }
}

