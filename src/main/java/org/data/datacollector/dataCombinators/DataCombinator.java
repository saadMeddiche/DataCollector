package org.data.datacollector.dataCombinators;

import lombok.Data;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DataCombinator {

    protected String dateBuilder(String date){
        if(date == null || date.isEmpty()) return "";
        return globalDateBuilder(new Date(date));
    }

    protected <O> AttachedData<O> attachIdToData(List<O> dataList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList , Function<O , String> employeeNumberGetter , BiFunction<O , String , O> idSetter){

        AttachedData<O> attachedData = new AttachedData<>();

        attachedData.dataList =  dataList.stream().map(
            data -> employeeNumberAndUserIdList.stream()
            .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(employeeNumberGetter.apply(data)))
            .findFirst()
            .map(
                employeeNumberAndUserId -> {
                    idSetter.apply(data , employeeNumberAndUserId.getUserId());
                    return data;
                }
            ).orElseGet(
                () -> {
                    attachedData.getDataWithoutUserIdList().add(data);
                    return data;
                }
            )
        ).toList();

        return attachedData;
    }

    private String globalDateBuilder(Date date){

        date.setHours(12);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(date);
    }

    @Data
    static class AttachedData<O> {
        private List<O> dataList;
        private List<O> dataWithoutUserIdList;
    }
}
