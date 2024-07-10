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
        return extractData("data2" , CourseSimu.class);
    }

    public List<CourseDG> extractCourseDG(){
        return extractData("data2" , CourseDG.class);
    }

    public List<CourseSC> extractCourseSC(){
        return extractData("data2" , CourseSC.class);
    }

    public List<CourseCRM> extractCourseCRM(){
        return extractData("data2" , CourseCRM.class);
    }

    public List<CourseSS> extractCourseSS(){
        return extractData("data2" , CourseSS.class);
    }

    public List<CourseELP> extractCourseELP(){
        return extractData("data2" , CourseELP.class);
    }
}
