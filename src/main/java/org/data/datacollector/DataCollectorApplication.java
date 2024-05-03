package org.data.datacollector;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.ButeeDataCombinator;
import org.data.datacollector.dataCombinators.models.Butee;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DataCollectorApplication {

    private final ButeeDataCombinator buteeDataCombinator;

    private final Path path;

    public static void main(String[] args) {
        SpringApplication.run(DataCollectorApplication.class, args);
    }

    @Bean
    ApplicationRunner init() {
        return args -> {
            List<Butee> buteeList = buteeDataCombinator.getButeeList();
            CsvWriter.writeCsv(buteeList, path.getAbsolutePathOfCsv("butee"));
//            buteeDataCombinator.buteeWithoutUserIdList.forEach(System.out::println);
            System.out.println("buteeList Count :"+ buteeList.size());
            System.out.println("buteeWithoutUserId Count :"+ buteeDataCombinator.buteeWithoutUserIdList.size());
        };
    }

}
