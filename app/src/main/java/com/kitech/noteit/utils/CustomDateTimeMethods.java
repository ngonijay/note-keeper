package com.kitech.noteit.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateTimeMethods {

    public static String getCurrentLocalDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return  formatter.format(date).toString();
    }
}
