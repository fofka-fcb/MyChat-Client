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

            BufferedReader readerFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Логика для авторизации
//            System.out.println("print your nickname for authorization: ");
//            while (true) {
//                String nicknameOfClient = readerFromClient.readLine();
//                writeToServer.write("!auto!" + nicknameOfClient + "\n");
//                writeToServer.flush();
//
//                BufferedReader readerFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                String messageFromServer = readerFromServer.readLine();
//                String autoIsTrue = "Log in again";
//                System.out.println(messageFromServer);
//                if (autoIsTrue.equals(messageFromServer) != true) {
//                    break;
//                }
//            }

            // Логика для регистрации
//            System.out.println("Print your nickname for registration");
//            while (true) {
//                String nicknameOfClient = readerFromClient.readLine();
//                writeToServer.write("!reg!" + nicknameOfClient + "\n");
//                writeToServer.flush();
//
//                BufferedReader readerFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                String messageFromServer = readerFromServer.readLine();
//                String regIsTrue = "Reg again";
//                System.out.println(messageFromServer);
//                if (regIsTrue.equals(messageFromServer) != true){
//                    break;
//                }
//            }

            // Логика полноценной авторизации/регистрации
            while (true) {
                System.out.println("Menu:\n 1. Authorization\n 2. Registration");
                System.out.println("Select num");
                String menuNum = readerFromClient.readLine();
                writeToServer.println(menuNum);
                writeToServer.flush();

                String selectNumOfMenu = readerFromServer.readLine();
                if (selectNumOfMenu.startsWith("!autho!")) {
                    System.out.println("You select authorization");
                    System.out.println("Print your nickname: ");
                    while (true) {
                        String nicknameOfClient = readerFromClient.readLine();
                        writeToServer.println("!auto!" + nicknameOfClient);
                        writeToServer.flush();

                        String messageFromServer = readerFromServer.readLine();
                        System.out.println(messageFromServer);

                        if (messageFromServer.startsWith("Log in again") != true){
                            break;
                        }
                    }
                } else if (selectNumOfMenu.startsWith("!reg!")) {

                    System.out.println("You select registration");
                    System.out.println("Print your nickname: ");

                }

                String chatMessage = readerFromServer.readLine();
                if (chatMessage.startsWith("!chat!")) {
                    break;
                }
            }

            new Thread(new ReadAndWriteMessageFromServer(socket)).start();

            System.out.println("print your message: ");
            String exit = "!Exit";
            while (true) {
                String messageToServer = readerFromClient.readLine();
                if (messageToServer.equals(exit)) {
                    socket.close();
                    break;
                }
                writeToServer.write(messageToServer + "\n");
                writeToServer.flush();
            }
        }
    }
}

