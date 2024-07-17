package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.DataOfUnknownUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataOfUnknownUsersExtractor extends DataExtractor {

    public List<DataOfUnknownUser> extractDataOfUnknownUsers() {
        return extractData("data_of_unknown_users", DataOfUnknownUser.class);
    }
}
