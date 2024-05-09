package org.data.datacollector.dataWriters.writers;

import lombok.RequiredArgsConstructor;
import org.data.datacollector.dataCombinators.combinators.CatDataCombinator;
import org.data.datacollector.dataCombinators.models.Cat;
import org.data.datacollector.dataWriters.models.CatResult;
import org.data.datacollector.services.CsvWriter;
import org.data.datacollector.services.Path;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CatWriter {

    private final CatDataCombinator catDataCombinator;

    private final Path path;

    public void write(){
        List<Cat> catList = catDataCombinator.getCatList();
        CsvWriter.writeCsv(
                mapCatToCatResult(catList),
                path.getAbsolutePathOfCsv("cat")
        );
        System.out.println("catList Count :"+ catList.size());
        System.out.println("catWithoutInstructorId Count :"+ catDataCombinator.catWithoutInstructorIdList.size());
        System.out.println("catWithoutPntId Count :"+ catDataCombinator.catWithoutPntIdList.size());
//        catDataCombinator.catWithoutInstructorIdList.forEach(
//                cat -> {
//                    if(cat.getEmployeeNumberOfInstructor() != null) System.out.println(cat.getEmployeeNumberOfInstructor() + " " + cat.getInstructorId());
//                }
//        );
    }

    public List<CatResult> mapCatToCatResult(List<Cat> catList){
        return catList.stream().map(
                cat -> CatResult.builder()
                        .id(cat.getId())
                        .type(cat.getType())
                        .category(cat.getCategory())
                        .inst(cat.getInst())
                        .date(cat.getDate())
                        .pntId(cat.getPntId())
                        .instructorId(cat.getInstructorId())
                        .build()
        ).toList();
    }
}
