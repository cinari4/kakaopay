package com.kakaopay.apisrv.service;

import com.kakaopay.apisrv.api.ParseDataApi;
import com.kakaopay.apisrv.domain.Attraction;
import com.kakaopay.apisrv.exception.ApiException;
import com.kakaopay.apisrv.repository.AttractionRepository;
import com.kakaopay.apisrv.response.ApiResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParseDataService {
    @Autowired
    private ParseDataApi parseDataApi;

    @Autowired
    private AttractionRepository attractionRepository;

    public boolean loadAttractionCsvData(String filename) {
        List<List<String>> csvData = parseDataApi.csv(filename);

        // check invalid format
        if (!checkInvalidData(csvData)) {
            throw new ApiException(ApiResponseCode.INVALID_FILE_FORMAT);
        }

        // remove first row
        csvData.remove(0);

        // save Attractions
        for (List<String> column : csvData) {
            Attraction save = attractionRepository.save(getAttraction(column));
            if (save == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * csv값을 받아서, Attractions에 맞는 형식으로 파싱되었는지 체크
     *
     * @param csvData
     * @return
     */
    private boolean checkInvalidData(List<List<String>> csvData) {
        if (csvData == null || csvData.size() <= 1) {
            return false;
        }

        int attractionFieldCnt = Attraction.class.getDeclaredFields().length;
        for (List<String> column : csvData) {
            if (attractionFieldCnt != column.size()) {
                return false;
            }
        }
        return true;
    }
 
    /**
     *  csv값에서 Attractions으로 매핑
     * @param data
     * @return
     */
    private Attraction getAttraction(List<String> data) {
        Attraction attraction = new Attraction();
        attraction.setId(Long.parseLong(data.get(0)));
        attraction.setProgramName(data.get(1));
        attraction.setTheme(data.get(2));
        attraction.setLocation(data.get(3));
        attraction.setProgramBrief(data.get(4));
        attraction.setProgramDetail(data.get(5));
        return attraction;

    }
}
