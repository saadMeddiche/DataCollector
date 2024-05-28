package org.data.datacollector.dataCombinators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataCombinator {


    protected String booleanBuilder(String stringBoolean, String trueValue, String falseValue){
        if(stringBoolean == null || stringBoolean.isEmpty()) return falseValue;
        return stringBoolean.equals(trueValue) ? trueValue : falseValue;
    }

    protected String dateBuilder(String stringDate){

        if(stringDate == null || stringDate.isEmpty()) return "";

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd 12:00:00");

        String formattedDate = "";

        try {
            Date date = inputFormat.parse(stringDate);
            formattedDate = outputFormat.format(date);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + stringDate);
        }

        return formattedDate;
    }

}
