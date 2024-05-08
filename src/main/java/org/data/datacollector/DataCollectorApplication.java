package org.data.datacollector;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.*;
import org.data.datacollector.dataCombinators.models.*;
import org.data.datacollector.dataWriters.writers.CourseWriter;
import org.data.datacollector.dataWriters.writers.UserCourseWriter;
import org.data.datacollector.dataWriters.writers.UserWriter;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DataCollectorApplication {

    private final ButeeDataCombinator buteeDataCombinator;

    private final CourseWriter courseWriter;

    private final CatDataCombinator catDataCombinator;

    private final UserWriter userWriter;

    private final UserCourseWriter userCourseWriter;

    private final Path path;

    public static void main(String[] args) {
        SpringApplication.run(DataCollectorApplication.class, args);
    }

    @Bean
    ApplicationRunner init() {
        return args -> {
            System.out.println("---------------------------------------------------");
            ButeeProcess();
            System.out.println("\n---------------------------------------------------");
            courseWriter.write();
            System.out.println("\n---------------------------------------------------");
            CatProcess();
            System.out.println("\n---------------------------------------------------");
            userWriter.write();
            System.out.println("\n---------------------------------------------------");
            userCourseWriter.write();
        };
    }

    private void ButeeProcess(){
        List<Butee> buteeList = buteeDataCombinator.getButeeList();
        CsvWriter.writeCsv(buteeList, path.getAbsolutePathOfCsv("butee"));
        System.out.println("buteeList Count :"+ buteeList.size());
        System.out.println("buteeWithoutUserId Count :"+ buteeDataCombinator.buteeWithoutUserIdList.size());
    }

    private void CatProcess(){
        List<Cat> catList = catDataCombinator.getCatList();
        CsvWriter.writeCsv(catList, path.getAbsolutePathOfCsv("cat"));
        System.out.println("catList Count :"+ catList.size());
        System.out.println("catWithoutInstructorId Count :"+ catDataCombinator.catWithoutInstructorIdList.size());
        System.out.println("catWithoutPntId Count :"+ catDataCombinator.catWithoutPntIdList.size());
//        catDataCombinator.catWithoutInstructorIdList.forEach(
//                cat -> {
//                    if(cat.getEmployeeNumberOfInstructor() != null) System.out.println(cat.getEmployeeNumberOfInstructor() + " " + cat.getInstructorId());
//                }
//        );
    }



}
