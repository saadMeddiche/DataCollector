package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCRM.CourseCRM;
import org.data.datacollector.dataExtractors.dataHolders.dataFromDG.CourseDG;
import org.data.datacollector.dataExtractors.dataHolders.dataFromELP.CourseELP;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSC.CourseSC;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSS.CourseSS;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSimu.CourseSimu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseDataExtractor extends DataExtractor {
    public List<CourseSimu> extractCourseSimu(){
        return extractData("data" , CourseSimu.class);
    }

    public List<CourseDG> extractCourseDG(){
        return extractData("data" , CourseDG.class);
    }

    public List<CourseSC> extractCourseSC(){
        return extractData("data" , CourseSC.class);
    }

    public List<CourseCRM> extractCourseCRM(){
        return extractData("data" , CourseCRM.class);
    }

    public List<CourseSS> extractCourseSS(){
        return extractData("data" , CourseSS.class);
    }

    public List<CourseELP> extractCourseELP(){
        return extractData("data" , CourseELP.class);
    }
}
