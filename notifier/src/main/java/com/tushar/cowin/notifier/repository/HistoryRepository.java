package com.tushar.cowin.notifier.repository;

import com.tushar.cowin.notifier.model.entity.AgeGroup;
import com.tushar.cowin.notifier.model.entity.History;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<History,String> {

    public History getHistoryByAgeGroupAndPincodePrefix(AgeGroup ageGroup, String pincodePrefix);
}
