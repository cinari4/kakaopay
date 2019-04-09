package com.kakaopay.apisrv.api;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class ParseDataApiTest {
    @Test
    public void parse_test() {
        ParseDataApi parseDataApi = new ParseDataApi();
        List<List<String>> data = parseDataApi.csv("data2.csv");
        assertNotNull(data);
    }
}

