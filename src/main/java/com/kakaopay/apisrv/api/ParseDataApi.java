package com.kakaopay.apisrv.api;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ParseDataApi {
    /**
     * csv형식의 데이터를 파싱합니다.
     *
     * @param filename
     * @return csv파싱한 데이터를 (파일을 찾을 수 없거나, IOException이 발생하면 null) 돌려줍니다
     */
    public List<List<String>> csv(String filename) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        List<List<String>> records = new ArrayList<>();
        
        try (CSVReader csvReader = new CSVReader(new FileReader(classLoader.getResource(filename).getFile()))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            return null;
        }
        return records;
    }
}
