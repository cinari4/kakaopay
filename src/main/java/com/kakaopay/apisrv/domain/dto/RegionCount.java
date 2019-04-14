package com.kakaopay.apisrv.domain.dto;

import lombok.Getter;
import lombok.Setter;

public class RegionCount {
    @Getter
    @Setter
    private String region;
    
    @Getter
    @Setter
    private int count;
}
