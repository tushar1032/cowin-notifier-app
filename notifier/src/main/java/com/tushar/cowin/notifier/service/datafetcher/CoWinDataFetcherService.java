package com.tushar.cowin.notifier.service.datafetcher;

import com.tushar.cowin.notifier.model.dto.cowin.location.district.DistrictInfo;
import com.tushar.cowin.notifier.model.dto.cowin.location.state.StateInfo;
import com.tushar.cowin.notifier.model.dto.cowin.vaccineslots.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.tushar.cowin.notifier.commons.Constants.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class CoWinDataFetcherService {
    private final RestTemplate restTemplate;

    public Data getVaccineSlots(String url) {
        HttpEntity entity = getHttpEntity();
        ResponseEntity<Data> response = restTemplate.exchange(url, HttpMethod.GET, entity, Data.class);
        Data data = response.getBody();
        assert data != null;
        return data;
    }

    private HttpEntity getHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(USER_AGENT_HEADER_KEY, MOZILLA_HEADER);
        HttpEntity entity = new HttpEntity(httpHeaders);
        return entity;
    }

    public StateInfo getStateInfo() {
        HttpEntity entity = getHttpEntity();
        ResponseEntity<StateInfo> response = restTemplate.exchange(STATES_INFO_URL,HttpMethod.GET, entity, StateInfo.class);
        StateInfo stateInfo = response.getBody();
        assert stateInfo != null;
        return stateInfo;
    }

    public DistrictInfo getDistrictInfo(String url) {
        HttpEntity entity = getHttpEntity();
        ResponseEntity<DistrictInfo> response = restTemplate.exchange(url,HttpMethod.GET, entity, DistrictInfo.class);
        DistrictInfo districtInfo = response.getBody();
        assert districtInfo != null;
        return districtInfo;
    }



}
