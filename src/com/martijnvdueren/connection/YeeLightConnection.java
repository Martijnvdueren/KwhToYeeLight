package com.martijnvdueren.connection;

/**
 * Created by marti on 16-4-2017.
 */
public class YeeLightConnection {

    private TCPConnection tcpConnection;

    public YeeLightConnection(TCPConnection tcpConnection){
        this.tcpConnection = tcpConnection;
    }

    public void setRgb24bitValue(int rgb24bitValue) {
        tcpConnection.sendJsonCommand("{\"id\":1,\"method\":\"set_rgb\",\"params\":[" + rgb24bitValue + ", \"smooth\", 500]}");
    }

    public void closeConnection(){
        tcpConnection.closeConnection();
    }
}
