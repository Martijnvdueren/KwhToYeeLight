package com.martijnvdueren;

//        /ISk5\2ME382-1004
//
//        0-0:96.1.1(4B414C37303035313731323838323133)
//        1-0:1.8.1(02733.938*kWh)
//        1-0:1.8.2(02788.524*kWh)
//        1-0:2.8.1(00000.024*kWh)
//        1-0:2.8.2(00000.026*kWh)
//        0-0:96.14.0(0001)
//        1-0:1.7.0(0000.45*kW)
//        1-0:2.7.0(0000.00*kW)
//        0-0:17.0.0(0999.00*kW)
//        0-0:96.3.10(1)
//        0-0:96.13.1()
//        0-0:96.13.0()
//        0-1:24.1.0(3)
//        0-1:96.1.0(4730303135353631313038333734353133)
//        0-1:24.3.0(170406230000)(00)(60)(1)(0-1:24.2.1)(m3)
//                (01485.058)
//        0-1:24.4.0(1)
//        !


//        while stack_teller < 20:
//        if stack[stack_teller][0:9] == "1-0:1.8.1":
//        print "daldag     ", stack[stack_teller][10:15]
//        elif stack[stack_teller][0:9] == "1-0:1.8.2":
//        print "piekdag    ", stack[stack_teller][10:15]
//# Daltarief, teruggeleverd vermogen 1-0:2.8.1
//        elif stack[stack_teller][0:9] == "1-0:2.8.1":
//        print "dalterug   ", stack[stack_teller][10:15]
//# Piek tarief, teruggeleverd vermogen 1-0:2.8.2
//        elif stack[stack_teller][0:9] == "1-0:2.8.2":
//        print "piekterug  ", stack[stack_teller][10:15]
//# Huidige stroomafname: 1-0:1.7.0
//        elif stack[stack_teller][0:9] == "1-0:1.7.0":
//        print "afgenomen vermogen      ", int(float(stack[stack_teller][10:17])*1000), " W"
//# Huidig teruggeleverd vermogen: 1-0:1.7.0
//        elif stack[stack_teller][0:9] == "1-0:2.7.0":
//        print "teruggeleverd vermogen  ", int(float(stack[stack_teller][10:17])*1000), " W"
//# Gasmeter: 0-1:24.3.0
//        elif stack[stack_teller][0:10] == "0-1:24.3.0":
//        print "Gas                     ", int(float(stack[stack_teller+1][1:10])*1000), " dm3"
//   else:
//        pass
//                stack_teller = stack_teller +1


public class EnergyDataParser {

    private double lowDay;
    private double highDay;
    private double lowReturn;
    private double highReturn;
    private double currentPower;
    private double currentReturnedPower;
    private double gasUsage;


    public void parseEnergyData(String serialEnergyData){

        String energyLines[] = serialEnergyData.split(";");

        for (String energyLine: energyLines){
            parseEnergyDataLine(energyLine);
        }

    }


        public void parseEnergyDataLine(String serialEnergyData){

        if(serialEnergyData.isEmpty()){

        }else if(serialEnergyData.contains("1-0:1.8.1")){

            String extractedData = getSubstring(serialEnergyData,"(", "*kWh)");
            lowDay = Double.parseDouble(extractedData);

        }else if(serialEnergyData.contains("1-0:1.8.2")) {

            String extractedData = getSubstring(serialEnergyData,"(", "*kWh)");
            highDay = Double.parseDouble(extractedData);

        }else if(serialEnergyData.contains("1-0:2.8.1")) {

            String extractedData = getSubstring(serialEnergyData,"(", "*kWh)");
            lowReturn = Double.parseDouble(extractedData);

        }else if(serialEnergyData.contains("1-0:2.8.2")) {

            String extractedData = getSubstring(serialEnergyData,"(", "*kWh)");
            highReturn = Double.parseDouble(extractedData);

        }else if(serialEnergyData.contains("1-0:1.7.0")) {

            String extractedData = getSubstring(serialEnergyData,"(", "*kW)");
            currentPower = Double.parseDouble(extractedData);

        }else if(serialEnergyData.contains("0-1:24.3.0")) {

            String extractedData = getSubstring(serialEnergyData,"(", ")");
            gasUsage =Double.parseDouble(extractedData);

        }
    }

    private static String getSubstring(String serialEnergyData, String start, String end) {
        return serialEnergyData.substring(serialEnergyData.indexOf(start)+1, serialEnergyData.indexOf(end));
    }


    public double getLowDay() {
        return lowDay;
    }

    public double getHighDay() {
        return highDay;
    }

    public double getLowReturn() {
        return lowReturn;
    }

    public double getHighReturn() {
        return highReturn;
    }

    public double getCurrentPower() {
        return currentPower;
    }

    public double getCurrentReturnedPower() {
        return currentReturnedPower;
    }

    public double getGasUsage() {
        return gasUsage;
    }

}
