package com.orioinc.storageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageSevice {

    @Autowired
    private StorageRepository repository;

    public String getText(String uniqueKey) {
        String text = repository.getTextByUniqueKey(uniqueKey);
        if (text.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return text;
    }

}
