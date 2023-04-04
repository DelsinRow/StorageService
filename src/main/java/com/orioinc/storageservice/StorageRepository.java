package com.orioinc.storageservice;


import com.orioinc.storageservice.model.DataText;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class StorageRepository {

    private Map<String, String> repository = Map.of("qwerty", "Correct");

    public void addElement(DataText data) {
        repository.put(data.getKey(), data.getInputText());
    }

    public String getTextByUniqueKey(String uniqueKey) {
        return repository.get(uniqueKey);
    }

    public Map<String, String> getStorage() {
        return repository;
    }

    public void setStorage(Map<String, String> storage) {
        this.repository = storage;
    }
}
