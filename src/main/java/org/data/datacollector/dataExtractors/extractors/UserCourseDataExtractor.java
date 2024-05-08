package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCRM.UserCourseCRM;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSC.UserCourseSC;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSimu.UserCourseSimu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCourseDataExtractor extends DataExtractor {

    public List<UserCourseCRM> extractUserCourseCRM(){
        return extractData("CRM" , UserCourseCRM.class);
    }

    public List<UserCourseSimu> extractUserCourseSimu(){
        return extractData("Simu" , UserCourseSimu.class);
    }

    public List<UserCourseSC> extractUserCourseSC(){
        return extractData("SC" , UserCourseSC.class);
    }
}
