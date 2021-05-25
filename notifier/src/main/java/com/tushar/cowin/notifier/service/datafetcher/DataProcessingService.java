package com.tushar.cowin.notifier.service.datafetcher;

import com.tushar.cowin.notifier.model.dto.cowin.location.district.District;
import com.tushar.cowin.notifier.model.dto.cowin.location.district.DistrictInfo;
import com.tushar.cowin.notifier.model.dto.cowin.location.state.State;
import com.tushar.cowin.notifier.model.dto.cowin.location.state.StateInfo;
import com.tushar.cowin.notifier.model.dto.cowin.vaccineslots.Center;
import com.tushar.cowin.notifier.model.dto.cowin.vaccineslots.Data;
import com.tushar.cowin.notifier.model.dto.cowin.vaccineslots.Session;
import com.tushar.cowin.notifier.model.dto.mail.MailItem;
import com.tushar.cowin.notifier.model.dto.mail.SessionInfo;
import com.tushar.cowin.notifier.model.entity.AgeGroup;
import com.tushar.cowin.notifier.model.entity.History;
import com.tushar.cowin.notifier.model.entity.Location;
import com.tushar.cowin.notifier.service.history.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.tushar.cowin.notifier.commons.Constants.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class DataProcessingService {

    private final CoWinDataFetcherService coWinDataFetcherService;
    private final HistoryService historyService;

    public List<Center> getCenters(int age, String districtId, String pincodePrefix) {
        String url = prepareUrlForVaccineSlots(districtId);
        Data data = coWinDataFetcherService.getVaccineSlots(url);
        List<Center> centers = data.getCenters();
        List<Center> filteredCenters = getFilteredCenters(age, centers, pincodePrefix);
        if(checkIfHistoryIsClear(age, pincodePrefix, filteredCenters)) {
            return filteredCenters;
        }
        return Collections.emptyList();
    }

    private boolean checkIfHistoryIsClear(int age, String pincodePrefix, List<Center> filteredCenters) {
        String hash = historyService.getHash(AgeGroup.of(age),pincodePrefix);
        if(hash.equals(String.valueOf(filteredCenters.hashCode()))) {
            return false;
        }
        saveHistory(age, pincodePrefix, filteredCenters);
        return true;
    }

    private void saveHistory(int age, String pincodePrefix, List<Center> filteredCenters) {
        History history = new History();
        history.setAgeGroup(AgeGroup.of(age));
        history.setPincodePrefix(pincodePrefix);
        history.setHash(filteredCenters);
        historyService.saveHistory(history);
    }

    private String prepareUrlForVaccineSlots(String districtId) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-y");
        final String DATE = LocalDate.now().plusDays(1).format(dateTimeFormatter);
        return String.format(GET_CENTERS_BY_DISTRICT_FOR_7_DAYS_URL, districtId, DATE);
    }

    public List<MailItem> getCenters(int age, List<Center> filteredCenters) {
        List<MailItem> mailItemList = new ArrayList<>();
        for (Center center : filteredCenters) {
            List<Session> sessions = center.getSessions();
            List<SessionInfo> sessionInfoList = sessions.stream()
                    .map(session -> new SessionInfo(session.getDate(), session.getVaccine(), session.getAvailableCapacity()))
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(sessionInfoList)) {
                mailItemList.add(new MailItem(age, center.getName(), sessionInfoList, center.getPincode(), center.getBlockName()));
            }
        }
        log.info("For {} plus: {}", age, mailItemList);
        return mailItemList;
    }

    private List<Center> getFilteredCenters(int age, List<Center> centers, String pincodePrefix) {
        List<Center> filteredCenters = centers.stream()
                .filter(center -> center.getPincode().startsWith(pincodePrefix))
                .filter(center -> !CollectionUtils.isEmpty(center.getSessions()))
                .collect(Collectors.toList());

        log.info("\n\n\nVaccine centers with PINCODE stating with {}", pincodePrefix);
        log.info(String.valueOf(filteredCenters));

        for (Center c : filteredCenters) {
            List<Session> sessions = c.getSessions();
            List<Session> filteredSession = sessions.stream()
                    .filter(session -> session.getAvailableCapacity() != 0)
                    .filter(session -> session.getMinAgeLimit() == age)
                    .collect(Collectors.toList());
            c.setSessions(filteredSession);
        }

        return filteredCenters;
    }

    public String getDistrictId(Location location) {
        State state = getState(location.getState());
        District district = getDistrict(location.getDistrict(), state);
        return district.getId();
    }

    private District getDistrict(String districtName, State state) {
        String url = prepareUrlForDistrictId(state.getId());
        DistrictInfo districtInfo = coWinDataFetcherService.getDistrictInfo(url);
        District district = districtInfo.getDistricts()
                .stream()
                .filter(dist -> dist.getName()
                        .equalsIgnoreCase(districtName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find the District with the given Name: " + districtName));
        return district;
    }

    private State getState(String stateName) {
        StateInfo stateInfo = coWinDataFetcherService.getStateInfo();
        State state = stateInfo
                .getStates()
                .stream()
                .filter(s -> s.getName()
                        .equalsIgnoreCase(stateName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find the State with the given Name: " + stateName));
        return state;
    }

    private String prepareUrlForDistrictId(String stateId) {
        return String.format(DISTRICT_INFO_URL,stateId);
    }


}
