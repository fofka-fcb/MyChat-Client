package org.example.ServicesOfClient;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

@RequiredArgsConstructor
public class WriteMessage implements Runnable {
    private final Socket socket;

    @SneakyThrows
    @Override
    public void run() {

        BufferedReader readerFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String messageFromClient;
        while ((messageFromClient = readerFromServer.readLine()) != null) {
            if (messageFromClient.startsWith("!Exit")) {
                break;
            }
            System.out.println(messageFromClient);
        }
    }
}
