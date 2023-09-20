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

            Menu menu = new MenuImpl(socket);
            menu.startMenu();

            Chat chat = new ChatImpl(socket);
            chat.startChat();
        }
    }
}

