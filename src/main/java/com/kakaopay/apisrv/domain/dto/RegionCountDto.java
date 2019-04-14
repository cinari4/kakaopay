package com.kakaopay.apisrv.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class RegionCountDto {
    @Getter
    @Setter
    private String keyword;

    @Getter
    @Setter
    private List<RegionCount> regionCount;

}
