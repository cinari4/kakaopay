package com.kakaopay.apisrv.api;

import com.kakaopay.apisrv.exception.ApiException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.kakaopay.apisrv.stub.ParseDataStub.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParseDataApiTest {
    @Test
    public void parse_test1_read_exist_file() {
        ParseDataApi parseDataApi = new ParseDataApi();
        List<List<String>> data = parseDataApi.csv(NORMAL_CSV_FILE);
        assertNotNull(data);
    }

    @Test(expected = ApiException.class)
    public void parse_test1_read_not_exist_file() {
        ParseDataApi parseDataApi = new ParseDataApi();
        parseDataApi.csv(NOT_EXIST_CSV_FILE);
    }

    @Test
    public void parse_test2_check_sampledata() {
        ParseDataApi parseDataApi = new ParseDataApi();
        List<List<String>> data = parseDataApi.csv(NORMAL_CSV_FILE);
        assertNotNull(data);

        // 읽어온 샘플 데이터 사이즈가 111인지
        assertEquals(NORMAL_CSV_ALL_ROW, data.size());

        // 읽어온 샘플 데이터의 각 행의 열 개수가 6인지
        for (List<String> rowData : data) {
            assertEquals(NORMAL_CSV_COLUMN, rowData.size());
        }

        // 읽어온 샘플 데이터의 첫 행 비교
        List<String> firstRowData = Arrays.asList("번호", "프로그램명", "테마별 분류", "서비스 지역", "프로그램 소개", "프로그램 상세 소개");
        assertEquals(firstRowData, data.get(0));

        // 읽어온 샘플 데이터의 끝 행 비교
        List<String> lastRowData = Arrays.asList("110", "[생태나누리] 아름다운 날들을 위한 힐링", "자연생태체험,", "충청북도 제천시 한수면", "", " 문경지구 실버세대의 건강증진과 여가생활 지원을 결합한 힐링 프로그램입니다.");
        assertEquals(lastRowData, data.get(data.size()-1));
    }
}

