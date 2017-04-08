package com.martijnvdueren;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {



    public static void main(String[] args){



        try {
            Process runtimeCommand = Runtime.getRuntime().exec("cu -l /dev/ttyUSB0 -s 9600 --parity=none\n");
            BufferedReader br = new BufferedReader(new InputStreamReader(runtimeCommand.getInputStream()));
            String response;
            int i = 0;

            while ((response = br.readLine()) != null){
                System.out.println("line "+i+++": " + response);
            }

            runtimeCommand.waitFor();
            System.out.println ("exit: " + runtimeCommand.exitValue());
            runtimeCommand.destroy();

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }


        EnergyDataParser energyDataParser = new EnergyDataParser();

        energyDataParser.parseEnergyData("1-0:1.8.1(02733.938*kWh)");
        energyDataParser.parseEnergyData("1-0:1.8.2(02788.524*kWh)");

        System.out.println(energyDataParser.getLowDay()+" "+energyDataParser.getHighDay());
    }
}
