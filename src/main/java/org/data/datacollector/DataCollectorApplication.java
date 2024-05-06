package org.data.datacollector;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.ButeeDataCombinator;
import org.data.datacollector.dataCombinators.CatDataCombinator;
import org.data.datacollector.dataCombinators.CourseDataCombinator;
import org.data.datacollector.dataCombinators.models.Butee;
import org.data.datacollector.dataCombinators.models.Cat;
import org.data.datacollector.dataCombinators.models.Course;
import org.data.datacollector.dataExtractors.CatDataExtractor;
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

    private final CourseDataCombinator courseDataCombinator;

    private final CatDataCombinator catDataCombinator;

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
            CourseProcess();
            System.out.println("\n---------------------------------------------------");
            CatProcess();
        };
    }

    private void ButeeProcess(){
        List<Butee> buteeList = buteeDataCombinator.getButeeList();
        CsvWriter.writeCsv(buteeList, path.getAbsolutePathOfCsv("butee"));
        System.out.println("buteeList Count :"+ buteeList.size());
        System.out.println("buteeWithoutUserId Count :"+ buteeDataCombinator.buteeWithoutUserIdList.size());
    }

    private void CourseProcess(){
        List<Course> courseList = courseDataCombinator.getCourseList();
        CsvWriter.writeCsv(courseList, path.getAbsolutePathOfCsv("course"));
        System.out.println("courseList Count :"+ courseList.size());
        System.out.println("courseWithoutInstructorId Count :"+ courseDataCombinator.courseWithoutInstructorIdList.size());
    }

    private void CatProcess(){
        List<Cat> catList = catDataCombinator.getCatList();
        CsvWriter.writeCsv(catList, path.getAbsolutePathOfCsv("cat"));
        System.out.println("catList Count :"+ catList.size());
        System.out.println("catWithoutInstructorId Count :"+ catDataCombinator.catWithoutInstructorIdList.size());
        System.out.println("catWithoutPntId Count :"+ catDataCombinator.catWithoutPntIdList.size());
    }

}
