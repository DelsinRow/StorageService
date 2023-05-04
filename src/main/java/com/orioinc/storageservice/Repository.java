package com.orioinc.storageservice;

import com.orioinc.storageservice.model.DataText;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Repository extends MongoRepository<DataText, String> {
    DataText findByKey(String key);
    List<DataText> findTop5ByOrderByDateDesc();
    List<DataText> findTop10ByOrderByDateDesc();
}