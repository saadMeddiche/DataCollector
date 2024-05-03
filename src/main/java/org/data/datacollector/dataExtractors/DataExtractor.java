package org.data.datacollector.dataExtractors;

import com.poiji.bind.Poiji;
import lombok.RequiredArgsConstructor;
import org.data.datacollector.services.Path;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataExtractor {

    private final Path path;

    public  <O> List<O> extractData(String fileName , Class<O> dataHolderClass){
        return Poiji.fromExcel(new File(path.getAbsolutePathOfXlsx(fileName)), dataHolderClass);
    }
}
