package com.orioinc.storageservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orioinc.storageservice.ConstantValues;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document(collection = ConstantValues.DATA_TEXT_COLLECTION_NAME)
public class DataText {

    @Id
    @Field("key")
    private String key;

    @Field("inputText")
    @JsonIgnore
    private String inputText;

    @Field("title")
    private String title;

    @Field("source")
    private String source;

    @Field("date")
    private LocalDate date;

    public DataText(String key, String inputText, String title, String source, LocalDate date) {
        this.key = key;
        this.inputText = inputText;
        this.title = title;
        this.source = source;
        this.date = date;
    }

}
