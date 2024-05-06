package org.data.datacollector.dataExtractors;

import org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL.CourseCrtlEL;
import org.data.datacollector.dataExtractors.dataHolders.dataFromDG.CourseDG;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSimu.CourseSimu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseDataExtractor extends DataExtractor {
    public List<CourseSimu> extractCourseSimu(){
        return extractData("Simu" , CourseSimu.class);
    }

    public List<CourseDG> extractCourseDG(){
        return extractData("DG" , CourseDG.class);
    }

    public List<CourseCrtlEL> extractCourseCrtlEL(){
        return extractData("CTRL EL" , CourseCrtlEL.class);
    }
}
