package com.kakaopay.apisrv.repository;

import com.kakaopay.apisrv.domain.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
}
