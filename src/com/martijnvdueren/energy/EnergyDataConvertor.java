package com.martijnvdueren.energy;

/**
 * Created by marti on 15-4-2017.
 */
public class EnergyDataConvertor {

    private EnergyDataConvertor(){}

    public static int KwhToRGB24bit(double currentKw, double maxKw){

        int r=0;
        int g=0;
        int b=0;

        //calculate current usage percentage in relation to ceiling maxKw
        double percentage = (maxKw>currentKw)? (currentKw / maxKw) : 1;

        //8bit range of percentage, and reversed range
        int b8 = (int)(percentage*255);
        int b8r = (int)(255-percentage*255);

        //setting r,g values based on the range
        if(percentage < 0.5){
            r = 255-(b8r-b8);
            g = 255;

        }else if(percentage == 0.5){
            r = 255;
            g = 255;

        }else if(percentage > 0.5){
            r = 255;
            g = 255-(b8-b8r);
        }

        //converting rgb values to 24gb24bit
        int rgb24bit = (r*65536)+(g*256)+b;


        return rgb24bit;
    }





}
