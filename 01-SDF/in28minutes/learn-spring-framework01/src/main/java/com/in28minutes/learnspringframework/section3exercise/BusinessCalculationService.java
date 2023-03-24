package com.in28minutes.learnspringframework.section3exercise;

import java.util.Arrays;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Component;

@Component

public class BusinessCalculationService {

    private DataService dataService;
    
    public BusinessCalculationService(DataService dataService) {
        super();
        this.dataService = dataService;
    }

    public int findMax() {
        return Arrays.stream(dataService.retrieveData())
        .max().orElse(0);
    }
    
}
