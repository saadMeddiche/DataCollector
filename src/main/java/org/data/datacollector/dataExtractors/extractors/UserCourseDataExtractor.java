package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCRM.UserCourseCRM;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCourseDataExtractor extends DataExtractor {

    public List<UserCourseCRM> extractUserCourseCRM(){
        return extractData("CRM" , UserCourseCRM.class);
    }
}
