package org.data.datacollector.dataCombinators.combinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.DataCombinator;
import org.data.datacollector.dataCombinators.models.Course;
import org.data.datacollector.dataCombinators.models.UserCourse;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUserIdEmployeeNumber.EmployeeNumberAndUserId;
import org.data.datacollector.dataExtractors.extractors.UserCourseDataExtractor;
import org.data.datacollector.dataExtractors.globalDataHolders.UserCourseData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserCourseDataCombinator extends DataCombinator {

    private final UserCourseDataExtractor userCourseDataExtractor;

    private final CourseDataCombinator courseDataCombinator;
    public List<UserCourse> userCourseWithoutInstructorIdList = new ArrayList<>();
    public List<UserCourse> userCourseWithoutTraineeIdList = new ArrayList<>();
    public List<UserCourse> userCourseWithoutCourseIdList = new ArrayList<>();
    private Long START_ID = 1L;

    public List<UserCourse> getUserCourseList(){

        // Extract Employee Number and User Id
        List<EmployeeNumberAndUserId> employeeNumberAndUserIdList = userCourseDataExtractor.extractEmployeeNumberAndUserId();

        // Generate UserCourseList
        List<UserCourse> userCourseList = new ArrayList<>();

        // Generate UserCourseList for CHL and add to userCourseList
        userCourseList.addAll(generateUserCourseList(userCourseDataExtractor.extractUserCourseCRM() , "HF"));
        userCourseList.addAll(generateUserCourseList(userCourseDataExtractor.extractUserCourseSimu() , "CHL"));
        userCourseList.addAll(generateUserCourseList(userCourseDataExtractor.extractUserCourseSC() , "SC"));
        userCourseList.addAll(generateUserCourseList(userCourseDataExtractor.extractUserCourseDG() , "DG"));
        userCourseList.addAll(generateUserCourseList(userCourseDataExtractor.extractUserCourseSS() , "SS"));
        userCourseList.addAll(generateUserCourseList(userCourseDataExtractor.extractUserCourseELP() , "EA"));

        // Attach Instructor Id and Trainee Id to UserCourse and Course Id to UserCourse
        List<UserCourse> userCourseListWithInstructorId = attachInstructorIdToUserCourse(userCourseList , employeeNumberAndUserIdList);
        List<UserCourse> userCourseListWithTraineeIdAndInstructorId = attachTraineeIdToUserCourse(userCourseListWithInstructorId , employeeNumberAndUserIdList);
        List<UserCourse> userCourseListWithTraineeIdAndInstructorIdAndCourseId = attachCourseIdToUserCourse(userCourseListWithTraineeIdAndInstructorId , courseDataCombinator.getCourseList());

        // Generate Id for UserCourseList and return
        return generateIdForUserCourseList(userCourseListWithTraineeIdAndInstructorIdAndCourseId);
    }

    private List<UserCourse> generateUserCourseList(List<? extends UserCourseData> userCourseList , String activityType) {
        return userCourseList.stream().flatMap(userCourseData ->
                userCourseData.getRows().stream()
                        .filter(row -> row.getValidityEnd() != null && row.getCourseDate() != null)
                        .map(row -> UserCourse.builder()
                            .courseDate(dateBuilder(row.getCourseDate()))
                            .instructorNumber(row.getInstructorNumber())
                            .oldButeeDate(row.getValidityEnd())
                            .activityType(activityType)
                            .traineeNumber(userCourseData.getEmployeeNumber())
                            .build()
                        )
        ).toList();
    }

    private List<UserCourse> attachInstructorIdToUserCourse(List<UserCourse> userCourseList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return userCourseList.stream().peek(userCourse -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(userCourse.getInstructorNumber()))
                .findFirst()
                .ifPresentOrElse(
                        (employeeNumberAndUserId) -> userCourse.setInstructorId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> userCourseWithoutInstructorIdList.add(userCourse)
                )
        ).toList();
    }

    private List<UserCourse> attachTraineeIdToUserCourse(List<UserCourse> userCourseList , List<EmployeeNumberAndUserId> employeeNumberAndUserIdList){
        return userCourseList.stream().peek(userCourse -> employeeNumberAndUserIdList.stream()
                .filter(employeeNumberAndUserId -> employeeNumberAndUserId.getEmployeeNumber().equals(userCourse.getTraineeNumber()))
                .findFirst()
                .ifPresentOrElse(
                        (employeeNumberAndUserId) -> userCourse.setTraineeId(employeeNumberAndUserId.getUserId())
                        ,
                        () -> userCourseWithoutTraineeIdList.add(userCourse)
                )
        ).toList();
    }

    private List<UserCourse> attachCourseIdToUserCourse(List<UserCourse> userCourseList , List<Course> courseList){
        return userCourseList.stream().peek(userCourse -> courseList.stream()
                .filter(course ->
                        Objects.equals(course.getInstructorNumber(), userCourse.getInstructorNumber())
                                && course.getCourseDate().equals(userCourse.getCourseDate())
                                && course.getActivityType().equals(userCourse.getActivityType())
                )
                .findFirst()
                .ifPresentOrElse(
                        (course) -> userCourse.setCourseId(course.getId())
                        ,
                        () -> userCourseWithoutCourseIdList.add(userCourse)
                )
        ).toList();
    }

    private List<UserCourse> generateIdForUserCourseList(List<UserCourse> userCourseList){
        return userCourseList.stream().peek(userCourse -> userCourse.setId(String.valueOf(START_ID++))).toList();
    }


}
