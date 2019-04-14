package com.kakaopay.apisrv.repository;

import com.kakaopay.apisrv.domain.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    List<Attraction> findByLocationContaining(String location);
    
    List<Attraction> findByProgramBriefContaining(String keyword);
    
    List<Attraction> findByProgramDetailContaining(String keyword);
}
