package com.kakaopay.apisrv.repository;

import com.kakaopay.apisrv.IntegrationTest;
import com.kakaopay.apisrv.domain.Attraction;
import com.kakaopay.apisrv.service.ParseDataService;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AttractionRepositoryTest extends IntegrationTest {
    @Autowired
    private AttractionRepository repository;
    
    @Autowired
    private ParseDataService parseDataService;
    
    /**
     * load sample data
     */
    @Before
    public void setup(){
        assertTrue(parseDataService.loadAttractionCsvData("data2.csv"));
    }
    
    @After
    public void cleanup() {
        repository.deleteAll();
    }
    
    /**
     * check findByLocationContainingTest from sample data
     */
    @Test
    public void findByLocationContainingTest_1() {
        List<Attraction> attractions = repository.findByLocationContaining("속초");
        assertEquals(2, attractions.size());
    }
    
    @Test
    public void findByProgramBriefContainingTest_1() {
        List<Attraction> attractions = repository.findByProgramBriefContaining("세계문화유산");
        assertEquals(2, attractions.size());
        assertTrue(attractions.get(0).getProgramBrief().contains("세계문화유산"));
        assertTrue(attractions.get(1).getProgramBrief().contains("세계문화유산"));
    }
    
    @Test
    public void findByProgramDetailContainingTest_1() {
        List<Attraction> attractions = repository.findByProgramDetailContaining("문화");
        int sum = 0;
        for (Attraction attraction : attractions) {
            sum += StringUtils.countMatches(attraction.getProgramDetail(), "문화");
        }
        assertEquals(59, sum);
    }
    
    @Test
    public void findByProgramDetailContainingTest_2() {
        List<Attraction> attractions = repository.findByProgramDetailContaining("한산대첩");
        int sum = 0;
        for (Attraction attraction : attractions) {
            sum += StringUtils.countMatches(attraction.getProgramDetail(), "한산대첩");
        }
        assertEquals(3, sum);
    }
}