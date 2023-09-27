package org.example.ServicesOfClient;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.*;
import java.net.Socket;

@RequiredArgsConstructor
public class ChatImpl implements Chat {
    private final Socket socket;

    @Override
    @SneakyThrows
    public void startChat() {
        BufferedReader readerFromClient = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter writeToServer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

        new Thread(new WriteMessage(socket)).start();

        System.out.println("CHAT");
        System.out.println("print your message: ");
        while (true) {
            String messageToServer = readerFromClient.readLine();

            writeToServer.println(messageToServer);
            writeToServer.flush();

            if (messageToServer.startsWith("!Exit")) {
                break;
            }
        }
    }
}
