package com.kakaopay.apisrv.controller;

import com.kakaopay.apisrv.response.ApiResponseDto;
import com.kakaopay.apisrv.service.ParseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParseDataController {
    @Autowired
    private ParseDataService parseDataService;

    /**
     *
     * @param filename
     */
    @PostMapping("/attractions/v1.0/attractions/csvfile/{filename}")
    public <T> ApiResponseDto<T> postAttractionsByCsv(@PathVariable String filename) {
        boolean result = parseDataService.loadAttractionCsvData(filename);
        return (ApiResponseDto<T>) ApiResponseDto.createOK(result);
    }

}
