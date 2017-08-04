package com.martijnvdueren.connection;

/**
 * Created by marti on 16-4-2017.
 */


public class YeeLightConnection<C extends Connection> {

    private C connection;

    public YeeLightConnection(C connection){
        this.connection = connection;
    }

    public void setRgb24bitValue(int rgb24bitValue) {
        connection.sendJsonCommand("{\"id\":1,\"method\":\"set_rgb\",\"params\":[" + rgb24bitValue + ", \"smooth\", 500]}\r\n");
    }

    public void closeConnection(){
        connection.closeConnection();
    }

}
