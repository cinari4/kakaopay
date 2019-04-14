package com.kakaopay.apisrv.domain.dto;

import lombok.Getter;
import lombok.Setter;

public class ProgramDetailCountDto {
    @Getter
    @Setter
    private String keyword;
    
    @Getter
    @Setter
    private int count;
}
