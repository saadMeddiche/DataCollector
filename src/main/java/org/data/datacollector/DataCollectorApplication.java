package org.data.datacollector;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataGenerator.EmployeeNumberAndFunctionGenerator;
import org.data.datacollector.dataWriters.writers.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DataCollectorApplication {

    private final ButeeHistoryWriter buteeHistoryWriter;

    private final CourseWriter courseWriter;

    private final CatWriter catWriter;

    private final UserWriter userWriter;

    private final UserCourseWriter userCourseWriter;

    private final LicenceWriter licenceWriter;

    private final LineCheckWriter lineCheckWriter;

    private final FlightWriter flightWriter;

    private final UserLineCheckWriter userLineCheckWriter;

    private final InstructorNominationWriter instructorNominationWriter;

    private final DacAuthorisationWriter dacAuthorisationWriter;

    private final InstructorObservationWriter instructorObservationWriter;

    private final InstructorSimuPlaceDroiteWriter instructorSimuPlaceDroiteWriter;

    private final InstructorFlightPlaceDgWriter instructorFlightPlaceDgWriter;

    private final ButeeWriter buteeWriter;

    private final UnknowUserWriter unknownUserWriter;

    private final EmployeeNumberAndFunctionGenerator employeeNumberAndFunctionGenerator;

    public static void main(String[] args) {
        SpringApplication.run(DataCollectorApplication.class, args);
    }

    @Bean
    ApplicationRunner init() {
        return args -> {
            employeeNumberAndFunctionGenerator.generateChangeSet();
//            System.out.println("---------------------------------------------------");
//            buteeWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            buteeHistoryWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            courseWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            catWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            userWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            userCourseWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            licenceWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            lineCheckWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            flightWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            userLineCheckWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            instructorNominationWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            dacAuthorisationWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            instructorObservationWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            instructorSimuPlaceDroiteWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            instructorFlightPlaceDgWriter.write();
//            System.out.println("\n---------------------------------------------------");
//            unknownUserWriter.write();
        };
    }

    private <O> void printData(List<O> data){
        data.forEach(
                System.out::println
        );
    }




}
