package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.DataOfMissedUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MissedUsersDataExtractor extends DataExtractor {

    public List<DataOfMissedUser> extractDataOfMissedUsers() {
        return extractData("data_of_unknown_users", DataOfMissedUser.class);
    }
}
