package org.data.datacollector.dataExtractors;

import org.data.datacollector.dataExtractors.dataHolders.dataFromCRM.CourseCRM;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL.CourseCrtlEL;
import org.data.datacollector.dataExtractors.dataHolders.dataFromDG.CourseDG;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSC.CourseSC;
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

    public List<CourseSC> extractCourseSC(){
        return extractData("SC" , CourseSC.class);
    }

    public List<CourseCRM> extractCourseCRM(){
        return extractData("CRM" , CourseCRM.class);
    }
}
