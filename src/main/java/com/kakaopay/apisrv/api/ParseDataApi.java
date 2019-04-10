package com.kakaopay.apisrv.api;

import com.kakaopay.apisrv.exception.ApiException;
import com.kakaopay.apisrv.response.ApiResponseCode;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ParseDataApi {
    /**
     * csv형식의 데이터를 파싱합니다.
     *
     * @param filename
     * @return csv파싱한 데이터를 돌려줍니다
     */
    public List<List<String>> csv(String filename) {
        URL resource = this.getClass().getClassLoader().getResource(filename);
        if (resource == null) {
            throw new ApiException(ApiResponseCode.FILE_NOT_FOUND);
        }

        List<List<String>> records = new ArrayList<>();
        
        try (CSVReader csvReader = new CSVReader(new FileReader(resource.getFile()))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            throw new ApiException(ApiResponseCode.SERVER_ERROR);
        }
        return records;
    }
}
