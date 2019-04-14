package com.kakaopay.apisrv.controller;

import com.kakaopay.apisrv.IntegrationTest;
import com.kakaopay.apisrv.domain.Attraction;
import com.kakaopay.apisrv.domain.dto.ProgramDetailCountDto;
import com.kakaopay.apisrv.domain.dto.ProgramThemeDto;
import com.kakaopay.apisrv.domain.dto.RegionCountDto;
import com.kakaopay.apisrv.repository.AttractionRepository;
import com.kakaopay.apisrv.response.ApiResponseCode;
import com.kakaopay.apisrv.response.ApiResponseDto;
import com.kakaopay.apisrv.service.ParseDataService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class AttractionControllerTest extends IntegrationTest {
    @Autowired
    AttractionController attractionController;
    
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
        stubAttraction.setId(null);
    }
    
    
    @Test
    public void getAttractionTest_1() {
        ApiResponseDto<Object> result = attractionController.getAttraction(1L);
        assertEquals(ApiResponseCode.OK, result.getCode());
        
        Attraction attraction = (Attraction) result.getData();
        assertEquals("자연과 문화를 함께 즐기는 설악산 기행", attraction.getProgramName());
    }
    
    @Test
    public void getAttractionTest_2_not_exist_record() {
        ApiResponseDto<Object> result = attractionController.getAttraction(200L);
        assertEquals(ApiResponseCode.OK, result.getCode());
        assertNull(result.getData());
    }
    
    @Test
    public void updateAttractionTest_1() {
        stubAttraction.setId(100L);
        ApiResponseDto<Object> result = attractionController.updateAttraction(1L, stubAttraction);
        assertEquals(ApiResponseCode.OK, result.getCode());
    
        boolean updateResult = (boolean) result.getData();
        assertTrue(updateResult);
    }
    
    @Test
    public void updateAttractionTest_2_not_exist_id() {
        stubAttraction.setId(200L);
        ApiResponseDto<Object> result = attractionController.updateAttraction(200L, stubAttraction);
        assertEquals(ApiResponseCode.OK, result.getCode());
        
        boolean updateResult = (boolean) result.getData();
        assertFalse(updateResult);
    }
    
    @Test
    public void addAttractionTest_1() {
        ApiResponseDto<Object> result = attractionController.addAttraction(stubAttraction);
        assertEquals(ApiResponseCode.OK, result.getCode());
    
        boolean addResult = (boolean) result.getData();
        assertTrue(addResult);
    }
    
    @Test
    public void getProgramThemeTest_1() {
        ApiResponseDto<Object> result = attractionController.getProgramTheme("속초");
        assertEquals(ApiResponseCode.OK, result.getCode());
    
        List<ProgramThemeDto> programThemeDtos = (List<ProgramThemeDto>) result.getData();
        assertEquals(2, programThemeDtos.size());
    }
    
    @Test
    public void getProgramThemeTest_2_not_exist_keyword() {
        ApiResponseDto<Object> result = attractionController.getProgramTheme("not_exist_keyword");
        assertEquals(ApiResponseCode.OK, result.getCode());
        
        List<ProgramThemeDto> programThemeDtos = (List<ProgramThemeDto>) result.getData();
        assertEquals(0, programThemeDtos.size());
    }
    
    @Test
    public void getRegionCountTest_1() {
        ApiResponseDto<Object> result = attractionController.getRegionCount("세계문화유산");
        assertEquals(ApiResponseCode.OK, result.getCode());
    
        RegionCountDto regionCountDto = (RegionCountDto) result.getData();
        assertEquals(1, regionCountDto.getRegionCount().size());
        assertEquals(2, regionCountDto.getRegionCount().get(0).getCount());
    }
    
    @Test
    public void getRegionCountTest_2_not_exist_keyword() {
        ApiResponseDto<Object> result = attractionController.getRegionCount("not_exist_keyword");
        assertEquals(ApiResponseCode.OK, result.getCode());
        
        RegionCountDto regionCountDto = (RegionCountDto) result.getData();
        assertEquals(0, regionCountDto.getRegionCount().size());
    }
    
    @Test
    public void getProgramDetailCountTest_1() {
        ApiResponseDto<Object> result = attractionController.getProgramDetailCount("문화");
        assertEquals(ApiResponseCode.OK, result.getCode());
    
        ProgramDetailCountDto programDetailCountDto = (ProgramDetailCountDto) result.getData();
        assertEquals(59, programDetailCountDto.getCount());
    }
    
    @Test
    public void getProgramDetailCountTest_2_not_exist_keyword() {
        ApiResponseDto<Object> result = attractionController.getProgramDetailCount("not_exist_keyword");
        assertEquals(ApiResponseCode.OK, result.getCode());
        
        ProgramDetailCountDto programDetailCountDto = (ProgramDetailCountDto) result.getData();
        assertEquals(0, programDetailCountDto.getCount());
    }
}