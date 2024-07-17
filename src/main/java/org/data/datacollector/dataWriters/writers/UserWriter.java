package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.MissedUserDataCombinator;
import org.data.datacollector.dataCombinators.combinators.UserDataCombinator;
import org.data.datacollector.dataCombinators.models.MissedUser;
import org.data.datacollector.dataCombinators.models.User;
import org.data.datacollector.dataWriters.models.UserResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class UserWriter {

    private final UserDataCombinator userDataCombinator;

    private final MissedUserDataCombinator missedUserDataCombinator;

    private final Path path;

    public void write() {
        List<User> userList = userDataCombinator.getUserList();
        List<MissedUser> missedUserList = missedUserDataCombinator.getMissedUserList();

        List<UserResult> userResultList1 = mapUserToUserResult(userList);
        List<UserResult> userResultList2 = mapMissedUserToUserResult(missedUserList);

        List<UserResult> userResultList = Stream.of(userResultList1, userResultList2)
                .flatMap(List::stream)
                .toList();

        CsvWriter.writeCsv(
                userResultList
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
                        .status("Inactive")
                        .seniority("1.0")
                        .isSIE(user.getIsSIE())
                        .build()
                ).toList();
    }

    private List<UserResult> mapMissedUserToUserResult(List<MissedUser> missedUsers) {
        return missedUsers.stream()
                .map(user -> UserResult.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .personalType(user.getPersonalType())
                        .employeeNumber(user.getEmployeeNumber())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .cFunction(user.getCFunction())
                        .airplaneSpecialty(user.getAirPlaneSpecialty())
                        .email(user.getEmail())
                        .tel1(user.getTel1())
                        .nationality(user.getNationality())
                        .birthPlace(user.getBirthPlace())
                        .address(user.getAddress())
                        .gender(user.getGender())
                        .licence(user.getLicense())
                        .passportNumber(user.getPassportNumber())
                        .passportValidityDate(user.getPassportValidityDate())
                        .status("Inactive")
                        .seniority("1.0")
                        .isSIE(user.getIsSIE())
                        .build()
                ).toList();
    }
}
