package com.orioinc.storageservice;

import com.orioinc.storageservice.exceptions.NotFoundKeyException;
import com.orioinc.storageservice.model.DataText;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class DataControlService {
    private final Repository repository;
    private  final MongoTemplate mongoTemplate;

    public DataControlService(Repository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public String getText(String key) {
        String response;
        if (repository.findByKey(key) != null){
            response = repository.findByKey(key).getInputText();
        } else {
            throw new NotFoundKeyException(HttpStatus.NOT_FOUND, "Document not found");
        }
        return response;
    }

    public DataText saveData(String title, String text) {
        String key = getUniqueKey();
        LocalDate date = LocalDate.now();
        DataText data = new DataText(key, text, title, date);
        repository.save(data);
        return data;
    }

    public String getUniqueKey() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        String base64 = Base64.getUrlEncoder().withoutPadding().encodeToString(buffer.array());
        return base64;
    }

    public List<DataText> getAllDocuments() {
        return mongoTemplate.findAll(DataText.class, "myCollection");
    }
    public List<DataText> getDocumentsCreateToday() {
        Query query = new Query();
        query.addCriteria(Criteria.where("date").is(LocalDate.now()));
        return mongoTemplate.find(query, DataText.class, "myCollection");
    }
    public List<DataText> getDocumentsByServiceAPI() {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(".*10 questions by next language.*"));
        return mongoTemplate.find(query, DataText.class, "myCollection");
    }
    public List<DataText> getTenLastDocument() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "date")).limit(10);
        return mongoTemplate.find(query, DataText.class, "myCollection");
    }

}
