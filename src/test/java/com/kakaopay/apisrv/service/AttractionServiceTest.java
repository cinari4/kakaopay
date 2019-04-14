package com.kakaopay.apisrv.service;

import com.kakaopay.apisrv.IntegrationTest;
import com.kakaopay.apisrv.domain.Attraction;
import com.kakaopay.apisrv.domain.dto.ProgramDetailCountDto;
import com.kakaopay.apisrv.repository.AttractionRepository;
import com.kakaopay.apisrv.domain.dto.ProgramThemeDto;
import com.kakaopay.apisrv.domain.dto.RegionCountDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.Assert.*;

public class AttractionServiceTest extends IntegrationTest {
    @Autowired
    private AttractionService attractionService;
    
    @Autowired
    private ParseDataService parseDataService;
    
    @Autowired
    private AttractionRepository repository;
    
    private Attraction stubAttraction;
    
    /**
     * load sample data
     */
    @Before
    public void setup(){
        assertTrue(parseDataService.loadAttractionCsvData("data2.csv"));
        stubAttraction = new Attraction();
        stubAttraction.setTheme("stub theme");
        stubAttraction.setLocation("stub location");
        stubAttraction.setProgramName("stub programName");
        stubAttraction.setProgramBrief("stub programBrief");
        stubAttraction.setProgramDetail("stub programDetail");
    }
    
    @After
    public void cleanup() {
        repository.deleteAll();
    }
    
    @Test
    public void getAttractionTest_1() {
        Attraction attraction = attractionService.getAttraction(1L);
        assertEquals("강원도 속초", attraction.getLocation());
        assertEquals("설악산 탐방안내소, 신흥사, 권금성, 비룡폭포", attraction.getProgramBrief());
        assertEquals(" 설악산은 왜 설악산이고, 신흥사는 왜 신흥사일까요? 설악산에 대해 정확히 알고, 배우고, 느낄 수 있는 당일형 생태관광입니다.", attraction.getProgramDetail());
        assertEquals("자연과 문화를 함께 즐기는 설악산 기행", attraction.getProgramName());
        assertEquals("문화생태체험,자연생태체험,", attraction.getTheme());
    }
    
    @Test
    public void updateAttractionTest_1() {
        stubAttraction.setId(1L);
        assertTrue(attractionService.updateAttraction(stubAttraction));
        Attraction updatedAttraction = attractionService.getAttraction(1L);
        assertEquals(stubAttraction.getLocation(), updatedAttraction.getLocation());
        assertEquals(stubAttraction.getTheme(), updatedAttraction.getTheme());
        assertEquals(stubAttraction.getProgramName(), updatedAttraction.getProgramName());
        assertEquals(stubAttraction.getProgramBrief(), updatedAttraction.getProgramBrief());
        assertEquals(stubAttraction.getProgramDetail(), updatedAttraction.getProgramDetail());
    }
    
    @Test
    public void addAttractionTest_1() {
        assertTrue(attractionService.addAttraction(stubAttraction));
        Attraction updatedAttraction = attractionService.getAttraction(111L);
        assertEquals(stubAttraction.getLocation(), updatedAttraction.getLocation());
        assertEquals(stubAttraction.getTheme(), updatedAttraction.getTheme());
        assertEquals(stubAttraction.getProgramName(), updatedAttraction.getProgramName());
        assertEquals(stubAttraction.getProgramBrief(), updatedAttraction.getProgramBrief());
        assertEquals(stubAttraction.getProgramDetail(), updatedAttraction.getProgramDetail());
    }
    
    @Test
    public void findByLocationTest_1() {
        List<ProgramThemeDto> programThemeDtos = attractionService.findByLocation("속초");
        assertEquals(2, programThemeDtos.size());
        assertEquals("자연과 문화를 함께 즐기는 설악산 기행", programThemeDtos.get(0).getProgramName());
        assertEquals("[설악산] 설악산에서 길을 묻다(설악을 내 품에)", programThemeDtos.get(1).getProgramName());
    }
    
    @Test
    public void findByLocationTest_2_not_exist_keyword() {
        List<ProgramThemeDto> programThemeDtos = attractionService.findByLocation("not_exist_keyword");
        assertEquals(0, programThemeDtos.size());
    }
    
    @Test
    public void findByProgramBriefTest_1() {
        RegionCountDto regionCountDto = attractionService.findByProgramBrief("세계문화유산");
        assertEquals("세계문화유산", regionCountDto.getKeyword());
        assertEquals("경상북도 경주시", regionCountDto.getRegionCount().get(0).getRegion());
        assertEquals(2, regionCountDto.getRegionCount().get(0).getCount());
    }
    
    @Test
    public void findByProgramBriefTest_2_not_exist_keyword() {
        RegionCountDto regionCountDto = attractionService.findByProgramBrief("not_exist_keyword");
        assertEquals(0, regionCountDto.getRegionCount().size());
    }
    
    @Test
    public void findByProgramDetailTest_1() {
        ProgramDetailCountDto programDetailCountDto = attractionService.findByProgramDetail("문화");
        assertEquals("문화", programDetailCountDto.getKeyword());
        assertEquals(59, programDetailCountDto.getCount());
    }
    
    @Test
    public void findByProgramDetailTest_2_not_exist_keyword() {
        ProgramDetailCountDto programDetailCountDto = attractionService.findByProgramDetail("not_exist_keyword");
        assertEquals(0, programDetailCountDto.getCount());
    }
}