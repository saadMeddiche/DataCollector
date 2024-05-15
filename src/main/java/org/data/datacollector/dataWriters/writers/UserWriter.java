package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.UserDataCombinator;
import org.data.datacollector.dataCombinators.models.User;
import org.data.datacollector.dataWriters.models.UserResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserWriter {

    private final UserDataCombinator userDataCombinator;

    private final Path path;

    public void write() {
        List<User> userList = userDataCombinator.getUserList();
        CsvWriter.writeCsv(
                mapUserToUserResult(userList)
                , path.getAbsolutePathOfCsv("users")
        );
        System.out.println("userList Count :"+ userList.size());
    }

    private List<UserResult> mapUserToUserResult(List<User> userList) {
        return userList.stream()
                .map(user -> UserResult.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .personalType(user.getPersonalType())
                        .employeeNumber(user.getEmployeeNumber())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .cFunction(user.getCFunction())
                        .airplaneSpecialty(user.getAirplaneSpecialty())
                        .releaseDate(user.getReleaseDate())
                        .releasedByEmployeeNumber(user.getReleasedByEmployeeNumber())
                        .isSIE(user.getIsSIE())
                        .build()
                ).toList();
    }
}
