package org.data.datacollector.dataCombinators;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataCombinator {


    protected String booleanBuilder(String stringBoolean, String trueValue, String falseValue){
        if(stringBoolean == null || stringBoolean.isEmpty()) return falseValue;
        return stringBoolean.equals(trueValue) ? trueValue : falseValue;
    }

    protected String dateBuilder(String stringDate){

        if(stringDate == null || stringDate.isEmpty()) return "";

        Date date;

        try {
            date = new Date(stringDate);
        } catch (Exception e) {
            System.out.println("Error while parsing date: " + stringDate);
            return "";
        }

        date.setHours(12);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(date);
    }

}
