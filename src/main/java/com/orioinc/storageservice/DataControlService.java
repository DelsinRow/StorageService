package com.orioinc.storageservice;

import com.orioinc.storageservice.exceptions.NotFoundKeyException;
import com.orioinc.storageservice.model.DataText;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
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

    public DataText saveData(String title, String source, String text) {
        String key = getUniqueKey();
        LocalDate date = LocalDate.now();
        DataText data = new DataText(key, text, title, source, date);
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
        ProjectionOperation projection = Aggregation.project("key", "title", "date");
        Aggregation aggregation = Aggregation.newAggregation(projection);
        return mongoTemplate.aggregate(aggregation, ConstantValues.DATA_TEXT_COLLECTION_NAME, DataText.class).getMappedResults();
    }

    public List<DataText> getDocumentsCreateToday() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("date").is(LocalDate.now())),
                Aggregation.project("key", "title", "date")
        );
        return mongoTemplate.aggregate(aggregation, ConstantValues.DATA_TEXT_COLLECTION_NAME, DataText.class).getMappedResults();
    }
    public List<DataText> getDocumentsByServiceAPI() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("source").is("ServiceAPI")),
                Aggregation.project("key", "title", "date")
        );
        return mongoTemplate.aggregate(aggregation, ConstantValues.DATA_TEXT_COLLECTION_NAME, DataText.class).getMappedResults();
    }
    public List<DataText> getTenLastDocument() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.sort(Sort.Direction.DESC, "date"),
                Aggregation.project("key", "title", "date"),
                Aggregation.limit(10)
        );
        return mongoTemplate.aggregate(aggregation, ConstantValues.DATA_TEXT_COLLECTION_NAME, DataText.class).getMappedResults();
    }

}
