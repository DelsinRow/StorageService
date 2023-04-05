package com.orioinc.storageservice;

import com.orioinc.storageservice.model.DataText;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<DataText, String> {
    DataText findByKey(String key);

}
