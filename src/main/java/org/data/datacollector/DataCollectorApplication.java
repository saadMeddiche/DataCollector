package org.data.datacollector;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.*;
import org.data.datacollector.dataCombinators.models.*;
import org.data.datacollector.dataWriters.writers.UserCourseWriter;
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

    private final UserDataCombinator userDataCombinator;

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
            CourseProcess();
            System.out.println("\n---------------------------------------------------");
            CatProcess();
            System.out.println("\n---------------------------------------------------");
            UserProcess();
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

    private void CourseProcess(){
        List<Course> courseList = courseDataCombinator.getCourseList();
        CsvWriter.writeCsv(courseList, path.getAbsolutePathOfCsv("course"));
        System.out.println("courseList Count :"+ courseList.size());
        System.out.println("courseWithoutInstructorId Count :"+ courseDataCombinator.courseWithoutInstructorIdList.size());
//        courseDataCombinator.courseWithoutInstructorIdList.forEach(
//                course -> System.out.println(course.getEmployeeNumberOfInstructor() + " " + course.getCourseDate() + " " +course.getActivityType() + " " + course.getInstructorId())
//        );
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

    private void UserProcess(){
        List<User> userList = userDataCombinator.getUserList();
        CsvWriter.writeCsv(userList, path.getAbsolutePathOfCsv("user"));
        System.out.println("userList Count :"+ userList.size());
    }


}
