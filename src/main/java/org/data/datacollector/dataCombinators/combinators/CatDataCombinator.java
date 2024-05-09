package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.Cat;
import org.data.datacollector.dataExtractors.extractors.CatDataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCAT2.CatCAT2;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class CatDataCombinator extends DataCombinator {

    private final CatDataExtractor catDataExtractor;

    private Long START_ID = 1L;

    public List<Cat> catWithoutPntIdList = new ArrayList<>();

    public List<Cat> catWithoutInstructorIdList = new ArrayList<>();

    public List<Cat> getCatList(){

        List<Cat> catList = new ArrayList<>(generateCatList(catDataExtractor.extractCatCAT2()));

        attachPntIdToCat(catList , catDataExtractor.extractEmployeeNumberAndUserId());

        attachInstructorIdToCat(catList , catDataExtractor.extractEmployeeNumberAndUserId());

        return catList;
    }

    private List<Cat> attachPntIdToCat(List<Cat> catList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return catList.stream().peek(
            cat -> employeeNumberAndUserIdList.stream()
            .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(cat.getPntNumber()))
            .findFirst()
            .ifPresentOrElse(
                    employeeNumberAndUserId -> cat.setPntId(employeeNumberAndUserId.getUserId())
                    ,
                    () -> catWithoutPntIdList.add(cat)
            )
        ).toList();
    }

    private List<Cat> attachInstructorIdToCat(List<Cat> catList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return catList.stream().peek(
            cat -> employeeNumberAndUserIdList.stream()
            .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(cat.getInstructorNumber()))
            .findFirst()
            .ifPresentOrElse(
                    employeeNumberAndUserId -> cat.setInstructorId(employeeNumberAndUserId.getUserId())
                    ,
                    () -> catWithoutInstructorIdList.add(cat))
        ).toList();
    }

    private List<Cat> generateCatList(List<CatCAT2> catCAT2List){

        List<Cat> catList = new ArrayList<>();

        catCAT2List.forEach(catCAT2 -> {

            if (catCAT2.getCat2FinalDate() != null && !catCAT2.getCat2FinalDate().isEmpty())
                catList.add(Cat.builder()
                        .id(String.valueOf(START_ID++))
                        .type("CAT II")
                        .category("FINAL")
                        .pntNumber(catCAT2.getEmployeeNumberOfPnt())
                        .inst(catCAT2.getCat2FinalInstructorName())
                        .date(dateBuilder(catCAT2.getCat2FinalDate()))
                        .instructorNumber(catCAT2.getCat2FinalEmployeeNumberOfInstructor())
                        .build());

            if (catCAT2.getCat2TransitionDate() != null && !catCAT2.getCat2TransitionDate().isEmpty())
                catList.add(Cat.builder()
                        .id(String.valueOf(START_ID++))
                        .type("CAT II")
                        .category("TRANSITION")
                        .pntNumber(catCAT2.getEmployeeNumberOfPnt())
                        .inst(catCAT2.getCat2TransitionInstructorName())
                        .date(dateBuilder(catCAT2.getCat2TransitionDate()))
                        .instructorNumber(catCAT2.getCat2TransitionEmployeeNumberOfInstructor())
                        .build()
                );

            if (catCAT2.getCat3FinalDate() != null && !catCAT2.getCat3FinalDate().isEmpty())
                catList.add(Cat.builder()
                        .id(String.valueOf(START_ID++))
                        .type("CAT III")
                        .category("FINAL")
                        .pntNumber(catCAT2.getEmployeeNumberOfPnt())
                        .inst(catCAT2.getCat3FinalInstructorName())
                        .date(dateBuilder(catCAT2.getCat3FinalDate()))
                        .instructorNumber(catCAT2.getCat3FinalEmployeeNumberOfInstructor())
                        .build()
                );

            if (catCAT2.getCat3TransitionDate() != null && !catCAT2.getCat3TransitionDate().isEmpty())
                catList.add(Cat.builder()
                        .id(String.valueOf(START_ID++))
                        .type("CAT III")
                        .category("TRANSITION")
                        .pntNumber(catCAT2.getEmployeeNumberOfPnt())
                        .inst(catCAT2.getCat3TransitionInstructorName())
                        .date(dateBuilder(catCAT2.getCat3TransitionDate()))
                        .instructorNumber(catCAT2.getCat3TransitionEmployeeNumberOfInstructor())
                        .build()
                );

        });

        return catList;
    }

}
