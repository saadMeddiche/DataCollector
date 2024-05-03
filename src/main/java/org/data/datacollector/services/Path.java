package org.data.datacollector.services;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Path {

    private final ResourceLoader resourceLoader;

    public String getAbsolutePathOfXlsx(String filename) {
        return getAbsolutePathOf(filename , "dataSource" , "xlsx");
    }

    public String getAbsolutePathOfCsv(String filename) {
        return getAbsolutePathOf(filename , "results" , "csv").replace("target\\classes" , "src/main/resources");
    }

    private String getAbsolutePathOf(String filename, String folder , String extension) {
        Resource resource = resourceLoader.getResource(String.format("classpath:%s/%s.%s" , folder , filename , extension));
        try {
            return resource.getFile().getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
