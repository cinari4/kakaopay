package com.kakaopay.apisrv.api;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseDataApi {
    public List<List<String>> csv(String filename) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        List<List<String>> records = new ArrayList<>();
        
        try (CSVReader csvReader = new CSVReader(new FileReader(classLoader.getResource(filename).getFile()))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            return null;
        }
        
        return records;
    }
}
