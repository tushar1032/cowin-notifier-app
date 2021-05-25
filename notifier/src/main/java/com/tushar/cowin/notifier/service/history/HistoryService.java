package com.tushar.cowin.notifier.service.history;

import com.tushar.cowin.notifier.model.entity.AgeGroup;
import com.tushar.cowin.notifier.model.entity.History;
import com.tushar.cowin.notifier.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    @Transactional(value = Transactional.TxType.SUPPORTS)
    public String getHash(AgeGroup ageGroup, String pincodePrefix) {
        Optional<History> history = Optional.ofNullable(historyRepository.getHistoryByAgeGroupAndPincodePrefix(ageGroup, pincodePrefix));
            return history.orElseGet(History::new).getHash();
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public History saveHistory(History history) {
        return historyRepository.save(history);
    }
}
