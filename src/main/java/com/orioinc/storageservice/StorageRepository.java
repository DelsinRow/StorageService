package com.orioinc.storageservice;


import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Data
public class StorageRepository {

    private Map<String, String> storage = Map.of("qwerty", "Correct");

    public void putInStorage(String key, String text) {
        storage.put(key, text);
    }

    public String getTextFirUniqueKey(String uniqueKey) {
        return storage.get(uniqueKey);
    }
}
