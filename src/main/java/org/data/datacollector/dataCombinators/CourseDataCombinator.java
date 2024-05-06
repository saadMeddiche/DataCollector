package org.data.datacollector.dataCombinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.models.Course;
import org.data.datacollector.dataExtractors.CourseDataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.global.CourseData;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
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

        courseList.addAll(generateCtrlELCourse());

        courseList.addAll(generateSCCourse());

        courseList.addAll(generateCRMCourse());

        courseList.addAll(generateSSCourse());

        courseList.addAll(generateELPCourse());

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

    // Course With activityType = CEL
    private List<Course> generateCtrlELCourse(){
        return generateCourseList(courseDataExtractor.extractCourseCrtlEL(), "CEL");
    }

    // Course With activityType = SC
    private List<Course> generateSCCourse(){
        return generateCourseList(courseDataExtractor.extractCourseSC(), "SC");
    }

    // Course With activityType = CRM
    private List<Course> generateCRMCourse(){
        return generateCourseList(courseDataExtractor.extractCourseCRM(), "HF");
    }

    // Course With activityType = SS
    private List<Course> generateSSCourse(){
        return generateCourseList(courseDataExtractor.extractCourseSS(), "SS");
    }

    // Course With activityType = EA
    private List<Course> generateELPCourse(){
        return generateCourseList(courseDataExtractor.extractCourseELP(), "EA");
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
                                .courseDate(dateBuilder(row.getCourseDate()))
                                .employeeNumberOfInstructor(row.getEmployeeNumberOfInstructor())
                                .cat2(catBuilder(row.getCatTwo()))
                                .cat3(catBuilder(row.getCatThree()))
                                .place(row.getPlace())
                                .presenceMarked(presenceMarkedBuilder("false"))
                                .activityType(activityType)
                                .build()

                )).toList();
    }

    private String dateBuilder(String date){
        Date date1 = new Date(date);

        date1.setHours(12);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(date1);
    }

    private String catBuilder(String cat){
        return "YES".equals(cat) ? "1" : "0";
    }

    private String presenceMarkedBuilder(String presenceMarked){
        return "true".equals(presenceMarked) ? "1" : "0";
    }

}
