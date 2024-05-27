package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.CourseDataCombinator;
import org.data.datacollector.dataCombinators.models.Course;
import org.data.datacollector.dataWriters.models.CourseResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseWriter {

    private final CourseDataCombinator courseDataCombinator;

    private final Path path;

    public void write(){
        List<Course> courseList = courseDataCombinator.getCourseList();
        CsvWriter.writeCsv(
                mapCourseToCourseResult(courseList),
                path.getAbsolutePathOfCsv("course")
        );
        System.out.println("courseList Count :"+ courseList.size());
        System.out.println("courseWithoutInstructorId Count :"+ courseDataCombinator.courseWithoutInstructorIdList.size());
//        courseDataCombinator.courseWithoutInstructorIdList.forEach(
//                course -> System.out.println(course.toString())
//        );
    }

    private List<CourseResult> mapCourseToCourseResult(List<Course> courseList) {
        return courseList.stream()
                .map(course -> CourseResult.builder()
                        .id(course.getId())
                        .courseDate(course.getCourseDate())
                        .activityType(course.getActivityType())
                        .instructorId(course.getInstructorId())
                        .place(course.getPlace())
                        .presenceMarked(course.getPresenceMarked())
                        .cat2(course.getCat2())
                        .cat3(course.getCat3())
                        .build())
                .toList();
    }
}
