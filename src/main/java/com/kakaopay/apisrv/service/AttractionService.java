package com.kakaopay.apisrv.service;

import com.kakaopay.apisrv.domain.Attraction;
import com.kakaopay.apisrv.domain.dto.ProgramDetailCountDto;
import com.kakaopay.apisrv.repository.AttractionRepository;
import com.kakaopay.apisrv.domain.dto.ProgramThemeDto;
import com.kakaopay.apisrv.domain.dto.RegionCount;
import com.kakaopay.apisrv.domain.dto.RegionCountDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AttractionService {
    @Autowired
    AttractionRepository repository;
    
    public Attraction getAttraction(Long id) {
        Optional<Attraction> attraction = repository.findById(id);
        if (attraction.isPresent()) {
            return attraction.get();
        }
        return null;
    }
    
    public boolean updateAttraction(Attraction param) {
        if (repository.existsById(param.getId())) {
            Attraction save = repository.save(param);
            if (save != null) {
                return true;
            }
        }
        return false;
    }
    
    public boolean addAttraction(Attraction param) {
        repository.save(param);
        return true;
    }
    
    public List<ProgramThemeDto> findByLocation(String location) {
        List<Attraction> attractions = repository.findByLocationContaining(location);
        List<ProgramThemeDto> programThemeDto = new ArrayList<>();
        for (Attraction attraction : attractions) {
            ProgramThemeDto programInfo = new ProgramThemeDto();
            programInfo.setProgramName(attraction.getProgramName());
            programInfo.setTheme(attraction.getTheme());
            programThemeDto.add(programInfo);
        }
        return programThemeDto;
    }
    
    public RegionCountDto findByProgramBrief(String keyword) {
        List<Attraction> attractions = repository.findByProgramBriefContaining(keyword);
        Map<String, Integer> regionMap = new HashMap<>();
        List<RegionCount> regionCountList = new ArrayList<>();
        RegionCountDto regionCountDto = new RegionCountDto();
        
        for (Attraction attraction : attractions) {
            // location에 다수의 지역정보를 split(,)
            String[] arrLocation = attraction.getLocation().split(",");
            for (int i = 0; i< arrLocation.length; i++) {
                regionMap.put(arrLocation[i], getIncreasedValue(regionMap, arrLocation[i]));
            }
        }
        
        for (Map.Entry<String, Integer> entry : regionMap.entrySet()) {
            RegionCount regionCount = new RegionCount();
            regionCount.setRegion(entry.getKey());
            regionCount.setCount(entry.getValue());
            regionCountList.add(regionCount);
        }
        
        regionCountDto.setKeyword(keyword);
        regionCountDto.setRegionCount(regionCountList);
        
        return regionCountDto;
    }
    
    public ProgramDetailCountDto findByProgramDetail(String keyword) {
        List<Attraction> attractions = repository.findByProgramDetailContaining(keyword);
        int count = 0;
        
        for (Attraction attraction : attractions) {
            count += StringUtils.countMatches(attraction.getProgramDetail(), keyword);
        }
    
        ProgramDetailCountDto programDetailCountDto = new ProgramDetailCountDto();
        programDetailCountDto.setKeyword(keyword);
        programDetailCountDto.setCount(count);
        return programDetailCountDto;
    }
    
    private int getIncreasedValue(Map<String, Integer> map, String key) {
        if (map.containsKey(key)) {
            return map.get(key) + 1;
        }
        return 1;
    }
}
