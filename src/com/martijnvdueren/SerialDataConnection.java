package com.martijnvdueren;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by marti on 14-4-2017.
 */
public class SerialDataConnection {


    private Process runtimeCommand;
    private BufferedReader br;
    private final String command = "cu -l /dev/ttyUSB0 -s 9600 --parity=none\n";
    private StringBuilder serialDataSet;
    private boolean isSerialDataSetReady;


    public SerialDataConnection(){

        try {
            runtimeCommand = Runtime.getRuntime().exec(command);
            br = new BufferedReader(new InputStreamReader(runtimeCommand.getInputStream()));
            String response;
            StringBuilder tempSb = new StringBuilder();


            //Getting only one response at the moment since the program won't continue out of this loop.
            //Have to read into and convert to threading afterwards.
            while(runtimeCommand.isAlive() && (response = br.readLine()) != null){

                tempSb.append(response+";");

                if(response.equalsIgnoreCase("!")) {

                    serialDataSet = new StringBuilder(tempSb);
                    isSerialDataSetReady = true;
                    tempSb.setLength(0);

                    runtimeCommand.destroy();
                    runtimeCommand.waitFor();
                    br.close();
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            runtimeCommand.destroy();
//            runtimeCommand.waitFor();
//            System.out.println ("exit: " + runtimeCommand.exitValue());

        }
    }

    public String getSerialDataSet() {
        return serialDataSet.toString();
    }

    public boolean isSerialDataSetReady() {
        return isSerialDataSetReady;
    }

}
