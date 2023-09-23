package org.example.ServicesOfClient;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.*;
import java.net.Socket;

@RequiredArgsConstructor
public class MenuImpl implements Menu {
    private final Socket socket;

    @SneakyThrows
    @Override
    public void startMenu() {

        BufferedReader readerFromClient = new BufferedReader(new InputStreamReader(System.in));

        BufferedReader readerFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter writeToServer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

        while (true) {
            System.out.println("Menu:\n 1. Authorization\n 2. Registration");
            System.out.println("Select num");
            String menuNum = readerFromClient.readLine();
            writeToServer.println(menuNum);
            writeToServer.flush();

            String selectNumOfMenu = readerFromServer.readLine();
            if (selectNumOfMenu.startsWith("!autho!")) {
                System.out.println("You select authorization");
                while (true) {
                    System.out.println("print your mail");
                    String loginOfClient = readerFromClient.readLine();
                    writeToServer.println(loginOfClient);
                    writeToServer.flush();

                    System.out.println("print your password");
                    String passwordOfClient = readerFromClient.readLine();
                    writeToServer.println(passwordOfClient);
                    writeToServer.flush();

                    String messageFromServer = readerFromServer.readLine();
                    System.out.println(messageFromServer);

                    if (!messageFromServer.startsWith("Log in again")) {
                        break;
                    }
                }
            } else if (selectNumOfMenu.startsWith("!reg!")) {
                System.out.println("You select registration");
                while (true) {
                    System.out.println("Print your login(email): ");
                    String loginOfClient = readerFromClient.readLine();
                    writeToServer.println(loginOfClient);
                    writeToServer.flush();

                    String messageFromServer = readerFromServer.readLine();
                    System.out.println(messageFromServer);

                    if (!messageFromServer.startsWith("Email already exists")) {
                        break;
                    }
                }

                System.out.println("Print your password");
                String passwordOfClient = readerFromClient.readLine();
                writeToServer.println(passwordOfClient);
                writeToServer.flush();

                while (true) {
                    System.out.println("print your nickname");
                    String nicknameOfClient = readerFromClient.readLine();
                    writeToServer.println(nicknameOfClient);
                    writeToServer.flush();

                    String messageFromServer = readerFromServer.readLine();
                    System.out.println(messageFromServer);

                    if (!messageFromServer.startsWith("Nickname already exists")) {
                        break;
                    }
                }
                String messageFromServer = readerFromServer.readLine();
                System.out.println(messageFromServer);
            }

            String chatMessage = readerFromServer.readLine();
            if (chatMessage.startsWith("!chat!")) {
                break;
            }
        }
    }
}
