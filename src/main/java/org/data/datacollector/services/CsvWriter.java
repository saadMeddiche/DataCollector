package org.data.datacollector.services;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvWriter {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static <T> void writeCsv(List<T> data, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            if (!data.isEmpty()) {
                Class<?> clazz = data.getFirst().getClass();
                writeHeader(writer, clazz);
                data.forEach(obj -> writeRow(writer, clazz, obj));
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeHeader(FileWriter writer, Class<?> clazz) {
        String header = getFieldNames(clazz);
        writeLine(writer, header);
    }

    private static <T> void writeRow(FileWriter writer, Class<?> clazz, T obj) {
        String row = getFieldValues(clazz, obj);
        writeLine(writer, row);
    }

    private static void writeLine(FileWriter writer, String line) {
        try {
            writer.append(line).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFieldNames(Class<?> clazz) {
        return getFieldNames(clazz.getDeclaredFields());
    }

    private static String getFieldNames(Field[] fields) {
        return Stream.of(fields)
                .map(Field::getName)
                .collect(Collectors.joining(","));
    }

    private static <T> String getFieldValues(Class<?> clazz, T obj) {
        return getFieldValues(clazz.getDeclaredFields(), obj);
    }

    private static <T> String getFieldValues(Field[] fields, T obj) {
        return Stream.of(fields)
                .peek(field -> field.setAccessible(true))
                .map(field -> {
                    try {
                        Object value = field.get(obj);
                        return value != null ? escapeCsvField(value) : "";
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .collect(Collectors.joining(","));
    }


    // escape special characters in a CSV field ( Just in case :D )
    private static String escapeCsvField(Object value) {
        if (value instanceof Date) {
            return DATE_FORMAT.format((Date) value);
        } else {
            String field = value.toString();
            if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
                return "\"" + field.replaceAll("\"", "\"\"") + "\"";
            } else {
                return field;
            }
        }
    }
}
