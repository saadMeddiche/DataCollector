package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.UnknownUserDataCombinator;
import org.data.datacollector.dataCombinators.combinators.UserDataCombinator;
import org.data.datacollector.dataCombinators.models.UnknownUser;
import org.data.datacollector.dataCombinators.models.User;
import org.data.datacollector.dataWriters.models.UnknownUserResult;
import org.data.datacollector.dataWriters.models.UserResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UnknowUserWriter {

    private final UnknownUserDataCombinator unknownUserDataCombinator;

    private final Path path;

    public void write() {
        List<UnknownUser> unknownUserList = unknownUserDataCombinator.getUnknownUserList();
        CsvWriter.writeCsv(
                mapUserToUserResult(unknownUserList)
                , path.getAbsolutePathOfCsv("unknown_users")
        );
        System.out.println("unknownUserList Count :"+ unknownUserList.size());
    }

    private List<UnknownUserResult> mapUserToUserResult(List<UnknownUser> unknownUserList) {
        return unknownUserList.stream()
                .map(user -> UnknownUserResult.builder()
                        .employeeNumber(user.getEmployeeNumber())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .sheetName(user.getSheetName())
                        .build()
                ).toList();
    }
}
