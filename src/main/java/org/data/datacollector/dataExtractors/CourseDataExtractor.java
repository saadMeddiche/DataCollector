package org.data.datacollector.dataExtractors;

import org.data.datacollector.dataExtractors.dataHolders.dataFromSimu.CourseSimu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseDataExtractor extends DataExtractor {
    public List<CourseSimu> extractCourseSimu(){
        return extractData("Simu" , CourseSimu.class);
    }
}