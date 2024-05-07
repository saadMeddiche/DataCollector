package org.data.datacollector.dataCombinators;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.models.Butee;
import org.data.datacollector.dataCombinators.models.Course;
import org.data.datacollector.dataCombinators.models.UserCourse;
import org.springframework.stereotype.Component;

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
        List<Course> courseList = courseDataCombinator.getCourseList();
        List<Butee> buteeList = buteeDataCombinator.getButeeList();

        return generateUserCourseList(courseList, buteeList);
    }

    private List<UserCourse> generateUserCourseList(List<Course> courseList, List<Butee> buteeList){
        return List.of();
    }
}
