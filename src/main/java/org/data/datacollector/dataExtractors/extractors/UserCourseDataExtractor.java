package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCRM.UserCourseCRM;
import org.data.datacollector.dataExtractors.dataHolders.dataFromDG.UserCourseDG;
import org.data.datacollector.dataExtractors.dataHolders.dataFromELP.UserCourseELP;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSC.UserCourseSC;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSS.UserCourseSS;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSimu.UserCourseSimu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCourseDataExtractor extends DataExtractor {

    public List<UserCourseCRM> extractUserCourseCRM(){
        return extractData("data2" , UserCourseCRM.class);
    }

    public List<UserCourseSimu> extractUserCourseSimu(){
        return extractData("data2" , UserCourseSimu.class);
    }

    public List<UserCourseSC> extractUserCourseSC(){
        return extractData("data2" , UserCourseSC.class);
    }

    public List<UserCourseDG> extractUserCourseDG(){
        return extractData("data2" , UserCourseDG.class);
    }

    public List<UserCourseSS> extractUserCourseSS(){
        return extractData("data2" , UserCourseSS.class);
    }

    public List<UserCourseELP> extractUserCourseELP(){
        return extractData("data2" , UserCourseELP.class);
    }
}
