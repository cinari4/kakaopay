package com.kakaopay.apisrv.controller;

import com.kakaopay.apisrv.domain.Attraction;
import com.kakaopay.apisrv.domain.dto.ProgramDetailCountDto;
import com.kakaopay.apisrv.exception.ApiException;
import com.kakaopay.apisrv.response.ApiResponseCode;
import com.kakaopay.apisrv.response.ApiResponseDto;
import com.kakaopay.apisrv.service.AttractionService;
import com.kakaopay.apisrv.domain.dto.ProgramThemeDto;
import com.kakaopay.apisrv.domain.dto.RegionCountDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttractionController {
    @Autowired
    AttractionService attractionService;
    
    /**
     * 관광정보 데이터 조회
     * @param id
     * @param <T>
     * @return
     */
    @GetMapping("/attractions/v1.0/attraction/{id}")
    public <T> ApiResponseDto<T> getAttraction(@PathVariable Long id) {
        Attraction attraction = attractionService.getAttraction(id);
        return (ApiResponseDto<T>) ApiResponseDto.createOK(attraction);
    }
    
    /**
     * 관광정보 데이터 수정
     * @param id
     * @param param
     * @param <T>
     * @return
     */
    @PostMapping("/attractions/v1.0/attraction/{id}")
    public <T> ApiResponseDto<T> updateAttraction(@PathVariable Long id,
                                                 @RequestBody Attraction param) {
        if (checkUpdateAttractionParam(param)) {
            throw new ApiException(ApiResponseCode.BAD_PARAMETER);
        }
        
        param.setId(id);
        boolean result = attractionService.updateAttraction(param);
        return (ApiResponseDto<T>) ApiResponseDto.createOK(result);
    }
    
    /**
     * 관광정보 데이터 추가
     * @param param
     * @param <T>
     * @return
     */
    @PostMapping("/attractions/v1.0/attraction")
    public <T> ApiResponseDto<T> addAttraction(@RequestBody Attraction param) {
        if (checkAddAttractionParam(param)) {
            throw new ApiException(ApiResponseCode.BAD_PARAMETER);
        }
    
        boolean result= attractionService.addAttraction(param);
        return (ApiResponseDto<T>) ApiResponseDto.createOK(result);
    }
    
    /**
     * 서비스 지역 칼럼을 기준으로 특정 지역에서 진행되는 프로그램명과 테마를 출력하는 API
     * @param location
     */
    @GetMapping("/attractions/v1.0/attraction/location/{location}/programTheme")
    public <T> ApiResponseDto<T> getProgramTheme(@PathVariable String location) {
        List<ProgramThemeDto> programThemeDtos = attractionService.findByLocation(location);
        return (ApiResponseDto<T>) ApiResponseDto.createOK(programThemeDtos);
    }
    
    /**
     * 프로그램 소개에서 특정 문자열이 포함된 레코드에서 서비스 지역 개수를 세어 출력하는 API
     * @param keyword
     * @param <T>
     * @return
     */
    @GetMapping("/attractions/v1.0/attraction/programBrief/{keyword}/regionCount")
    public <T> ApiResponseDto<T> getRegionCount(@PathVariable String keyword) {
        RegionCountDto regionCountDtos = attractionService.findByProgramBrief(keyword);
        return (ApiResponseDto<T>) ApiResponseDto.createOK(regionCountDtos);
    }
    
    /**
     * 상세 정보를 읽어와서 입력 단어의 출현빈도수를 계산하여 출력하는 API
     * @param keyword
     * @param <T>
     * @return
     */
    @GetMapping("/attractions/v1.0/attraction/programDetail/{keyword}/count")
    public <T> ApiResponseDto<T> getProgramDetailCount(@PathVariable String keyword) {
        ProgramDetailCountDto programDetailCountDto = attractionService.findByProgramDetail(keyword);
        return (ApiResponseDto<T>) ApiResponseDto.createOK(programDetailCountDto);
    }
    
    /**
     * Update api 파라미터 체크
     * @param param
     * @return
     */
    private boolean checkUpdateAttractionParam(Attraction param) {
        if (param.getId() == null || param.getId() == 0
                || StringUtils.isEmpty(param.getTheme())
                || StringUtils.isEmpty(param.getLocation())
                || StringUtils.isEmpty(param.getProgramName())
                || StringUtils.isEmpty(param.getProgramBrief())
                || StringUtils.isEmpty(param.getProgramDetail())) {
            return true;
        }
        return false;
    }
    
    /**
     * Add api 파라미터 체크
     * @param param
     * @return
     */
    private boolean checkAddAttractionParam(Attraction param) {
        if (StringUtils.isEmpty(param.getTheme())
                || StringUtils.isEmpty(param.getLocation())
                || StringUtils.isEmpty(param.getProgramName())
                || StringUtils.isEmpty(param.getProgramBrief())
                || StringUtils.isEmpty(param.getProgramDetail())) {
            return true;
        }
        return false;
    }
}
