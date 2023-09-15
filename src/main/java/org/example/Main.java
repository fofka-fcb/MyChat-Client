package org.example;

import org.example.ServicesOfClient.Client;
import org.example.ServicesOfClient.ClientImpl;


public class Main {

    public static void main(String[] args) {
        Client client = new ClientImpl();
        client.start();
    }
}