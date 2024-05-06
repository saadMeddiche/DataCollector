package org.data.datacollector.dataCombinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.models.Course;
import org.data.datacollector.dataExtractors.CourseDataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.global.CourseData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseDataCombinator {

    private final CourseDataExtractor courseDataExtractor;

    public List<Course> courseWithoutInstructorIdList = new ArrayList<>();

    public List<Course> getCourseList(){

        List<Course> courseList = new ArrayList<>();

        courseList.addAll(generateCHLCourse());

        courseList.addAll(generateDGCourse());

        return attachInstructorIdToCourse(courseList , courseDataExtractor.extractEmployeeNumberAndUserId());
    }

    // Course With activityType = CHL
    private List<Course> generateCHLCourse(){
        return generateCourseList(courseDataExtractor.extractCourseSimu(), "CHL");
    }

    // Course With activityType = DG
    private List<Course> generateDGCourse(){
        return generateCourseList(courseDataExtractor.extractCourseDG(), "DG");
    }

    private List<Course> attachInstructorIdToCourse(List<Course> courseList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return courseList.stream().peek(course -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(course.getEmployeeNumberOfInstructor()))
                .findFirst()
                .ifPresentOrElse(
                        (employeeNumberAndUserId) -> course.setInstructorId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> courseWithoutInstructorIdList.add(course)
                )
        ).toList();
    }

    private List<Course> generateCourseList(List<? extends CourseData> courseDataList , String activityType){
        return courseDataList.stream()
                .flatMap(courseData -> courseData.getRows().stream()
                        .filter(row -> row.getCourseDate() != null && !row.getCourseDate().isEmpty())
                        .map(row -> Course.builder()
                                .courseDate(new Date(row.getCourseDate()))
                                .employeeNumberOfInstructor(row.getEmployeeNumberOfInstructor())
                                .cat2(catBuilder(row.getCatTwo()))
                                .cat3(catBuilder(row.getCatThree()))
                                .activityType(activityType)
                                .presenceMarked(presenceMarkedBuilder("false"))
                                .build()
                )).toList();
    }

    private String catBuilder(String cat){
        return "YES".equals(cat) ? "1" : "NO".equals(cat) ? "0" : "";
    }

    private String presenceMarkedBuilder(String presenceMarked){
        return "true".equals(presenceMarked) ? "1" : "0";
    }

}
