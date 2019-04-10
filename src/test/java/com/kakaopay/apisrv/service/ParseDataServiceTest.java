package com.kakaopay.apisrv.service;

import com.kakaopay.apisrv.IntegrationTest;
import com.kakaopay.apisrv.IntegrationTestCategory;
import com.kakaopay.apisrv.exception.ApiException;
import com.kakaopay.apisrv.repository.AttractionRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import static com.kakaopay.apisrv.stub.ParseDataStub.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Category(IntegrationTestCategory.class)
public class ParseDataServiceTest extends IntegrationTest {
    @Autowired
    private ParseDataService parseDataService;

    @Autowired
    private AttractionRepository attractionRepository;

    @Test
    public void loadAttractionCsvData_test1_normal() {
        assertTrue(parseDataService.loadAttractionCsvData(NORMAL_CSV_FILE));
        assertEquals(NORMAL_CSV_DATA_ROW, attractionRepository.count());
    }

    @Test(expected = ApiException.class)
    public void loadAttractionCsvData_test2_invalid1() {
        parseDataService.loadAttractionCsvData(INVALID_CSV_FILE1);
        assertEquals(0, attractionRepository.count());
    }

    @Test(expected = ApiException.class)
    public void loadAttractionCsvData_test2_invalid2() {
        parseDataService.loadAttractionCsvData(INVALID_CSV_FILE2);
        assertEquals(0, attractionRepository.count());
    }

    @After
    public void cleanup() {
        attractionRepository.deleteAll();
    }
}