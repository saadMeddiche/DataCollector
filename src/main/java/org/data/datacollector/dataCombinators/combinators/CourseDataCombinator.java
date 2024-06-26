package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.Course;
import org.data.datacollector.dataExtractors.extractors.CourseDataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.globalDataHolders.CourseData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Scope("prototype")
public class CourseDataCombinator extends DataCombinator {

    private final CourseDataExtractor courseDataExtractor;

    private Long START_ID = 1L;

    public List<Course> courseWithoutInstructorIdList = new ArrayList<>();

    public List<Course> getCourseList(){

        List<Course> courseList = new ArrayList<>();

        courseList.addAll(generateCourseList(courseDataExtractor.extractCourseELP(), "EA"));

        courseList.addAll(generateCourseList(courseDataExtractor.extractCourseSimu(), "CHL"));

        courseList.addAll(generateCourseList(courseDataExtractor.extractCourseDG(), "DG"));

        courseList.addAll(generateCourseList(courseDataExtractor.extractCourseSC(), "SC"));

        courseList.addAll(generateCourseList(courseDataExtractor.extractCourseCRM(), "HF"));

        courseList.addAll(generateCourseList(courseDataExtractor.extractCourseSS(), "SS"));

        // Attach Instructor Id to Course
        List<Course> courseListWithInstructorId =   attachInstructorIdToCourse(courseList , courseDataExtractor.extractEmployeeNumberAndUserId());

        // Generate Id for Course
        return generateIdForCourse(courseListWithInstructorId);
    }

    private List<Course> attachInstructorIdToCourse(List<Course> courseList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return courseList.stream().peek(course -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(course.getInstructorNumber()))
                .findFirst()
                .ifPresentOrElse(
                        (employeeNumberAndUserId) -> course.setInstructorId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> courseWithoutInstructorIdList.add(course)
                )
        ).toList();
    }

    private List<Course> generateCourseList(List<? extends CourseData> courseDataList , String activityType){

        List<Course> courseList = courseDataList.stream()
                .flatMap(courseData -> courseData.getRows().stream()
                        .filter(row -> row.getCourseDate() != null && !row.getCourseDate().isEmpty())
                        .map(row -> Course.builder()
                                .courseDate(dateBuilder(row.getCourseDate()))
                                .instructorNumber(row.getEmployeeNumberOfInstructor())
                                .cat2(catBuilder(row.getCatTwo()))
                                .cat3(catBuilder(row.getCatThree()))
                                .place(row.getPlace())
                                .presenceMarked(presenceMarkedBuilder("false"))
                                .activityType(activityType)
                                .build()

                        )).distinct().toList();


        return courseList;
    }

    private List<Course> generateIdForCourse(List<Course> courseList){
        return courseList.stream().peek(course -> course.setId(String.valueOf(START_ID++))).toList();
    }

    private String catBuilder(String cat){
        return "YES".equals(cat) ? "1" : "0";
    }

    private String presenceMarkedBuilder(String presenceMarked){
        return "true".equals(presenceMarked) ? "1" : "0";
    }

}
