package org.data.datacollector.dataCombinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.models.Butee;
import org.data.datacollector.dataCombinators.models.Course;
import org.data.datacollector.dataCombinators.models.UserCourse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserCourseDataCombinator {

    private final CourseDataCombinator courseDataCombinator;

    private final ButeeDataCombinator buteeDataCombinator;

    private Long START_ID = 1L;

    public List<UserCourse> getUserCourseList(){
        List<Course> courseList = courseDataCombinator.getCourseListWithRepetition();
        List<Butee> buteeList = buteeDataCombinator.getButeeList();

        List<UserCourse> userCourseList = generateUserCourseList(courseList, buteeList);

        return generateIdForUserCourseList(userCourseList);
    }

    private List<UserCourse> generateUserCourseList(List<Course> courseList, List<Butee> buteeList){
        Set<UserCourse> userCourseList =  new HashSet<>();

        courseList.forEach(course -> {
            buteeList.forEach(butee -> {
                if(course.getEmployeeNumber().equals(butee.getEmployeeNumber())){
                    userCourseList.add(UserCourse.builder()
                            .courseId(course.getId())
                            .traineeId(butee.getUserId())
                            .oldButeeDate(butee.getValidityEnd())
                            .build());
                }
            });
        });

        return userCourseList.stream().toList();
    }

    private List<UserCourse> generateIdForUserCourseList(List<UserCourse> userCourseList){
        return userCourseList.stream().peek(userCourse -> userCourse.setId(String.valueOf(START_ID++))).toList();
    }
}
