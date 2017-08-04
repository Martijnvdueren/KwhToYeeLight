package com.martijnvdueren.connection;

import java.io.IOException;

/**
 * Created by marti on 10-5-2017.
 */
public class CommandlineConnection extends Connection{

    private String host;
    private int port;
    private Process pr;
    private Runtime rt;

    public CommandlineConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void sendJsonCommand(String json) {
        try {

            String command = "sudo echo -ne '"+json+"\\r\\n' | nc -w1 " + host + " 5 " + port;
            System.out.println(command);
            rt = Runtime.getRuntime();
            pr = rt.exec(command);


        }catch (IOException io){
            io.printStackTrace();
        }

    }

    public void closeConnection(){
       pr.destroy();
    }
}