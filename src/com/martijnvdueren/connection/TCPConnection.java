package com.martijnvdueren.connection;

import java.io.*;
import java.net.Socket;

public class TCPConnection
{
    Socket clientSocket;
    DataOutputStream outToServer;
    PrintWriter printWriterw;
    BufferedReader inFromServer;


    public TCPConnection(String host, int port){
        try{
            clientSocket = new Socket(host, port);
            System.out.println("scientsocket");
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            System.out.println("outtoserver");
            printWriterw = new PrintWriter(outToServer);
            System.out.println("printwriterw");
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("infromserver");
        }catch (IOException io) {
            io.printStackTrace();
        }
    }


    public void sendJsonCommand(String Json)
    {
        System.out.println(Json);
        printWriterw.println(Json);

        System.out.println("Hangs here after sending command to yeelight it never returns infromserver.");
        printWriterw.flush();

        try {
           System.out.println("FROM SERVER: " + inFromServer.readLine());
        }catch (IOException io) {
            io.printStackTrace();
        }

    }

    public void closeConnection(){
        try {
            clientSocket.close();
        }catch(IOException io) {
            io.printStackTrace();
        }
    }
}

