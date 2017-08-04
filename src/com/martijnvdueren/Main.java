package com.martijnvdueren;

import com.martijnvdueren.energy.SerialDataConnection;
import com.martijnvdueren.connection.TCPConnection;
import com.martijnvdueren.connection.YeeLightConnection;
import com.martijnvdueren.energy.EnergyData;

import static com.martijnvdueren.energy.EnergyDataConvertor.KwhToRGB24bit;
//Todo: Refactor the connecitons using generics
//Todo: making a interface for connection

public class Main {


    public static void main(String[] args) throws Exception{

        System.out.println("Started Serial Data to Yeelight");
        double maxKw = Double.parseDouble(args[0]);
        //making connections
        YeeLightConnection yeeLightConnection = new YeeLightConnection(new TCPConnection("192.168.0.104",55443));


        //testdata when developing without connection
        //EnergyData energyData = new EnergyData("/ISk5\\2ME382-1004;;0-0:96.1.1(4B414C37303035313731323838323133);1-0:1.8.1(02750.633*kWh);1-0:1.8.2(02806.731*kWh);1-0:2.8.1(00000.024*kWh);1-0:2.8.2(00000.027*kWh);0-0:96.14.0(0001);1-0:1.7.0(0000.81*kW);1-0:2.7.0(0000.00*kW);0-0:17.0.0(0999.00*kW);0-0:96.3.10(1);0-0:96.13.1();0-0:96.13.0();0-1:24.1.0(3);0-1:96.1.0(4730303135353631313038333734353133);0-1:24.3.0(170415170000)(00)(60)(1)(0-1:24.2.1)(m3);(01500.525);0-1:24.4.0(1);!;");

        for(int i = 0; i < 120; i++){
            System.out.println("\n============================\nGetting Serial Data...");
            SerialDataConnection serialData = new SerialDataConnection();
            EnergyData energyData = new EnergyData(serialData.getSerialDataSet());
            System.out.println("Current Kwh Usage: "+ energyData.getCurrentPower() );
            int rgb24BitVal = KwhToRGB24bit(energyData.getCurrentPower(), maxKw);
            yeeLightConnection.setRgb24bitValue(rgb24BitVal);

            Thread.sleep(100);
        }

        yeeLightConnection.closeConnection();
        System.out.println("close socket");

    }

}
