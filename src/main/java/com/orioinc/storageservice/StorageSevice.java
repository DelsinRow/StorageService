package com.orioinc.storageservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StorageSevice {

    @Autowired
    private StorageRepository repository;

    public String getText(String uniqueKey) {
        String text = repository.getTextFirUniqueKey(uniqueKey);
        if (text.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return text;
    }

}
