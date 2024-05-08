package org.data.datacollector.dataCombinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.models.UserCourse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserCourseDataCombinator {

    private final CourseDataCombinator courseDataCombinator;

    private final ButeeDataCombinator buteeDataCombinator;

    private Long START_ID = 1L;

    public List<UserCourse> getUserCourseList(){
        return generateUserCourseList();
    }

    private List<UserCourse> generateUserCourseList(){
        return List.of();
    }

    private List<UserCourse> generateIdForUserCourseList(List<UserCourse> userCourseList){
        return userCourseList.stream().peek(userCourse -> userCourse.setId(String.valueOf(START_ID++))).toList();
    }
}
