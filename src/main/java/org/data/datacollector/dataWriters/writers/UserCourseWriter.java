package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.UserCourseDataCombinator;
import org.data.datacollector.dataCombinators.models.UserCourse;
import org.data.datacollector.dataWriters.models.UserCourseResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserCourseWriter {

    private final UserCourseDataCombinator userCourseDataCombinator;

    private final Path path;

    public void write(){
        List<UserCourse> userCourseList = userCourseDataCombinator.getUserCourseList();

        CsvWriter.writeCsv(
                mapUserCourseToUserCourseResult(userCourseList),
                path.getAbsolutePathOfCsv("user_course")
        );

        System.out.println("userCourseList Count :"+ userCourseList.size());
        System.out.println("userCourseWithoutInstructorId Count :"+ userCourseDataCombinator.userCourseWithoutInstructorIdList.size());
        System.out.println("userCourseWithoutTraineeId Count :"+ userCourseDataCombinator.userCourseWithoutTraineeIdList.size());
        System.out.println("userCourseWithoutCourseId Count :"+ userCourseDataCombinator.userCourseWithoutCourseIdList.size());

//        System.out.println("userCourseWithoutInstructorId :");
//        userCourseDataCombinator.userCourseWithoutInstructorIdList.forEach(
//                userCourse -> System.out.println(userCourse.getInstructorNumber() + " " + userCourse.getCourseDate() + " " + userCourse.getActivityType() + " " + userCourse.getCourseId())
//        );
//
//        System.out.println("userCourseWithoutTraineeId :");
//        userCourseDataCombinator.userCourseWithoutTraineeIdList.forEach(
//                userCourse -> System.out.println(userCourse.getTraineeNumber() + " " + userCourse.getTraineeId())
//        );
//
//        System.out.println("\nuserCourseWithoutCourseId :");
//        userCourseDataCombinator.userCourseWithoutCourseIdList.forEach(
//                userCourse -> System.out.println(userCourse.getInstructorNumber() + " " + userCourse.getCourseDate() + " " + userCourse.getActivityType() + " " + userCourse.getCourseId())
//        );

        System.out.println("\nuserCourseWithoutCourseIdForEnglish :");
        userCourseDataCombinator.userCourseWithoutCourseIdForEnglishList.forEach(
                userCourse -> System.out.println(userCourse.getInstructorNumber() + " " + userCourse.getCourseDate() + " " + userCourse.getActivityType() + " " + userCourse.getCourseId())
        );
    }

    private List<UserCourseResult> mapUserCourseToUserCourseResult(List<UserCourse> userCourseList) {
        return userCourseList.stream()
                .map(userCourse -> UserCourseResult.builder()
                        .id(userCourse.getId())
                        .employeeId(userCourse.getTraineeId())
                        .courseId(userCourse.getCourseId())
                        .oldButeeDate(userCourse.getOldButeeDate())
                        .build())
                .toList();
    }
}
