package com.orioinc.storageservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "myCollection")
public class DataText {
    @Id
    @Field("key")
    private String key;

    private String inputText;

    public DataText(String key, String inputText) {
        this.key = key;
        this.inputText = inputText;
    }
}
