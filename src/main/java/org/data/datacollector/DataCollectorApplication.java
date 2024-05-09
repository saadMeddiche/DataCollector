package org.data.datacollector;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.*;
import org.data.datacollector.dataCombinators.models.*;
import org.data.datacollector.dataWriters.writers.*;
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

    private final ButeeWriter buteeWriter;

    private final CourseWriter courseWriter;

    private final CatWriter catWriter;

    private final UserWriter userWriter;

    private final UserCourseWriter userCourseWriter;

    public static void main(String[] args) {
        SpringApplication.run(DataCollectorApplication.class, args);
    }

    @Bean
    ApplicationRunner init() {
        return args -> {
            System.out.println("---------------------------------------------------");
            buteeWriter.write();
            System.out.println("\n---------------------------------------------------");
            courseWriter.write();
            System.out.println("\n---------------------------------------------------");
            catWriter.write();
            System.out.println("\n---------------------------------------------------");
            userWriter.write();
            System.out.println("\n---------------------------------------------------");
            userCourseWriter.write();
        };
    }




}
