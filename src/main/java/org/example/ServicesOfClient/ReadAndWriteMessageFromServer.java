package org.example.ServicesOfClient;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

@RequiredArgsConstructor
public class ReadAndWriteMessageFromServer implements Runnable {
    private final Socket socket;
//    private final ClientImpl client;

    @SneakyThrows
    @Override
    public void run() {

//        BufferedReader readerFromClient = new BufferedReader(new InputStreamReader());

        BufferedReader readerFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while (true) {
            System.out.println(readerFromServer.readLine());
        }
    }
}
