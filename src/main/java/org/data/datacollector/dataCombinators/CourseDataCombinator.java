package org.data.datacollector.dataCombinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.models.Course;
import org.data.datacollector.dataExtractors.CourseDataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.global.CourseData;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CourseDataCombinator extends DataCombinator {

    private final CourseDataExtractor courseDataExtractor;

    private Long START_ID = 1L;

    public List<Course> courseWithoutInstructorIdList = new ArrayList<>();

    public List<Course> getCourseList(){

        List<Course> courseList = new ArrayList<>();

        courseList.addAll(generateCourseListWithoutRepetition(courseDataExtractor.extractCourseSimu(), "CHL"));

        courseList.addAll(generateCourseListWithoutRepetition(courseDataExtractor.extractCourseDG(), "DG"));

        courseList.addAll(generateCourseListWithoutRepetition(courseDataExtractor.extractCourseSC(), "SC"));

        courseList.addAll(generateCourseListWithoutRepetition(courseDataExtractor.extractCourseCRM(), "HF"));

        courseList.addAll(generateCourseListWithoutRepetition(courseDataExtractor.extractCourseSS(), "SS"));

        courseList.addAll(generateCourseListWithoutRepetition(courseDataExtractor.extractCourseELP(), "EA"));

        // Attach Instructor Id to Course
        List<Course> courseListWithInstructorId =   attachInstructorIdToCourse(courseList , courseDataExtractor.extractEmployeeNumberAndUserId());

        // Generate Id for Course
        return generateIdForCourse(courseListWithInstructorId);
    }

    public List<Course> getCourseListWithRepetition(){
        List<Course> courseList = new ArrayList<>();

        courseList.addAll(generateCourseListWithRepetition(courseDataExtractor.extractCourseSimu(), "CHL"));

        courseList.addAll(generateCourseListWithRepetition(courseDataExtractor.extractCourseDG(), "DG"));

        courseList.addAll(generateCourseListWithRepetition(courseDataExtractor.extractCourseSC(), "SC"));

        courseList.addAll(generateCourseListWithRepetition(courseDataExtractor.extractCourseCRM(), "HF"));

        courseList.addAll(generateCourseListWithRepetition(courseDataExtractor.extractCourseSS(), "SS"));

        courseList.addAll(generateCourseListWithRepetition(courseDataExtractor.extractCourseELP(), "EA"));

        List<Course> courseListWithInstructorId = attachInstructorIdToCourse(courseList , courseDataExtractor.extractEmployeeNumberAndUserId());

        return findIdForCourseListWithRepetition(courseListWithInstructorId , getCourseList());
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

    private List<Course> findIdForCourseListWithRepetition(List<Course> courseListWithRepetition , List<Course> courseListWithoutRepetition){

        for(Course courseWithRepetition : courseListWithRepetition){

            for(Course courseWithoutRepetition : courseListWithoutRepetition){

               if(
                       courseWithRepetition.getActivityType().equals(courseWithoutRepetition.getActivityType())
                        && courseWithRepetition.getCourseDate().equals(courseWithoutRepetition.getCourseDate())
                        && courseWithRepetition.getEmployeeNumberOfInstructor().equals(courseWithoutRepetition.getEmployeeNumberOfInstructor())
               ){
                     courseWithRepetition.setId(courseWithoutRepetition.getId());
               }

            }

        }

        return courseListWithRepetition;
    }

    private List<Course> generateCourseListWithRepetition(List<? extends CourseData> courseDataList , String activityType){
        return courseDataList.stream()
                .flatMap(courseData -> courseData.getRows().stream()
                        .filter(row -> row.getCourseDate() != null && !row.getCourseDate().isEmpty())
                        .map(row -> Course.builder()
                                .employeeNumber(courseData.getEmployeeNumber())
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

    private List<Course> generateCourseListWithoutRepetition(List<? extends CourseData> courseDataList , String activityType){

        List<Course> courseList = courseDataList.stream()
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

        // Remove Repetition
        Set<Course> courseSet = new HashSet<>(courseList);

        return courseSet.stream().toList();
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
