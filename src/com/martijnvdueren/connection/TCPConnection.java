package com.martijnvdueren.connection;

import java.io.*;
import java.net.Socket;

public class TCPConnection extends Connection
{
    Socket clientSocket;
    DataOutputStream outToServer;
    PrintWriter printWriterw;
    BufferedReader inFromServer;


    public TCPConnection(String host, int port){
        try{
            clientSocket = new Socket(host, port);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            printWriterw = new PrintWriter(outToServer);
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }catch (IOException io) {
            io.printStackTrace();
        }
    }


    public void sendJsonCommand(String Json)
    {
        System.out.println("JSON command:\n"+Json);
        printWriterw.println(Json);
        printWriterw.flush();

        try {
           System.out.println("FROM LIGHT: " + inFromServer.readLine());
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

