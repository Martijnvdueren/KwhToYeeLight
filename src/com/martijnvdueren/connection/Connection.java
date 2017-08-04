package com.martijnvdueren.connection;

/**
 * Created by marti on 4-8-2017.
 */
public abstract class Connection {

    public abstract void sendJsonCommand(String Json);
    public abstract void closeConnection();

}
